package com.geektcp.alpha.agent.advice;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;

import static com.geektcp.alpha.agent.util.LogUtil.log;

public class TimerAdvice {

    private TimerAdvice() {
    }

    @Advice.OnMethodEnter
    static long enter(@Advice.Origin Method method) {
        return System.currentTimeMillis();
    }

    @Advice.OnMethodExit
    static void exit(@Advice.Origin Method method, @Advice.Enter long start) {
        long end = System.currentTimeMillis();
        log("TimerAdvice: " + method + " took " + (end - start) + " milliseconds ");
    }

}