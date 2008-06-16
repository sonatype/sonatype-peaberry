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
import static org.ops4j.peaberry.Peaberry.service;
import static org.ops4j.peaberry.util.Attributes.attributes;
import static org.osgi.framework.Constants.OBJECTCLASS;

import java.util.Properties;

import org.ops4j.peaberry.ServiceWatcher.Handle;
import org.testng.annotations.Test;

import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Test service injection using automatic bindings.
 * 
 * @author stuart.mcculloch@jayway.net (Stuart McCulloch)
 */
@Test(testName = "ServiceInjectionTests", suiteName = "OSGi")
public class ServiceInjectionTests
    extends OSGiServiceTester {

  @Inject
  SimpleService fieldService;

  final SimpleService ctorService;

  // default constructor for TestNG
  protected ServiceInjectionTests() {
    ctorService = null;
  }

  @Inject
  public ServiceInjectionTests(final SimpleService service) {
    ctorService = service;
  }

  SimpleService setterService;

  @Inject
  protected void setTestService(final SimpleService service) {
    setterService = service;
  }

  // test proxy support for API hierarchies
  protected static interface ExtendedService
      extends SimpleService {
    int encode();
  }

  @Inject
  @Named("extendedService")
  Object extendedService;

  @Inject
  @Named("extendedService")
  Iterable<?> extendedServices;

  public void checkInjection() {
    disableAllServices();

    missingService(ctorService);
    missingService(fieldService);
    missingService(setterService);

    enableService("A");

    checkService(ctorService, "A");
    checkService(fieldService, "A");
    checkService(setterService, "A");

    enableExtendedService("B");

    assert "B".equals(((SimpleService) extendedService).check());

    assert extendedService instanceof SimpleService;
    assert extendedService instanceof ExtendedService;

    assert ((ExtendedService) extendedService).encode() == "B".hashCode();

    assert extendedServices.iterator().next() instanceof SimpleService;
    assert extendedServices.iterator().next() instanceof ExtendedService;
  }

  protected void enableExtendedService(final String name) {
    final Properties properties = new Properties();

    properties.setProperty("name", name);

    properties.put(OBJECTCLASS, new String[] {
        SimpleService.class.getName(), ExtendedService.class.getName()
    });

    final Handle<?> handle = registry.add(new ExtendedService() {
      public String check() {
        if (handles.containsKey(name)) {
          return name;
        }

        throw new RuntimeException("Missing Service");
      }

      public int encode() {
        if (handles.containsKey(name)) {
          return name.hashCode();
        }

        throw new RuntimeException("Missing Service");
      }
    }, attributes(properties));

    handles.put(name, handle);
  }

  @Test(enabled = false)
  public static void configure(final Binder binder) {

    binder.bind(SimpleService.class).toProvider(service(SimpleService.class).single());

    binder.bind(Object.class).annotatedWith(named("extendedService")).toProvider(
        service(ExtendedService.class).single());

    binder.bind(Iterable.class).annotatedWith(named("extendedService")).toProvider(
        service(ExtendedService.class).multiple());
  }
}
