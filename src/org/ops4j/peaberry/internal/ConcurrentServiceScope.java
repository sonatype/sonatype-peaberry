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

package org.ops4j.peaberry.internal;

import java.util.Iterator;
import java.util.Map;

import org.ops4j.peaberry.Export;
import org.ops4j.peaberry.Import;
import org.ops4j.peaberry.ServiceScope;

/**
 * A {@link ServiceScope} with similar behaviour to {@link ConcurrentImport}.
 * 
 * @author mcculls@gmail.com (Stuart McCulloch)
 */
final class ConcurrentServiceScope<S>
    implements ServiceScope<S> {

  final Iterable<Import<S>> handles;
  final ServiceScope<? super S> scope;

  // the "best" service
  Import<S> currentImport;
  Export<S> currentExport;

  ConcurrentServiceScope(final Iterable<Import<S>> handles, final ServiceScope<? super S> scope) {
    this.handles = handles;
    this.scope = scope;
  }

  // every candidate has a tracker
  private class TrackingExport<T>
      implements Export<T> {

    private final Import<T> trackedImport;

    TrackingExport(final Import<T> trackedImport) {
      this.trackedImport = trackedImport;
    }

    // Export aspect is only active when our service is "best"

    @SuppressWarnings("unchecked")
    public void put(final T instance) {
      synchronized (ConcurrentServiceScope.this) {
        if (trackedImport == currentImport) { // NOPMD
          currentExport.put((S) instance);
        }
      }
    }

    public void attributes(final Map<String, ?> attributes) {
      synchronized (ConcurrentServiceScope.this) {
        if (trackedImport == currentImport) { // NOPMD
          currentExport.attributes(attributes);
        }
      }
    }

    public void unput() {
      synchronized (ConcurrentServiceScope.this) {
        if (trackedImport == currentImport) { // NOPMD
          currentExport.unput();

          // pass the baton onto the next "best" service
          final Iterator<Import<S>> i = handles.iterator();
          if (i.hasNext()) {
            currentImport = i.next();
            currentExport = scope.add(currentImport);
          } else {
            currentImport = null;
            currentExport = null;
          }
        }
      }
    }

    // Import aspect is always available

    public T get() {
      return trackedImport.get();
    }

    public Map<String, ?> attributes() {
      return trackedImport.attributes();
    }

    public void unget() {
      trackedImport.unget();
    }
  }

  @SuppressWarnings("unchecked")
  public synchronized <T extends S> Export<T> add(final Import<T> service) {
    final Iterator<Import<S>> i = handles.iterator();

    // will this new service become the "best"
    if (i.hasNext() && i.next().equals(service)) {
      if (currentExport != null) {
        currentExport.unput();
      }

      currentImport = (Import<S>) service;
      currentExport = scope.add(currentImport);
    }

    // every new service has a tracker
    return new TrackingExport<T>(service);
  }

  @Override
  public boolean equals(final Object rhs) {
    if (rhs instanceof ConcurrentServiceScope) {
      final ConcurrentServiceScope<?> concurrentScope = (ConcurrentServiceScope<?>) rhs;
      return handles.equals(concurrentScope.handles) && scope.equals(concurrentScope.scope);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return handles.hashCode() ^ scope.hashCode();
  }
}
