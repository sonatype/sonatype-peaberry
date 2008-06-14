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

import static com.google.inject.internal.base.Objects.nonNull;
import static org.ops4j.peaberry.internal.ServiceFilterFactory.getServiceFilter;
import static org.ops4j.peaberry.internal.ServiceProxyFactory.getMultiServiceProxy;
import static org.ops4j.peaberry.internal.ServiceProxyFactory.getUnaryServiceProxy;
import static org.ops4j.peaberry.internal.ServiceTypes.expectsHandle;
import static org.ops4j.peaberry.internal.ServiceTypes.expectsSequence;
import static org.ops4j.peaberry.internal.ServiceTypes.getServiceClass;
import static org.ops4j.peaberry.internal.ServiceTypes.getServiceType;
import static org.ops4j.peaberry.util.Attributes.attributes;

import java.lang.reflect.Type;
import java.util.Map;

import org.ops4j.peaberry.Service;
import org.ops4j.peaberry.ServiceRegistry;
import org.ops4j.peaberry.ServiceWatcher.Handle;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;

/**
 * Provide dynamic {@link Service} {@link Provider}s.
 * 
 * @author stuart.mcculloch@jayway.net (Stuart McCulloch)
 */
public final class ServiceProviderFactory {

  private static final String SERVICE_PROVIDER_DESCRIPTION = "%s lookup(%s,\"%s\") from %s";

  private static final String HANDLE_PROVIDER_DESCRIPTION = "%s register(%s,\"%s\") with %s";

  // utility: instances not allowed
  private ServiceProviderFactory() {}

  /**
   * Create a new {@link Service} {@link Provider} for the target member.
   * 
   * @param registry dynamic service registry
   * @param key key of the member being injected
   * 
   * @return {@link Service} {@link Provider} for the target
   */
  public static <T> Provider<T> getServiceProvider(final ServiceRegistry registry, final Key<? extends T> key) {

    nonNull(registry, "service registry");
    nonNull(key, "injection key");

    final Type memberType = key.getTypeLiteral().getType();
    final Service spec = (Service) key.getAnnotation();

    if (expectsHandle(memberType)) {

      final Type serviceType = getServiceType(memberType);
      final Key<?> serviceKey = Key.get(serviceType, spec);

      return getHandleProvider(registry, serviceKey, attributes(spec));
    }

    ServiceRegistry leasedRegistry;
    if (null != spec && spec.lease().value() != 0) {
      leasedRegistry = new LeasedServiceRegistry(registry, spec.lease());
    } else {
      leasedRegistry = registry;
    }

    final Class<?> serviceClass = getServiceClass(memberType);
    final String filter = getServiceFilter(spec, serviceClass);

    if (expectsSequence(memberType)) {
      return getMultiProvider(spec, leasedRegistry, serviceClass, filter);
    }

    return getUnaryProvider(spec, leasedRegistry, serviceClass, filter);
  }

  /**
   * Create a new unary {@link Service} {@link Provider} for the target.
   * 
   * @param spec custom service specification
   * @param registry dynamic service registry
   * @param clazz expected service class
   * @param filter RFC-1960 LDAP filter
   * 
   * @return unary {@link Service} {@link Provider} for the target
   */
  @SuppressWarnings("unchecked")
  private static <T> Provider<T> getUnaryProvider(final Service spec, final ServiceRegistry registry,
      final Class clazz, final String filter) {

    return new Provider() {
      public Object get() {
        return getUnaryServiceProxy(spec, registry, clazz, filter);
      }

      @Override
      public String toString() {
        return String.format(SERVICE_PROVIDER_DESCRIPTION, "UnaryServiceProvider", clazz, filter, registry);
      }
    };
  }

  /**
   * Create a new multiple {@link Service} {@link Provider} for the target.
   * 
   * @param spec custom service specification
   * @param registry dynamic service registry
   * @param clazz expected service class
   * @param filter RFC-1960 LDAP filter
   * 
   * @return multiple {@link Service} {@link Provider} for the target
   */
  @SuppressWarnings("unchecked")
  private static <T> Provider<T> getMultiProvider(final Service spec, final ServiceRegistry registry,
      final Class clazz, final String filter) {

    return new Provider() {
      public Iterable get() {
        return getMultiServiceProxy(spec, registry, clazz, filter);
      }

      @Override
      public String toString() {
        return String.format(SERVICE_PROVIDER_DESCRIPTION, "MultiServiceProvider", clazz, filter, registry);
      }
    };
  }

  /**
   * Create a new registering {@link Service} {@link Provider} for the target.
   * 
   * @param registry dynamic service registry
   * @param serviceKey service binding key
   * @param attributes service attributes
   * 
   * @return registering {@link Service} {@link Provider} for the target
   */
  @SuppressWarnings("unchecked")
  private static <T> Provider<T> getHandleProvider(final ServiceRegistry registry, final Key serviceKey,
      final Map<String, ?> attributes) {

    return new Provider() {

      @Inject
      Injector injector;

      public Handle get() {
        return registry.add(injector.getInstance(serviceKey), attributes);
      }

      @Override
      public String toString() {
        return String.format(HANDLE_PROVIDER_DESCRIPTION, "ServiceHandleProvider", serviceKey, attributes, registry);
      }
    };
  }
}
