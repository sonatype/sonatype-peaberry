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

import org.ops4j.peaberry.Import;

/**
 * A static {@link Import} based on a fixed instance and attribute map.
 * 
 * @author mcculls@gmail.com (Stuart McCulloch)
 */
final class StaticImport<T>
    implements Import<T> {

  private final T instance;
  private final Map<String, ?> attributes;

  public StaticImport(final T instance, final Map<String, ?> attributes) {
    this.instance = instance;
    this.attributes = attributes;
  }

  public T get() {
    return instance;
  }

  public Map<String, ?> attributes() {
    return attributes;
  }

  public void unget() {}
}
