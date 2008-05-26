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

package org.ops4j.peaberry.test.osgi;

import static org.ops4j.peaberry.Peaberry.osgiModule;
import static org.ops4j.peaberry.Peaberry.service;
import static org.ops4j.peaberry.util.Attributes.attributes;

import org.ops4j.peaberry.Service;
import org.ops4j.peaberry.ServiceUnavailableException;
import org.ops4j.peaberry.ServiceWatcher.Handle;
import org.osgi.framework.BundleContext;
import org.testng.annotations.Test;

import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

/**
 * @author stuart.mcculloch@jayway.net (Stuart McCulloch)
 */
@Test(testName = "ServiceHandleTests", suiteName = "OSGi")
public class ServiceHandleTests
    extends OSGiServiceTester {

  protected interface WordService {
    String getWord();
  }

  protected static class WordServiceImplA
      implements WordService {

    public String getWord() {
      return "A";
    }
  }

  protected static class WordServiceImplB
      implements WordService {

    public String getWord() {
      return "B";
    }
  }

  @Inject
  @Named("A")
  @Service("word=A")
  Handle<WordService> producerA;

  @Inject
  @Named("B")
  @Service("word=B")
  Handle<WordService> producerB;

  @Inject
  @Named("one")
  @Service("word=A")
  WordService consumerA;

  @Inject
  @Named("two")
  @Service("word=B")
  WordService consumerB;

  @Test(enabled = false)
  public static void setup(Binder binder, BundleContext bundleContext) {

    binder.install(osgiModule(bundleContext));

    binder.bind(WordService.class).annotatedWith(Names.named("A")).to(
        WordServiceImplA.class);
    binder.bind(WordService.class).annotatedWith(Names.named("B")).to(
        WordServiceImplB.class);
  }

  private void checkWord(String word, String result) {
    assert word.equals(result) : "Expected " + word + ", got " + result;
  }

  public void testWiring() {

    checkWord("A", consumerA.getWord());
    checkWord("B", consumerB.getWord());

    checkWord("A", producerA.get().getWord());
    checkWord("B", producerB.get().getWord());

    producerA.modify(attributes(service("word=B")));
    producerB.modify(attributes(service("word=A")));

    checkWord("B", consumerA.getWord());
    checkWord("A", consumerB.getWord());

    producerA.remove();
    producerB.remove();

    try {
      consumerA.getWord();
      assert false : "No service expected";
    } catch (ServiceUnavailableException e) {}

    try {
      consumerB.getWord();
      assert false : "No service expected";
    } catch (ServiceUnavailableException e) {}
  }
}
