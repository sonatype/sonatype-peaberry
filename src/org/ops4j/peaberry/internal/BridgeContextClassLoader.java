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

import java.util.concurrent.Callable;

import com.google.inject.Module;

/**
 * @author stuart.mcculloch@jayway.net (Stuart McCulloch)
 */
public final class BridgeContextClassLoader
    extends ClassLoader {

  private static final String CGLIB_PACKAGE = "com.google.inject.cglib";

  private static final ClassLoader PEABERRY_LOADER =
      BridgeContextClassLoader.class.getClassLoader();

  public BridgeContextClassLoader(Module module) {
    super(module.getClass().getClassLoader());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Class<?> loadClass(final String name, final boolean resolve)
      throws ClassNotFoundException {

    // delegate internal CGLIB requests to Peaberry bundle classloader
    if (PEABERRY_LOADER != null && name.startsWith(CGLIB_PACKAGE)) {
      final Class<?> clazz = PEABERRY_LOADER.loadClass(name);
      if (resolve) {
        super.resolveClass(clazz);
      }
      return clazz;
    }

    // default to standard OSGi delegation
    return super.loadClass(name, resolve);
  }

  public <T> T bridge(Callable<T> action)
      throws Exception {

    Thread currentThread = Thread.currentThread();
    ClassLoader oldTCCL = currentThread.getContextClassLoader();
    currentThread.setContextClassLoader(this);

    try {
      return action.call();
    } finally {
      currentThread.setContextClassLoader(oldTCCL);
    }
  }
}
