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

import static java.lang.reflect.Modifier.ABSTRACT;
import static java.lang.reflect.Modifier.FINAL;
import static java.lang.reflect.Modifier.NATIVE;
import static java.lang.reflect.Modifier.PRIVATE;
import static java.lang.reflect.Modifier.PUBLIC;
import static java.lang.reflect.Modifier.STATIC;
import static java.lang.reflect.Modifier.SYNCHRONIZED;
import static java.util.Collections.addAll;
import static org.objectweb.asm.ClassWriter.COMPUTE_MAXS;
import static org.objectweb.asm.Opcodes.ACONST_NULL;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ASTORE;
import static org.objectweb.asm.Opcodes.ATHROW;
import static org.objectweb.asm.Opcodes.CHECKCAST;
import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.GETFIELD;
import static org.objectweb.asm.Opcodes.IFNONNULL;
import static org.objectweb.asm.Opcodes.ILOAD;
import static org.objectweb.asm.Opcodes.INVOKEINTERFACE;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.IRETURN;
import static org.objectweb.asm.Opcodes.ISTORE;
import static org.objectweb.asm.Opcodes.NEW;
import static org.objectweb.asm.Opcodes.PUTFIELD;
import static org.objectweb.asm.Opcodes.RETURN;
import static org.objectweb.asm.Opcodes.V1_5;
import static org.objectweb.asm.Type.VOID;
import static org.objectweb.asm.Type.getArgumentTypes;
import static org.objectweb.asm.Type.getDescriptor;
import static org.objectweb.asm.Type.getInternalName;
import static org.objectweb.asm.Type.getMethodDescriptor;
import static org.objectweb.asm.Type.getReturnType;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.ops4j.peaberry.Import;
import org.ops4j.peaberry.ServiceUnavailableException;

/**
 * Around-advice glue, specifically optimized for the {@link Import} concept.
 * 
 * @author mcculls@gmail.com (Stuart McCulloch)
 */
final class ImportGlue {

  // instances not allowed
  private ImportGlue() {}

  private static final String UNAVAILABLE_NAME = getInternalName(ServiceUnavailableException.class);
  private static final String EXCEPTION_NAME = getInternalName(Exception.class);

  private static final String IMPORT_NAME = getInternalName(Import.class);
  private static final String OBJECT_NAME = getInternalName(Object.class);

  private static final String VOID_DESC = "()V";

  private static final String IMPORT_DESC = getDescriptor(Import.class);
  private static final String OBJECT_DESC = getDescriptor(Object.class);

  private static final Method[] OBJECT_METHODS = Object.class.getMethods();

  private static final String PROXY_SUFFIX = "$pbryglu";
  private static final String PROXY_HANDLE = "__pbry__";

  static String getProxyName(final String clazzName) {
    final StringBuilder tmpName = new StringBuilder();

    // support proxy of java.* interfaces by changing the package space
    if (clazzName.startsWith("java.") || clazzName.startsWith("java/")) {
      tmpName.append('$');
    }

    return tmpName.append(clazzName).append(PROXY_SUFFIX).toString();
  }

  static String getClazzName(final String proxyName) {
    final int head = '$' == proxyName.charAt(0) ? 1 : 0;
    final int tail = proxyName.lastIndexOf(PROXY_SUFFIX);

    if (tail > 0) {
      return proxyName.substring(head, tail);
    }

    return proxyName;
  }

  static byte[] generateProxy(final Class<?> clazz) {

    final String clazzName = getInternalName(clazz);
    final String proxyName = getProxyName(clazzName);

    final String superName;
    final String[] interfaceNames;

    if (clazz.isInterface()) {
      superName = OBJECT_NAME;
      interfaceNames = new String[]{clazzName};
    } else {
      superName = clazzName;
      interfaceNames = getInternalNames(clazz.getInterfaces());
    }

    final ClassWriter cw = new ClassWriter(COMPUTE_MAXS);

    cw.visit(V1_5, PUBLIC | FINAL, proxyName, null, superName, interfaceNames);

    // single Import<T> constructor
    init(cw, superName, proxyName);

    // for the moment only proxy the public API...
    final Method[] publicAPI = clazz.getMethods();
    final List<Method> methods = new ArrayList<Method>(publicAPI.length + OBJECT_METHODS.length);
    addAll(methods, publicAPI);

    if (clazz.isInterface()) {
      // patch in any missing Object methods...
      for (final Method m : OBJECT_METHODS) {
        if (missingMethod(publicAPI, m)) {
          methods.add(m);
        }
      }
    }

    for (final Method m : methods) {
      // we cannot proxy any static or final methods
      if ((m.getModifiers() & (STATIC | FINAL)) == 0) {
        wrap(cw, proxyName, m);
      }
    }

    cw.visitEnd();

    return cw.toByteArray();
  }

