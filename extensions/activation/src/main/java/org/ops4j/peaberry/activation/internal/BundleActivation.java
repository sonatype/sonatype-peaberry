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
package org.ops4j.peaberry.activation.internal;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.ops4j.peaberry.Export;
import org.ops4j.peaberry.Peaberry;
import org.ops4j.peaberry.activation.PeaberryActivationException;
import org.osgi.framework.Bundle;

import com.google.inject.Binding;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.Scope;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.spi.BindingScopingVisitor;

/**
 * Handles the activation of a single bundle.
 * 
 * @author rinsvind@gmail.com (Todor Boev)
 */
public class BundleActivation {
  private static final BindingScopingVisitor<Boolean> SINGLETONS =
      new BindingScopingVisitor<Boolean>() {
        public Boolean visitEagerSingleton() {
          return true;
        }

        public Boolean visitScope(final Scope scope) {
          return Scopes.SINGLETON == scope;
        }

        public Boolean visitScopeAnnotation(final Class<? extends Annotation> tag) {
          return Singleton.class == tag;
        }

        public Boolean visitNoScoping() {
          return false;
        }
      };

//  private static final BindingTargetVisitor<Object, Boolean> INSTANCES =
//      new BindingTargetVisitor<Object, Boolean>() {
//        public Boolean visit(InstanceBinding<?> arg) {
//          return true;
//        }
//
//        public Boolean visit(ProviderInstanceBinding<?> arg) {
//          return false;
//        }
//
//        public Boolean visit(ProviderKeyBinding<?> arg) {
//          return false;
//        }
//
//        public Boolean visit(LinkedKeyBinding<?> arg) {
//          return false;
//        }
//
//        public Boolean visit(ExposedBinding<?> arg) {
//          return false;
//        }
//
//        public Boolean visit(UntargettedBinding<?> arg) {
//          return false;
//        }
//
//        public Boolean visit(ConstructorBinding<?> arg) {
//          return false;
//        }
//
//        public Boolean visit(ConvertedConstantBinding<?> arg) {
//          return false;
//        }
//
//        public Boolean visit(ProviderBinding<?> arg) {
//          return false;
//        }
//      };

  private final Bundle bundle;
  private final Class<? extends Module> moduleClass;

  private List<BundleRoot<?>> roots;
  private Injector injector;

  public BundleActivation(final Bundle bundle, final Class<? extends Module> moduleClass) {
    this.bundle = bundle;
    this.moduleClass = moduleClass;
  }

  public void activate() {
    if (isActive()) {
      throw new PeaberryActivationException(Bundles.toString(bundle) + " already activated");
    }

    createInjector();
    createRoots();

    for (final BundleRoot<?> root : roots) {
      root.activate(injector);
    }
  }

  public void deactivate() {
    if (!isActive()) {
      return;
    }

    /*
     * Deactivate in reverse order. This should bring down the services before
     * we stop the active roots.
     */
    for (final ListIterator<BundleRoot<?>> iter = roots.listIterator(roots.size()); iter
        .hasPrevious();) {

      try {
        iter.previous().deactivate();
      } catch (final Exception e) {
        /*
         * FIX Accumulate these into an error list and at the end of the
         * deactivation toss an exception containing the list like Guice does
         */
        e.printStackTrace();
      }
    }

    injector = null;
    roots = null;
  }

  private boolean isActive() {
    return injector != null;
  }

  private void createInjector() {
    injector =
        Guice.createInjector(Peaberry.osgiModule(bundle.getBundleContext()), Reflection
            .create(moduleClass));
  }

  @SuppressWarnings("unchecked")
  private void createRoots() {
    final List<BundleRoot<?>> singletons = new ArrayList<BundleRoot<?>>();
    final List<BundleRoot<?>> exports = new ArrayList<BundleRoot<?>>();

    /* Scan for singletons first */
    for (final Map.Entry<Key<?>, Binding<?>> e : injector.getBindings().entrySet()) {
      final Key<?> k = e.getKey();
      final Binding<?> b = e.getValue();

      /*
       * Exports are captured before singletons. In this way exports that have
       * been redundantly marked as singleton are treated correctly rather than
       * scanned for start/stop methods etc.
       */
      if (Export.class == k.getTypeLiteral().getRawType()) {
        exports.add(new ExportBundleRoot((Key<Export<?>>) k));
      } else if (b.acceptScopingVisitor(SINGLETONS) /*|| b.acceptTargetVisitor(INSTANCES)*/) {
        singletons.add(new SingletonBundleRoot((Key<Object>) k));
      }
    }

    /*
     * To get deterministic startup we activate the singletons before we export
     * some of them to control flow from other bundles.
     */
    singletons.addAll(exports);
    roots = singletons;
  }
}
