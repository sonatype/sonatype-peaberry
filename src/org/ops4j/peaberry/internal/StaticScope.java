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

import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;
import com.google.inject.Scopes;

/**
 * Provides static service scoping.
 * 
 * @author stuart.mcculloch@jayway.net (Stuart McCulloch)
 */
public class StaticScope
    implements Scope {

  public <T> Provider<T> scope(Key<T> key, final Provider<T> dynamic) {
    if (dynamic instanceof ServiceProvider) {
      return new Provider<T>() {

        private volatile T instance;

        public T get() {
          if (instance == null) {
            synchronized (StaticScope.class) {
              if (instance == null) {
                instance = ((ServiceProvider<T>) dynamic).resolve();
              }
            }
          }
          return instance;
        }

        @Override
        public String toString() {
          return String.format("%s[%s]", dynamic, StaticScope.this);
        }

      };
    } else {
      return Scopes.SINGLETON.scope(key, dynamic);
    }
  }

  @Override
  public String toString() {
    return "STATIC";
  }
}
