package com.geektcp.alpha.agent.builder;//package com.geektcp.alpha.agent.builder;
//
//import javassist.*;
//import javassist.expr.ExprEditor;
//import javassist.expr.MethodCall;
//
//import java.io.ByteArrayInputStream;
//import java.lang.instrument.Instrumentation;
//import java.security.ProtectionDomain;
//
//import static com.geektcp.alpha.agent.util.LogUtil.log;
//
///**
// * @author haiyang.tang on 11.27 027 10:11:14.
// */
//public class TransformerBuilder {
//
//    private TransformerBuilder() {
//    }
//
//    public static void build(Instrumentation instrumentation){
//        instrumentation.addTransformer(new ClassTransformer());
//    }
//
//    /**
//     * @author haiyang.tang on 11.14 014 16:29:45.
//     */
//     static class ClassTransformer implements java.lang.instrument.ClassFileTransformer {
//        @Override
//        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
//                                ProtectionDomain protectionDomain, byte[] classfileBuffer)  {
//            log("start agent !");
//            if (!className.startsWith("com/casstime")) {
//                return new byte[0];
//            }
//            String newClassName = className.replace("/", ".");
//            log("Transforming: " + newClassName);
//
//            ClassPool pool = ClassPool.getDefault();
//            CtClass cl = null;
//            try {
//                pool.insertClassPath(new LoaderClassPath(loader));
//                try {
//                    cl = pool.get(newClassName);
//                } catch (NotFoundException e) {
//                    ByteArrayInputStream is = null;
//                    try {
//                        is = new ByteArrayInputStream(classfileBuffer);
//                        cl = pool.makeClass(is);
//                    } finally {
//                        if (null != is) {
//                            is.close();
//                            is = null;
//                        }
//                    }
//                }
//
//                if (cl.isInterface()) {
//                    return null;
//                }
//
//                CtMethod[] methods = cl.getDeclaredMethods();
//                for (CtMethod method : methods) {
//                    System.out.println("=====" + method.getName());
//                    //  enhance(method);
//                    enhanceMethod(method);
//                }
//                return cl.toBytecode();
//            } catch (Exception e) {
//                e.printStackTrace();
//                return null;
//            } finally {
//                if (null != cl) {
//                    cl.detach();
//                }
//            }
//        }
//
//        private void enhance(CtMethod method) throws CannotCompileException {
//            method.insertBefore("{ System.out.println(\"" + method.getLongName() + " called ...\"); }");
//            method.instrument(new ExprEditor() {
//                public void edit(MethodCall m) throws CannotCompileException {
//                    String className = m.getClassName();
//                    System.out.println("className: " + className);
//                    System.out.println("getMethodName: " + m.getMethodName());
//                    if (className.startsWith("com.casstime.dingtalk.controller")) {
//                        StringBuilder source = new StringBuilder();
//                        source.append("{long startTime = System.currentTimeMillis();");
//                        source.append("$_=$proceed($$) ;");
//                        source.append("System.out.println(\"");
//                        source.append(className).append(".").append(m.getMethodName());
//                        source.append(" cost: \" + (System.currentTimeMillis() - startTime) + \" ms\");}");
//                        m.replace(source.toString());
//                    }
//                }
//            });
//            System.out.println("agent enhanced!");
//        }
//
//        private void enhanceMethod(CtMethod method) throws Exception {
//            method.insertBefore("{ System.out.println(\"" + method.getLongName() + " called ...\"); }");
//            if (method.isEmpty()) {
//                return;
//            }
//            String methodName = method.getName();
//            if (methodName.equalsIgnoreCase("main")) {
//                return;
//            }
//
//            ExprEditor editor = new ExprEditor() {
//                @Override
//                public void edit(MethodCall methodCall) throws CannotCompileException {
//                    String className = methodCall.getClassName();
//                    System.out.println("<<<<<<<<<<<<<<");
//                    System.out.println("className: " + className);
//                    System.out.println("getMethodName: " + methodCall.getMethodName());
//                    StringBuilder source = new StringBuilder();
//                    source.append("{long startTime = System.currentTimeMillis();");
//                    source.append("$_=$proceed($$);");
//                    source.append("System.out.println(\"");
//                    source.append("[className: " + className + "]").append(".")
//                            .append("[method:" + methodCall.getMethodName() + "]");
//                    source.append(" cost: \" + (System.currentTimeMillis() - startTime) + \" ms\");}");
//                    methodCall.replace(source.toString());
//
//                    System.out.println(">>>>>>>>>>>>>");
//                }
//            };
//            method.instrument(editor);
//        }
//    }
//}
