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

import static org.ops4j.peaberry.util.Attributes.attributes;
import static org.osgi.framework.Constants.OBJECTCLASS;

import java.util.Properties;

import org.ops4j.peaberry.ServiceWatcher.Handle;
import org.ops4j.peaberry.test.osgi.OSGiServiceTester.SimpleService;
import org.testng.annotations.Test;

import com.google.inject.Inject;

/**
 * Test service injection using automatic bindings.
 * 
 * @author stuart.mcculloch@jayway.net (Stuart McCulloch)
 */
@Test(testName = "ServiceInjectionTests", suiteName = "OSGi")
public class ServiceInjectionTests
    extends OSGiServiceTester {

  @Inject
  @Service
  SimpleService fieldService;

  final SimpleService ctorService;

  // default constructor for TestNG
  protected ServiceInjectionTests() {
    ctorService = null;
  }

  @Inject
  public ServiceInjectionTests(@Service
  final SimpleService service) {
    ctorService = service;
  }

  SimpleService setterService;

  @Inject
  protected void setTestService(@Service
  final SimpleService service) {
    setterService = service;
  }

  // test proxy support for API hierarchies
  protected static interface ExtendedService
      extends SimpleService {
    int encode();
  }

  @Inject
  @Service(interfaces = ExtendedService.class)
  Object extendedService1;

  @Inject
  @Service(interfaces = {
      SimpleService.class, ExtendedService.class
  })
  Object extendedService2;

  @Inject
  @Service(interfaces = {
      SimpleService.class, ExtendedService.class
  })
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

    assert "B".equals(((SimpleService) extendedService1).check());
    assert "B".equals(((SimpleService) extendedService2).check());

    assert extendedService1 instanceof SimpleService;
    assert extendedService1 instanceof ExtendedService;

    assert extendedService2 instanceof SimpleService;
    assert extendedService2 instanceof ExtendedService;

    assert ((ExtendedService) extendedService1).encode() == "B".hashCode();
    assert ((ExtendedService) extendedService2).encode() == "B".hashCode();

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
}
