package com.geektcp.alpha.agent;

import com.geektcp.alpha.agent.example.advisor.ExceptionAdvisor;
import com.geektcp.alpha.agent.example.advisor.LoggerAdvisor;
import com.geektcp.alpha.agent.service.impl.TestServiceImpl;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.annotation.Annotation;

/**
 * @author haiyang.tang on 12.03 003 15:07:50.
 */
public class ByteBuddyTest {


    private static final String DEFAULT_INTERFACE = "com.casstime.webforagent.controller.ThyControllerInterface";

    public static void main(String[] args) throws Exception {
//        testByteBuddy();
//        testException();
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);

    }



    private static void testClassForName() {
        try {
            Class cls = Class.forName("com.casstime.agent.annotation.GetMapping");

            System.out.println(cls.getName());
            System.out.println(cls.getSimpleName());
            System.out.println(cls.getAnnotations());
            System.out.println(cls.getFields());
            Class cls3 = Class.forName("com.casstime.agent.annotation.GetMapping");
            Annotation[] annotations = cls.getAnnotations();
            System.out.println(annotations);

            ElementMatcher elementMatcher = ElementMatchers.isAnnotatedWith(cls3);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void testByteBuddy() {
        try {
            TestServiceImpl testServiceImpl = new ByteBuddy()
                    .subclass(TestServiceImpl.class)
                    .method(ElementMatchers.any())
                    .intercept(Advice.to(LoggerAdvisor.class))
                    .make()
                    .load(TestServiceImpl.class.getClassLoader())
                    .getLoaded()
                    .newInstance();
            testServiceImpl.bar(123);
            testServiceImpl.foo(456);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testException() {
        try {
            TestServiceImpl testServiceImpl = new ByteBuddy()
                    .subclass(TestServiceImpl.class)
                    .method(ElementMatchers.any())
                    .intercept(Advice.to(ExceptionAdvisor.class))
                    .make()
                    .load(TestServiceImpl.class.getClassLoader())
                    .getLoaded()
                    .newInstance();

            testServiceImpl.exception(123);
            testServiceImpl.bar(422);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void testAnnotation() {
        try {
            Class cls = Class.forName("com.casstime.agent.annotation.GetMapping");
            AnnotationDescription annotationDescription = (AnnotationDescription) cls.newInstance();
            AnnotationDescription annotations = (AnnotationDescription) cls.getAnnotation(AnnotationDescription.class);
            AnnotationDescription tt = null;
//            ElementMatchers.annotationType();
            TestServiceImpl testServiceImpl = new ByteBuddy()
                    .subclass(TestServiceImpl.class)
                    .method(ElementMatchers.isAnnotatedWith(ElementMatchers.isAnnotation()))
//                    .method(ElementMatchers.declaresAnnotation)
                    .intercept(Advice.to(LoggerAdvisor.class))
                    .make()
                    .load(TestServiceImpl.class.getClassLoader())
                    .getLoaded()
                    .newInstance();
            testServiceImpl.bar(123);
            testServiceImpl.foo(456);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
