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

package org.ops4j.peaberry.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

/**
 * Collection of utility methods for dealing with service attributes.
 * 
 * @author stuart.mcculloch@jayway.net (Stuart McCulloch)
 */
public final class Attributes {

  // instances not allowed
  private Attributes() {}

  /**
   * Converts a Properties object to a type-safe attribute map.
   * 
   * @param properties service properties
   * @return type-safe map of service attributes
   */
  public static Map<String, ?> attributes(Properties properties) {

    Map<String, Object> attributes = new HashMap<String, Object>();

    /*
     * Sigh, Properties is a really messed-up class... in Java5 there is only
     * one method that looks up all keys *including default keys* and that can
     * throw a ClassCastException if there happen to be any non-String keys.
     * (Java6 adds stringPropertyNames, but we're currently targetting Java5)
     */
    try {
      for (Enumeration<?> e = properties.propertyNames(); e.hasMoreElements();) {
        final String key = (String) e.nextElement();
        attributes.put(key, properties.getProperty(key));
      }
    } catch (ClassCastException e) {}

    // now add non-String values that have String keys
    for (Entry<?, ?> entry : properties.entrySet()) {
      final Object key = entry.getKey();
      if (key instanceof String) {
        attributes.put((String) key, entry.getValue());
      }
    }

    return attributes;
  }
}
