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

import org.ops4j.peaberry.Service;

import com.google.inject.BindingFactory;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.spi.Dependency;

/**
 * @author stuart.mcculloch@jayway.net (Stuart McCulloch)
 */
public final class ServiceBindingFactory
    implements BindingFactory<Object> {

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  public <T> boolean bind(Dependency<T> dependency,
      LinkedBindingBuilder<T> linkedBindingBuilder) {

    // TODO Auto-generated method stub
    Service spec = (Service) dependency.getKey().getAnnotation();
    System.out.println("AUTOBIND:"
        + ServiceQueryFactory.get(spec, dependency.getKey().getTypeLiteral()
            .getType()));

    linkedBindingBuilder.toInstance((T) new java.util.ArrayList());
    return true;
  }
}
