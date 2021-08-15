package com.geektcp.alpha.agent.advice;

import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperMethod;
import java.lang.reflect.Method;

/**
 * @author haiyang.tang on 12.06 006 13:46:02.
 */
public class InterfaceAdvice {

    private InterfaceAdvice() {
    }

    public static class NoFallback {
        @RuntimeType
        public static Object foo(@SuperMethod(fallbackToDefault = false) Method method){
            return null;
        }
    }
}
