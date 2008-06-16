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

import org.ops4j.peaberry.Peaberry;

/**
 * See the dynamic service EDSL examples at {@link Peaberry}.
 * 
 * @author stuart.mcculloch@jayway.net (Stuart McCulloch)
 */
public interface FilteredServiceBuilder<T>
    extends ScopedServiceBuilder<T> {

  /**
   * Apply the given RFC-1960 LDAP filter to the dynamic service lookup.
   * 
   * @param filter RFC-1960 LDAP filter
   * @return service EDSL builder
   * 
   * @see <a href="http://www.ietf.org/rfc/rfc1960.txt">RFC-1960</a>
   */
  ScopedServiceBuilder<T> filter(String filter);
}
