/**
 * Copyright (C) 2009 Todor Boev
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

package examples.activation.importer.internal;

import org.ops4j.peaberry.ServiceUnavailableException;
import org.ops4j.peaberry.activation.Start;
import org.ops4j.peaberry.activation.Stop;

import com.google.inject.Inject;

import examples.activation.hello.Hello;

/**
 * @author rinsvind@gmail.com (Todor Boev)
 * 
 */
public class Greeter {
  private final Loop loop;

  @Inject
  public Greeter(final Hello hello) {
    loop = new Loop(new Runnable() {
      private long no = 0;

      public void run() {
        try {
          hello.hello("number " + no++);
        } catch (final ServiceUnavailableException sue) {
          /* Ignore - keep trying every second */
        }

        try {
          Thread.sleep(1000);
        } catch (final InterruptedException e) {
          /* Ignore */
        }
      }
    });
  }

  @Start
  public void start() {
    loop.start();
  }

  @Stop
  public void stop() {
    loop.stop();
  }
}
