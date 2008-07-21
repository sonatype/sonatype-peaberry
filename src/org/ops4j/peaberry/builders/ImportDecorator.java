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

package org.ops4j.peaberry.builders;

import org.ops4j.peaberry.Import;

/**
 * Provide runtime decoration of imported services.
 * 
 * @author stuart.mcculloch@jayway.net (Stuart McCulloch)
 */
public interface ImportDecorator<S> {

  /**
   * Decorate an imported service.
   * 
   * @param handle service handle
   * @return decorated service handle
   */
  <T extends S> Import<T> decorate(Import<T> handle);
}