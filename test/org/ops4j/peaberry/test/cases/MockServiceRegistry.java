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

package org.ops4j.peaberry.test.cases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.ops4j.peaberry.AttributeFilter;
import org.ops4j.peaberry.Export;
import org.ops4j.peaberry.Import;
import org.ops4j.peaberry.ServiceRegistry;

import com.google.inject.Singleton;

/**
 * Very simple example of a custom {@code ServiceRegistry}.
 * 
 * @author mcculls@gmail.com (Stuart McCulloch)
 */
@Singleton
@SuppressWarnings("unchecked")
final class MockServiceRegistry
    implements ServiceRegistry {

  final Map<Object, Map> registry = new HashMap<Object, Map>();

  public <T> Iterable<Import<T>> lookup(final Class<? extends T> clazz, final AttributeFilter filter) {
    return new Iterable() {
      public Iterator iterator() {

        final List<Import<T>> imports = new ArrayList<Import<T>>();

        for (final Entry<Object, Map> e : registry.entrySet()) {
          final Object service = e.getKey();

          if (clazz.isInstance(service) && (null == filter || filter.matches(e.getValue()))) {
            imports.add(new Import<T>() {

              public T get() {
                return (T) service;
              }

              public void unget() {}
            });
          }
        }

        return imports.iterator();
      }
    };
  }

  // /CLOVER:OFF
  public <S, T extends S> Export<S> export(final T service, final Map<String, ?> attributes) {
    registry.put(service, attributes);

    return new Export() {

      public void modify(final Map newAttributes) {
        registry.put(service, newAttributes);
      }

      public void remove() {
        registry.remove(service);
      }

      public Object get() {
        return service;
      }

      public void unget() {}
    };
  }
  // /CLOVER:ON
}
