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

import static org.ops4j.peaberry.internal.ServiceQueryFactory.expectsSequence;
import static org.ops4j.peaberry.internal.ServiceQueryFactory.getServiceType;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.ops4j.peaberry.Service;

/**
 * @author stuart.mcculloch@jayway.net (Stuart McCulloch)
 */
public final class ServiceProviderFactory {

  /**
   * @param registry
   * @param memberType
   * @param spec
   * @return
   */
  @SuppressWarnings("unchecked")
  public static ServiceProvider<?> get(final ServiceRegistry registry,
      Type memberType, Service spec) {

    final String query = ServiceQueryFactory.get(spec, memberType);

    if (expectsSequence(memberType)) {

      return new ServiceProvider() {
        public Iterable get() {
          return new Iterable() {
            // fresh lookup each time
            public Iterator iterator() {
              return registry.lookup(query);
            }
          };
        }

        public Iterable resolve() {
          // provide static collection of services
          return asCollection(registry.lookup(query));
        }
      };
    } else {

      final Class<?> serviceType = getServiceType(memberType);

      return new ServiceProvider() {
        public Object get() {
          // provide dynamic dispatch of API calls to service instance
          return ServiceProxyFactory.get(serviceType, registry, query);
        }

        public Object resolve() {
          // provide static instance of service
          return registry.lookup(query).next();
        }
      };
    }
  }

  /**
   * Utility method to wrap an iterator up as a collection
   */
  private static <T> Collection<T> asCollection(Iterator<T> iterator) {
    Collection<T> collection = new ArrayList<T>();
    for (; iterator.hasNext();) {
      collection.add(iterator.next());
    }
    return collection;
  }
}
