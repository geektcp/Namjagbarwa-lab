package com.geektcp.alpha.agent;

/**
 * @author haiyang.tang on 12.06 006 11:36:07.
 */

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import com.geektcp.alpha.agent.service.TestService;
import com.geektcp.alpha.agent.service.impl.TestServiceImpl;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.Default;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.matcher.ElementMatchers;

public class InheritedMethodTest {


    public void testInterface() throws Exception {
        DynamicType.Loaded<?> loaded = new ByteBuddy()
                .subclass(TestServiceImpl.class)
                .implement(TestService.class)
                .intercept(Advice.to(TestInterfaceAdvice.class))
                .method(ElementMatchers.named("thy"))
                .intercept(MethodDelegation.to(TestInterfaceInterceptor.class))
                .make()
                .load(TestService.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER);

        TestService instance = (TestService)loaded.getLoaded().getDeclaredConstructor().newInstance();
//        instance.bar(123);
//        instance.foo(456);
        System.out.println(instance.thy());
    }

    public void testNoDefaultInterface() throws Exception {
        DynamicType.Loaded<?> loaded = new ByteBuddy()
                .subclass(Object.class)
                .implement(DelegationNoDefaultInterface.class)
                .intercept(MethodDelegation.to(DelegationNoDefaultInterfaceInterceptor.class))
                .make()
                .load(DelegationNoDefaultInterface.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER);
        DelegationNoDefaultInterface instance = (DelegationNoDefaultInterface) loaded.getLoaded().getDeclaredConstructor().newInstance();
        instance.foo();
    }

    public static class TestInterfaceInterceptor {
        public static String intercept(@SuperCall Callable<String> memoryDatabase) throws Exception{
            System.out.println("-------------------");
//            return memoryDatabase.call();
//            return proxy.thy();
            System.out.println("Calling database @SuperCall");
            try {
                // This will call the overridden method with the original parameters
                return memoryDatabase.call();
            } finally {
                System.out.println("Returned from database @SuperCall");
            }
        }
    }

    public static class TestInterfaceAdvice {
        @Advice.OnMethodEnter
        static void enter(@Advice.Origin Method method) throws Exception {
            System.out.println("-------- TestInterfaceAdvice -----------");
        }
    }

    public interface DelegationNoDefaultInterface {

        String foo();
    }

    public static class DelegationNoDefaultInterfaceInterceptor {

        public static String intercept(@Default DelegationNoDefaultInterface proxy) {
//            return proxy.foo();
            return null;
        }
    }
}