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

package org.ops4j.peaberry.osgi;

import org.ops4j.peaberry.ServiceRegistry;
import org.osgi.framework.BundleContext;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.Scope;

/**
 * OSGi specific Guice binding {@link Module}.
 * 
 * @author mcculls@gmail.com (Stuart McCulloch)
 */
public final class OSGiModule
    extends AbstractModule {

  private final BundleContext bundleContext;

  public OSGiModule(final BundleContext bundleContext) {
    this.bundleContext = bundleContext;
  }

  @Override
  protected void configure() {
    bind(BundleContext.class).toInstance(bundleContext);
    final Scope bundleScope = new BundleScope(bundleContext);

    // need indirect binding so registry is registered as caching
    bind(ServiceRegistry.class).to(CachingServiceRegistry.class);
    bind(CachingServiceRegistry.class).in(bundleScope);
  }

  @Override
  public String toString() {
    return String.format("OSGiModule[%s]", bundleContext.getBundle());
  }

  @Override
  public int hashCode() {
    return bundleContext.hashCode();
  }

  @Override
  public boolean equals(final Object rhs) {
    if (rhs instanceof OSGiModule) {
      return bundleContext.equals(((OSGiModule) rhs).bundleContext);
    }
    return false;
  }
}
