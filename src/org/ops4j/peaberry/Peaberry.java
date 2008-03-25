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

import java.lang.annotation.Annotation;
import java.util.logging.Logger;

import org.ops4j.peaberry.internal.ServiceBindingFactory;
import org.osgi.framework.BundleContext;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.internal.GuiceCodeGen;
import com.google.inject.matcher.AbstractMatcher;
import com.google.inject.spi.Dependency;

/**
 * @author stuart.mcculloch@jayway.net (Stuart McCulloch)
 */
public final class Peaberry {

  /**
   * Simple dependency matcher that matches on the binding annotation type.
   */
  private static final class AnnotationTypeMatcher
      extends AbstractMatcher<Dependency<?>> {

    private final Class<? extends Annotation> m_annotationType;

    /**
     * @param type annotation type to match on
     */
    public AnnotationTypeMatcher(Class<? extends Annotation> type) {
      m_annotationType = type;
    }

    /**
     * {@inheritDoc}
     */
    public boolean matches(Dependency<?> dependency) {
      return m_annotationType.equals(dependency.getKey().getAnnotationType());
    }
  }

  /**
   * @param bc current bundle context
   * @return OSGi service injection rules
   */
  public static Module getOSGiModule(final BundleContext bc) {

    return new Module() {
      public void configure(Binder binder) {

        binder.bind(BundleContext.class).toInstance(bc);

        // auto-bind service dependencies and implementations
        binder.addBindingFactory(new AnnotationTypeMatcher(Service.class),
            new ServiceBindingFactory());
      }
    };
  }

  /**
   * Convenience method for constructing an OSGi enabled Guice injector
   * 
   * @param bc current bundle context
   * @param module custom Guice module
   * @return OSGi enabled injector
   */
  public static Injector getOSGiInjector(BundleContext bc, Module module) {

    // eagerly load logging subsystem, as this appears to fix class unloading
    Logger.getAnonymousLogger();

    // enable use of bundle TCCL
    GuiceCodeGen.enableTCCL();

    // TODO: bridge TCCL to find internal cglib packages
    return Guice.createInjector(getOSGiModule(bc), module);
  }
}
