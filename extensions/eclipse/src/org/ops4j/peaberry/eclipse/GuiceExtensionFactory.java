/**
 * Copyright (C) 2009 Stuart McCulloch
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

package org.ops4j.peaberry.eclipse;

import static java.util.Collections.singletonMap;
import static java.util.logging.Level.WARNING;
import static org.osgi.framework.Bundle.ACTIVE;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.logging.Logger;

import org.eclipse.core.runtime.ContributorFactoryOSGi;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;

import com.google.inject.Injector;

/**
 * An extension factory that uses {@link Injector}s published as OSGi services.
 * Bundles without published injectors will use the standard extension process.
 * <p>
 * To use this factory rename your current extension class attribute and replace
 * it with one that refers to both this class and the name of the new attribute:
 * 
 * <pre>{@literal <}extension point="org.eclipse.ui.views"{@literal >}
 *   {@literal <}view name="Message"
 *         allowMultiple="true"
 *         icon="icons/sample2.gif"
 *         class="example.View"
 *         id="example.View"{@literal >}
 *   {@literal <}/view>
 * {@literal <}/extension{@literal >}</pre>
 * becomes:
 * 
 * <pre>{@literal <}extension point="org.eclipse.ui.views"{@literal >}
 *   {@literal <}view name="Message"
 *         allowMultiple="true"
 *         icon="icons/sample2.gif"
 *         class="org.ops4j.peaberry.eclipse.GuiceExtensionFactory:key"
 *         key="example.View"
 *         id="example.View"{@literal >}
 *   {@literal <}/view>
 * {@literal <}/extension{@literal >}</pre>
 * If no name is given after the factory class it is assumed to be "id":
 * 
 * <pre>{@literal <}extension point="org.eclipse.ui.views"{@literal >}
 *   {@literal <}view name="Message"
 *         allowMultiple="true"
 *         icon="icons/sample2.gif"
 *         class="org.ops4j.peaberry.eclipse.GuiceExtensionFactory"
 *         id="example.View"{@literal >}
 *   {@literal <}/view>
 * {@literal <}/extension{@literal >}</pre>
 * The {@code publishInjector} helper method can publish an injector for one or
 * more bundles. Alternatively you can publish the services yourself: they must
 * have the {@link Injector} interface and a service property {@code bundle.id}
 * containing a long or array of longs representing the associated bundle ids.
 * 
 * @author mcculls@gmail.com (Stuart McCulloch)
 */
public final class GuiceExtensionFactory
    implements IExecutableExtension, IExecutableExtensionFactory {

  private static final Logger LOGGER = Logger.getLogger(GuiceExtensionFactory.class.getName());

  // attribute used to select bundle-scoped services
  private static final String BUNDLE_ID = "bundle.id";

  private String clazzAttributeName;
  private IConfigurationElement config;

  /**
   * Publish an {@link Injector} as a service associated with the given bundles.
   * 
   * @param context current bundle context
   * @param injector a valid injector
   * @param bundles the target bundles
   */
  public static void publishInjector(final BundleContext context, final Injector injector,
      final Bundle... bundles) {

    final long[] ids = new long[bundles.length];
    for (int i = 0; i < ids.length; i++) {
      ids[i] = bundles[i].getBundleId();
    }

    @SuppressWarnings("unchecked")
    final Dictionary props = new Hashtable(singletonMap(BUNDLE_ID, ids));
    context.registerService(Injector.class.getName(), injector, props);
  }

  public void setInitializationData(final IConfigurationElement config, final String name,
      final Object data) {

    clazzAttributeName = mapClassAttribute(data);
    this.config = config;
  }

  public Object create()
      throws CoreException {

    final Bundle bundle = ContributorFactoryOSGi.resolve(config.getContributor());

    // auto-start lazy bundles...
    if ((bundle.getState() & ACTIVE) == 0) {
      try {
        bundle.start();
      } catch (final BundleException e) {
        LOGGER.log(WARNING, "Exception starting bundle", e);
      }
    }

    final BundleContext context = bundle.getBundleContext();
    final String value = config.getAttribute(clazzAttributeName);
    if (null == value) {
      throw new RuntimeException("Missing IExecutableExtension adapter data");
    }

    final int n = value.indexOf(':');

    // separate name and data components from the attribute value
    final String clazzName = n < 0 ? value : value.substring(0, n);
    final String filter = '(' + BUNDLE_ID + '=' + bundle.getBundleId() + ')';

    ServiceReference ref = null;

    try {

      // find and use the service without explicit checks
      final Class<?> clazz = bundle.loadClass(clazzName);
      ref = context.getServiceReferences(Injector.class.getName(), filter)[0];
      return ((Injector) context.getService(ref)).getInstance(clazz);

    } catch (final NullPointerException e) { // NOPMD

      /* no injector service available so silently drop through... */

    } catch (final Exception e) {

      LOGGER.log(WARNING, "Exception injecting: " + clazzName, e);

    } finally {
      try {
        // play nice and clean-up
        context.ungetService(ref);
      } catch (final RuntimeException re) {/* already gone */} // NOPMD
    }

    LOGGER.log(WARNING, "Creating non-injected extension: " + clazzName);

    return config.createExecutableExtension(clazzAttributeName);
  }

  static String mapClassAttribute(final Object data) {
    // data is expected to refer to another attribute with the real class string
    return data instanceof String && !((String) data).isEmpty() ? (String) data : "id";
  }
}
