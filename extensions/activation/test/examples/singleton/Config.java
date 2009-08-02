/**
 * Copyright (C) 2009 Todor Boev
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

package examples.singleton;

import static com.google.inject.matcher.Matchers.*;

import org.ops4j.peaberry.activation.Start;
import org.ops4j.peaberry.activation.Stop;
import org.ops4j.peaberry.activation.invocations.InvocationLogModule;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * @author rinsvind@gmail.com (Todor Boev)
 * 
 */
public class Config
    extends AbstractModule {

  @Override
  protected void configure() {
    bind(SingletonRoot.class).in(Singleton.class);

    install(new InvocationLogModule(
      subclassesOf(SingletonRoot.class), annotatedWith(Start.class).or(annotatedWith(Stop.class))));
  }
}
