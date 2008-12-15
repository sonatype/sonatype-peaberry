/**
 * Copyright (C) 2008 Stuart McCulloch
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ops4j.peaberry.osgi;

import static org.osgi.framework.Constants.SERVICE_ID;
import static org.osgi.framework.Constants.SERVICE_RANKING;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.ops4j.peaberry.AttributeFilter;
import org.ops4j.peaberry.Import;
import org.ops4j.peaberry.ServiceUnavailableException;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * {@code Import} implementation backed by an OSGi {@code ServiceReference}.
 * 
 * @author mcculls@gmail.com (Stuart McCulloch)
 */
final class OSGiServiceImport
    implements Import<Object>, Comparable<OSGiServiceImport> {

  private final BundleContext bundleContext;
  private final ServiceReference ref;

  // heavily used attributes
  private final long id;
  private int rank;

  private final Map<String, ?> attributes;

  private Object instance;

  private volatile boolean calledGet;
  private final AtomicInteger count;

  public OSGiServiceImport(final BundleContext bundleContext, final ServiceReference ref) {

    this.bundleContext = bundleContext;
    this.ref = ref;

    // cache attributes used when sorting services
    id = getNumberProperty(SERVICE_ID).longValue();
    rank = getNumberProperty(SERVICE_RANKING).intValue();

    attributes = new OSGiServiceAttributes(ref);

    count = new AtomicInteger();
  }

  public boolean updateRanking() {
    // ranking is mutable...
    final int oldRank = rank;
    rank = getNumberProperty(SERVICE_RANKING).intValue();
    return oldRank != rank;
  }

  public boolean matches(final AttributeFilter filter) {
    return filter.matches(attributes);
  }

  public Object get() {
    count.getAndIncrement();
    if (!calledGet) {
      synchronized (this) {
        if (!calledGet) {
          try {
            instance = bundleContext.getService(ref);
          } catch (final RuntimeException re) {
            throw new ServiceUnavailableException(re);
          } finally {
            calledGet = true;
          }
        }
      }
    }
    return instance;
  }

  public Map<String, ?> attributes() {
    return calledGet && instance != null ? attributes : null;
  }

  public void unget() {
    count.decrementAndGet();
  }

  public void flush(final boolean serviceUnregistered) {
    if (serviceUnregistered) {
      // no need to unget, as service is gone
      instance = null;
      calledGet = true; // flush
      return;
    }

    // check no-one is using the service
    if (calledGet && 0 == count.get()) {

      synchronized (this) {
        if (calledGet) {
          // attempt to block other threads calling get()
          calledGet = false;

          if (count.get() > 0) {
            // another thread snuck in, so roll back...
            calledGet = true;
          } else {
            instance = null;
            try {
              // cached result not being used
              bundleContext.ungetService(ref);
            } catch (final IllegalStateException e) {/* already gone */} // NOPMD
          }
        }
      }
    }
  }

  @Override
  public boolean equals(final Object rhs) {
    if (rhs instanceof OSGiServiceImport) {
      // service id is a unique identifier
      return id == ((OSGiServiceImport) rhs).id;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return (int) (id ^ (id >>> 32));
  }

  public int compareTo(final OSGiServiceImport rhs) {

    if (id == rhs.id) {
      return 0;
    }

    if (rank == rhs.rank) {
      // prefer lower service id
      return id < rhs.id ? -1 : 1;
    }

    // but higher ranking beats all
    return rank < rhs.rank ? 1 : -1;
  }

  private Number getNumberProperty(final String key) {
    final Object num = ref.getProperty(key);
    if (num instanceof Number) {
      return (Number) num;
    }
    return 0;
  }
}
