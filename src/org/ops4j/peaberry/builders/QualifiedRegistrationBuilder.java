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

import java.util.Map;

import org.ops4j.peaberry.Peaberry;

/**
 * See the service registration EDSL examples at {@link Peaberry}.
 * 
 * @author stuart.mcculloch@jayway.net (Stuart McCulloch)
 */
public interface QualifiedRegistrationBuilder<T>
    extends ScopedRegistrationBuilder<T> {

  /**
   * Apply the given RFC-2253 LDAP attributes to the service registration.
   * 
   * @param attributes service attributes
   * @return registration EDSL builder
   * 
   * @see <a href="http://www.ietf.org/rfc/rfc2253.txt">RFC-2253</a>
   */
  ScopedRegistrationBuilder<T> attributes(Map<String, ?> attributes);

  /**
   * Apply the given sequence of LDAP names to the service registration.
   * 
   * @param names sequence of name=value strings
   * @return registration EDSL builder
   * 
   * @see <a href="http://www.ietf.org/rfc/rfc2253.txt">RFC-2253</a>
   */
  ScopedRegistrationBuilder<T> attributes(String... names);
}
