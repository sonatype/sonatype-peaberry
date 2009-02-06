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

package org.ops4j.peaberry.eclipse.test;

import org.ops4j.peaberry.Import;
import org.ops4j.peaberry.eclipse.EclipseRegistry;
import org.ops4j.peaberry.eclipse.ExtensionPoint;
import org.testng.annotations.Test;

import examples.menu.Item;

/**
 * @author mcculls@gmail.com (Stuart McCulloch)
 */
@Test
public final class ExtensionTests {

  @ExtensionPoint("examples.menu")
  private static interface MenuItem {
    String getName();
  }

  public void testExtensionPoint() {
    for (final Import<MenuItem> item : new EclipseRegistry().lookup(MenuItem.class, null)) {
      System.out.println("ITEM: " + item.get().getName());
    }

    for (final Import<Item> item : new EclipseRegistry().lookup(Item.class, null)) {
      System.out.println("HINT: " + item.get().getHint());
    }
  }
}
