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

import java.util.Map;

import org.ops4j.peaberry.ServiceRegistry;
import org.ops4j.peaberry.ServiceWatcher.Handle;
import org.ops4j.peaberry.builders.QualifiedRegistrationBuilder;
import org.ops4j.peaberry.builders.RegistrationProxyBuilder;
import org.ops4j.peaberry.builders.ScopedRegistrationBuilder;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;

/**
 * @author stuart.mcculloch@jayway.net (Stuart McCulloch)
 */
public final class QualifiedRegistrationBuilderImpl<T>
    implements QualifiedRegistrationBuilder<T> {

  final Key<? extends T> implementationKey;

  Map<String, ?> attributes = null;

  Key<? extends ServiceRegistry> registryKey = Key.get(ServiceRegistry.class);

  public QualifiedRegistrationBuilderImpl(final Key<? extends T> key) {
    this.implementationKey = key;
  }

  public ScopedRegistrationBuilder<T> attributes(Map<String, ?> customAttributes) {
    attributes = customAttributes;
    return this;
  }

  public RegistrationProxyBuilder<T> registry(Key<? extends ServiceRegistry> key) {
    registryKey = key;
    return this;
  }

  public Provider<Handle<T>> handle() {
    return new Provider<Handle<T>>() {

      @Inject
      Injector injector;

      public Handle<T> get() {
        ServiceRegistry registry = injector.getInstance(registryKey);
        T serviceImplementation = injector.getInstance(implementationKey);

        return registry.add(serviceImplementation, attributes);
      }
    };
  }
}
