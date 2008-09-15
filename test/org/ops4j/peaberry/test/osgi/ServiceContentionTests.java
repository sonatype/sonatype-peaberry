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

import static org.ops4j.peaberry.Peaberry.registration;
import static org.ops4j.peaberry.Peaberry.service;
import static org.ops4j.peaberry.util.TypeLiterals.export;
import static org.ops4j.peaberry.util.TypeLiterals.iterable;
import static org.osgi.framework.Constants.SERVICE_RANKING;

import java.util.Properties;

import org.ops4j.peaberry.Export;
import org.ops4j.peaberry.ServiceUnavailableException;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.testng.annotations.Test;

import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Key;

/**
 * Test service contention.
 * 
 * @author mcculls@gmail.com (Stuart McCulloch)
 */
@Test(testName = "ServiceContentionTests", suiteName = "OSGi")
public final class ServiceContentionTests
    extends OSGiServiceTester {

  @Test(enabled = false)
  public static void configure(final Binder binder) {

    binder.bind(export(DummyService.class)).toProvider(
        registration(Key.get(DummyServiceImpl.class)).export());

    binder.bind(DummyService.class).toProvider(service(DummyService.class).single());

    binder.bind(iterable(RankService.class)).toProvider(service(RankService.class).multiple());
  }

  protected static interface DummyService {
    String test();
  }

  static class DummyServiceImpl
      implements DummyService {

    public String test() {
      try {
        Thread.sleep(100);
      } catch (final InterruptedException e) {}

      return "DONE";
    }
  }

  @Inject
  DummyService importedService;

  @Inject
  Export<DummyService> exportedService;

  public void testContention() {

    final Thread[] threads = new Thread[42];

    for (int i = 0; i < threads.length; i++) {
      threads[i] = new Thread(new Runnable() {
        public void run() {
          assert "DONE".equals(importedService.test());
        }
      });
    }

    for (final Thread t : threads) {
      t.start();
    }

    for (final Thread t : threads) {
      try {
        t.join();
      } catch (final InterruptedException e) {}
    }

    assert "DONE".equals(importedService.test());

    exportedService.remove();

    try {
      importedService.test();
      assert false : "No service expected";
    } catch (final ServiceUnavailableException e) {}
  }

  protected static interface RankService {
    int rank();
  }

  @Inject
  BundleContext bundleContext;

  @Inject
  Iterable<RankService> rankings;

  public void testIntegrity() {

    final Thread[] threads = new Thread[42];

    for (int i = 0; i < threads.length; i++) {
      threads[i] = new Thread(new Runnable() {
        public void run() {

          try {
            Thread.sleep(1000 + (int) (10 * Math.random()));
          } catch (final InterruptedException e1) {}

          final int rank = (int) (1000 * Math.random());

          Properties props = new Properties();
          props.put(SERVICE_RANKING, rank);

          ServiceRegistration registration =
              bundleContext.registerService(RankService.class.getName(), new RankService() {
                public int rank() {
                  return rank;
                }
              }, props);

          try {
            Thread.sleep((int) (10 * Math.random()));
          } catch (final InterruptedException e2) {}

          registration.unregister();
        }
      });
    }

    for (final Thread t : threads) {
      t.start();
    }

    boolean started = false;

    int prevRank;
    do {
      prevRank = Integer.MAX_VALUE;
      for (RankService next : rankings) {
        try {
          assert prevRank >= next.rank() : "Expected " + prevRank + " >= " + next.rank();
          prevRank = next.rank();
        } catch (final ServiceUnavailableException e) {}
        started = true;
      }
    } while (!started || prevRank < Integer.MAX_VALUE);

    for (final Thread t : threads) {
      try {
        t.join();
      } catch (final InterruptedException e) {}
    }
  }
}
