package com.geektcp.alpha.agent.advice;

import net.bytebuddy.asm.Advice;
import static com.geektcp.alpha.agent.util.LogUtil.log;

public class ExceptionAdvice {

    private ExceptionAdvice() {
    }

    @Advice.OnMethodEnter
    static long enter(@Advice.Origin String method)  {
        log("before");
        return System.currentTimeMillis();
    }

    @Advice.OnMethodExit
    static void exit(@Advice.Origin String method, @Advice.Enter long start) throws Exception {
        log("end");
        long end = System.currentTimeMillis();
        log("ExceptionAdvice: " + method + " took " + (end - start) + " milliseconds ");
    }

}