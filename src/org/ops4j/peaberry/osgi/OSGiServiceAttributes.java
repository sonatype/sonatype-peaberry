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

package org.ops4j.peaberry.osgi;

import static java.util.Collections.addAll;
import static java.util.Collections.unmodifiableSet;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.osgi.framework.ServiceReference;

/**
 * Service attributes adapter backed by an OSGi @{code ServiceReference}.
 * 
 * @author mcculls@gmail.com (Stuart McCulloch)
 */
final class OSGiServiceAttributes
    extends AbstractMap<String, Object> {

  // OSGi service attributes use case-less keys, so we need to honour this...
  private static final Comparator<String> IGNORE_CASE = new Comparator<String>() {
    public int compare(final String lhs, final String rhs) {
      return lhs.compareToIgnoreCase(rhs);
    }
  };

  private final ServiceReference ref;

  public OSGiServiceAttributes(final ServiceReference ref) {
    this.ref = ref;
  }

  @Override
  public Object get(final Object key) {
    return ref.getProperty((String) key);
  }

  @Override
  public boolean containsKey(final Object key) {
    return keySet().contains(key);
  }

  @Override
  public Set<String> keySet() {
    // service reference is mutable, so build new set each time
    final Set<String> ks = new TreeSet<String>(IGNORE_CASE);
    addAll(ks, ref.getPropertyKeys());
    return unmodifiableSet(ks);
  }

  // can safely cache entry set, as it has no state
  private volatile Set<Entry<String, Object>> entrySet;

  @Override
  public Set<Entry<String, Object>> entrySet() {
    if (null == entrySet) {
      entrySet = new AbstractSet<Entry<String, Object>>() {

        @Override
        public Iterator<Entry<String, Object>> iterator() {
          return new Iterator<Entry<String, Object>>() {
            private final Iterator<String> i = keySet().iterator();

            public boolean hasNext() {
              return i.hasNext();
            }

            public Entry<String, Object> next() {
              final String key = i.next();
              return new ImmutableAttribute(key, get(key));
            }

            public void remove() {
              throw new UnsupportedOperationException();
            }
          };
        }

        @Override
        public int size() {
          return keySet().size();
        }
      };
    }
    return entrySet;
  }
}
