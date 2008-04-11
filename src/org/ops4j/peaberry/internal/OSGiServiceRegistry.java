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

import java.util.Arrays;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.Map;

import org.apache.felix.framework.util.MapToDictionary;
import org.ops4j.peaberry.ServiceRegistry;
import org.ops4j.peaberry.ServiceUnavailableException;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

/**
 * OSGi {@link ServiceRegistry} adaptor (proof-of-concept, not optimised)
 * 
 * @author stuart.mcculloch@jayway.net (Stuart McCulloch)
 */
public final class OSGiServiceRegistry
    implements ServiceRegistry {

  /**
   * Current bundle context, used to interrogate the registry.
   */
  final BundleContext bundleContext;

  public OSGiServiceRegistry(BundleContext bundleContext) {
    this.bundleContext = bundleContext;
  }

  /**
   * {@inheritDoc}
   */
  public <T> Iterator<T> lookup(final Class<? extends T> type, String filter) {

    /*
     * This is just a quick proof-of-concept implementation, it doesn't track
     * services and suffers from potential race conditions. A production ready
     * implementation will be available soon.
     */

    final ServiceReference[] services;

    try {

      services = bundleContext.getServiceReferences(null, filter);

      if (services != null) {
        Arrays.sort(services, new Comparator<ServiceReference>() {
          public int compare(ServiceReference lhs, ServiceReference rhs) {

            Long lhsId = (Long) lhs.getProperty(Constants.SERVICE_ID);
            Long rhsId = (Long) rhs.getProperty(Constants.SERVICE_ID);

            if (lhsId == rhsId) {
              return 0;
            }

            Long lhsRanking = (Long) lhs.getProperty(Constants.SERVICE_RANKING);
            Long rhsRanking = (Long) rhs.getProperty(Constants.SERVICE_RANKING);

            if (lhsRanking == rhsRanking) {
              return rhsId.compareTo(lhsId);
            } else {
              return lhsRanking.compareTo(rhsRanking);
            }
          }
        });
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return new Iterator<T>() {
      int i = 0;

      public boolean hasNext() {
        return services != null && i < services.length;
      }

      public T next() {
        try {
          return type.cast(bundleContext.getService(services[i++]));
        } catch (Exception e) {
          throw new ServiceUnavailableException(e);
        }
      }

      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

  /**
   * {@inheritDoc}
   */
  public <T> Handle<T> add(T service, Map<?, ?> properties) {
    Dictionary<?, ?> props = new MapToDictionary(properties);
    String clazz = service.getClass().getName();

    final ServiceRegistration registration =
        bundleContext.registerService(clazz, service, props);

    return new Handle<T>() {

      public void modify(Map<?, ?> properties) {
        registration.setProperties(new MapToDictionary(properties));
      }

      public void remove() {
        registration.unregister();
      }
    };
  }
}
