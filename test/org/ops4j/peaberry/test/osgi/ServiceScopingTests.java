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

package org.ops4j.peaberry.test.osgi;

import static com.google.inject.name.Names.named;
import static org.ops4j.peaberry.Peaberry.osgiServiceRegistry;
import static org.ops4j.peaberry.Peaberry.registration;
import static org.ops4j.peaberry.Peaberry.service;

import java.util.Iterator;
import java.util.Map;

import org.ops4j.peaberry.Export;
import org.ops4j.peaberry.Import;
import org.ops4j.peaberry.ServiceRegistry;
import org.ops4j.peaberry.test.osgi.OSGiServiceTester.SimpleService;
import org.osgi.framework.BundleContext;
import org.testng.annotations.Test;

import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;

/**
 * Test service scoping.
 * 
 * @author mcculls@gmail.com (Stuart McCulloch)
 */
@Test(testName = "ServiceScopingTests", suiteName = "OSGi")
public final class ServiceScopingTests {

  @Test(enabled = false)
  public static void configure(final Binder binder) {

    final Key<ServiceRegistry> registryKey = Key.get(ServiceRegistry.class, named("counting"));
    final Key<SimpleService> serviceKey = Key.get(SimpleService.class, named("counting"));

    binder.bind(registryKey).toProvider(CountingRegistryProvider.class);
    binder.bind(serviceKey).toProvider(CountingServiceProvider.class);

    binder.bind(SimpleService.class).toProvider(
        service(SimpleService.class).in(registryKey).single());

    binder.bind(Export.class).toProvider(registration(serviceKey).in(registryKey).export());
  }

  static class CountingRegistryProvider
      implements Provider<ServiceRegistry> {

    @Inject
    BundleContext bundleContext;

    static volatile int lookupCount = 0;
    static volatile int iteratorCount = 0;
    static volatile int exportCount = 0;

    public ServiceRegistry get() {
      final ServiceRegistry osgiRegistry = osgiServiceRegistry(bundleContext);
      return new ServiceRegistry() {

        @SuppressWarnings("unchecked")
        public <T> Iterable<Import<T>> lookup(final Class<? extends T> clazz, final String filter) {

          lookupCount++;
          final Iterable<Import<T>> services = osgiRegistry.lookup(clazz, filter);

          return new Iterable<Import<T>>() {
            public Iterator<Import<T>> iterator() {

              iteratorCount++;
              return services.iterator();
            }
          };
        }

        public <S, T extends S> Export<S> export(final T service, final Map<String, ?> attributes) {

          exportCount++;
          return osgiRegistry.export((S) service, attributes);
        }
      };
    }
  }

  static class CountingServiceProvider
      implements Provider<SimpleService> {

    public SimpleService get() {
      return new SimpleService() {
        public String check() {

          final String lookupCount = "lookup:" + CountingRegistryProvider.lookupCount;
          final String iteratorCount = "iterator:" + CountingRegistryProvider.iteratorCount;
          final String exportCount = "export:" + CountingRegistryProvider.exportCount;

          return lookupCount + ',' + iteratorCount + ',' + exportCount;
        }
      };
    }
  }

  @Inject
  SimpleService service;

  @Inject
  Injector injector;

  public void checkScoping() {

    @SuppressWarnings("unchecked")
    Export handle = injector.getInstance(Export.class);

    assert service.check().equals("lookup:1,iterator:1,export:1");
    assert service.check().equals("lookup:1,iterator:2,export:1");

    handle.remove();

    handle = injector.getInstance(Export.class);

    assert service.check().equals("lookup:1,iterator:3,export:2");
    assert service.check().equals("lookup:1,iterator:4,export:2");

    handle.remove();
  }
}
