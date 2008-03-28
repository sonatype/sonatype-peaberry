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

package org.ops4j.peaberry;

import static org.ops4j.peaberry.internal.ServiceProviderFactory.resolve;

import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;

/**
 * Provides leased service scoping.
 * 
 * @author stuart.mcculloch@jayway.net (Stuart McCulloch)
 */
public class LeasedScope
    implements Scope {

  final long leaseTimeInSeconds;

  public LeasedScope(long leaseTimeInSeconds) {
    this.leaseTimeInSeconds = leaseTimeInSeconds;
  }

  public <T> Provider<T> scope(Key<T> key, final Provider<T> unscoped) {
    return new Provider<T>() {

      private volatile long expireTimeInMillis;
      private volatile T instance;

      public T get() {
        if (expireTimeInMillis < System.currentTimeMillis()) {
          synchronized (LeasedScope.class) {
            if (expireTimeInMillis < System.currentTimeMillis()) {

              expireTimeInMillis =
                  System.currentTimeMillis() + leaseTimeInSeconds * 1000;

              instance = resolve(unscoped);
            }
          }
        }
        return instance;
      }

      @Override
      public String toString() {
        return String.format("%s[%s]", unscoped, LeasedScope.this);
      }

    };
  }

  @Override
  public String toString() {
    return "LEASED_SERVICE(" + leaseTimeInSeconds + "s)";
  }
}