  private static void init(final ClassWriter cw, final String superName, final String proxyName) {
    cw.visitField(PRIVATE | FINAL, PROXY_HANDLE, IMPORT_DESC, null, null).visitEnd();

    final MethodVisitor v = cw.visitMethod(PUBLIC, "<init>", '(' + IMPORT_DESC + ")V", null, null);

    v.visitCode();

    // store Import<T> handle
    v.visitVarInsn(ALOAD, 0);
    v.visitInsn(DUP);
    v.visitVarInsn(ALOAD, 1);
    v.visitFieldInsn(PUTFIELD, proxyName, PROXY_HANDLE, IMPORT_DESC);
    v.visitMethodInsn(INVOKESPECIAL, superName, "<init>", VOID_DESC);
    v.visitInsn(RETURN);

    v.visitMaxs(0, 0);
    v.visitEnd();
  }

  @SuppressWarnings("PMD.ExcessiveMethodLength")
  private static void wrap(final ClassWriter cw, final String proxyName, final Method method) {

    final String methodName = method.getName();

    final String descriptor = getMethodDescriptor(method);
    final String[] exceptions = getInternalNames(method.getExceptionTypes());

    // simple delegating proxy, so don't need synchronization on wrapper method
    final int modifiers = method.getModifiers() & ~(ABSTRACT | NATIVE | SYNCHRONIZED);

    final MethodVisitor v = cw.visitMethod(modifiers, methodName, descriptor, null, exceptions);

    final Label start = new Label();
    final Label invoke = new Label();
    final Label end = new Label();

    final Label ungetR = new Label();
    final Label finalR = new Label();
    final Label catchX = new Label();
    final Label ungetX = new Label();
    final Label finalX = new Label();

    v.visitCode();

    // support try{ get(); } finally { unget(); } model
    v.visitTryCatchBlock(start, ungetR, catchX, null);
    v.visitTryCatchBlock(ungetR, finalR, finalR, EXCEPTION_NAME);
    v.visitTryCatchBlock(ungetX, finalX, finalX, EXCEPTION_NAME);

    // store handle as "this"
    v.visitVarInsn(ALOAD, 0);
    v.visitFieldInsn(GETFIELD, proxyName, PROXY_HANDLE, IMPORT_DESC);
    v.visitInsn(DUP);
    v.visitVarInsn(ASTORE, 0);

    v.visitLabel(start);

    // dereference handle to get actual service instance
    v.visitMethodInsn(INVOKEINTERFACE, IMPORT_NAME, "get", "()" + OBJECT_DESC);
    v.visitInsn(DUP);

    // null => ServiceUnavailableException
    v.visitJumpInsn(IFNONNULL, invoke);
    v.visitTypeInsn(NEW, UNAVAILABLE_NAME);
    v.visitInsn(DUP);
    v.visitMethodInsn(INVOKESPECIAL, UNAVAILABLE_NAME, "<init>", "()V");
    v.visitInsn(ATHROW);

    v.visitLabel(invoke);

    final Class<?> clazz = method.getDeclaringClass();
    final String subjectName = getInternalName(clazz);

    if (!clazz.isInterface()) {
      v.visitTypeInsn(CHECKCAST, subjectName);
    }

    int i = 1;
    for (final Type t : getArgumentTypes(method)) {
      v.visitVarInsn(t.getOpcode(ILOAD), i);
      i = i + t.getSize();
    }

    // delegate to real method
    if (clazz.isInterface()) {
      v.visitMethodInsn(INVOKEINTERFACE, subjectName, methodName, descriptor);
    } else {
      v.visitMethodInsn(INVOKEVIRTUAL, subjectName, methodName, descriptor);
    }

    final Type returnType = getReturnType(method);

    if (VOID != returnType.getSort()) {
      v.visitVarInsn(returnType.getOpcode(ISTORE), 1);
    }

    v.visitLabel(ungetR);

    // unget on return
    v.visitVarInsn(ALOAD, 0);
    v.visitMethodInsn(INVOKEINTERFACE, IMPORT_NAME, "unget", VOID_DESC);
    v.visitInsn(ACONST_NULL);

    v.visitLabel(finalR);

    if (VOID != returnType.getSort()) {
      v.visitVarInsn(returnType.getOpcode(ILOAD), 1);
    }

    v.visitInsn(returnType.getOpcode(IRETURN));

    // cache initial exception
    v.visitLabel(catchX);
    v.visitVarInsn(ASTORE, 1);

    v.visitLabel(ungetX);

    // unget on exception
    v.visitVarInsn(ALOAD, 0);
    v.visitMethodInsn(INVOKEINTERFACE, IMPORT_NAME, "unget", VOID_DESC);
    v.visitInsn(ACONST_NULL);

    v.visitLabel(finalX);

    // restore initial exception
    v.visitVarInsn(ALOAD, 1);
    v.visitInsn(ATHROW);

    v.visitLabel(end);
    v.visitMaxs(0, 0);
    v.visitEnd();
  }

  private static String[] getInternalNames(final Class<?>... clazzes) {
    final String[] names = new String[clazzes.length];
    for (int i = 0; i < names.length; i++) {
      names[i] = getInternalName(clazzes[i]);
    }
    return names;
  }

  private static boolean missingMethod(final Method[] methods, final Method method) {
    final String sig = Arrays.toString(method.getParameterTypes());
    final String name = method.getName();

    for (final Method m : methods) {
      // just filter on method name and signature, ignore the declaring class...
      if (name.equals(m.getName()) && sig.equals(Arrays.toString(m.getParameterTypes()))) {
        return false;
      }
    }
    return true;
  }
}
