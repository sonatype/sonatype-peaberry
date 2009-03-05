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

package org.ops4j.peaberry.util;

import java.util.Map;

import org.ops4j.peaberry.Export;
import org.ops4j.peaberry.Import;
import org.ops4j.peaberry.ServiceWatcher;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * Skeletal implementation to simplify development of {@link ServiceWatcher}s.
 * <p>
 * Developers only have to extend this class and provide implementations of the
 * {@link #adding(Import)}, {@link #modified(Object, Map)}, and {@link #removed}
 * service tracking methods. The design of this helper class is loosely based on
 * the OSGi {@link ServiceTrackerCustomizer}.
 * 
 * @author mcculls@gmail.com (Stuart McCulloch)
 */
public abstract class AbstractWatcher<S>
    implements ServiceWatcher<S> {

  @SuppressWarnings("unchecked")
  public <T extends S> Export<T> add(final Import<T> service) {
    return (Export) new TrackingExport((Import) service);
  }

  // simple instance + attributes holder
  private static class SimpleImport<T>
      implements Import<T> {

    protected T instance;
    protected Map<String, ?> attributes;

    public SimpleImport(final T instance, final Map<String, ?> attributes) {
      this.instance = instance;
      this.attributes = attributes;
    }

    public T get() {
      return instance;
    }

    public Map<String, ?> attributes() {
      return attributes;
    }

    public void unget() {/* nothing to do */}
  }

  private final class TrackingExport
      extends SimpleImport<S>
      implements Export<S> {

    public TrackingExport(final Import<S> service) {
      super(adding(service), service.attributes());
    }

    public synchronized void put(final S newInstance) {
      unput(); // make sure it's been unput
      if (null != newInstance) {
        instance = adding(new SimpleImport<S>(newInstance, attributes));
      }
    }

    public synchronized void attributes(final Map<String, ?> newAttributes) {
      attributes = newAttributes;
      if (null != instance) {
        modified(instance, attributes);
      }
    }

    public synchronized void unput() {
      if (null != instance) {
        removed(instance);
        instance = null;
      }
    }
  }

  /**
   * Notification that a service has been added to this watcher.
   * 
   * @param service new service handle
   * @return tracking instance, null if the service shouldn't be tracked
   */
  protected S adding(final Import<S> service) {
    return service.get();
  }

  /**
   * Notification that some service attributes have been modified.
   * 
   * @param instance tracking instance
   * @param attributes service attributes
   */
  protected void modified(final S instance, final Map<String, ?> attributes) {} // NOPMD

  /**
   * Notification that a service has been removed from this watcher.
   * 
   * @param instance tracking instance
   */
  protected void removed(final S instance) {} // NOPMD
}
