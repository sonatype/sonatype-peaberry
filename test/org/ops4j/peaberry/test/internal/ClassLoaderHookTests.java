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

package org.ops4j.peaberry.test.internal;

import static com.google.inject.internal.GuiceCodeGen.getClassLoader;

import org.testng.annotations.Test;

/**
 * Additional classloader hook tests for code that isn't exercised in OSGi.
 * 
 * @author stuart.mcculloch@jayway.net (Stuart McCulloch)
 */
@Test(testName = "ClassLoaderHookTests", suiteName = "Internal")
public final class ClassLoaderHookTests {

  public void standardDelegation() {

    assert getClassLoader(String.class) == String.class.getClassLoader();
    assert getClassLoader(getClass()) == getClass().getClassLoader();

    nonDelegatingContainer(); // should still work with standard delegation

    assert getClassLoader(String.class) == String.class.getClassLoader();
    assert getClassLoader(getClass()) == getClass().getClassLoader();
  }
}
