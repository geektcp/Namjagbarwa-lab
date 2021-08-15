package com.geektcp.alpha.agent.example.advisor;

import com.geektcp.alpha.agent.example.annotation.Prometheus;
import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author haiyang.tang on 12.03 003 15:10:59.
 */
public class ExceptionAdvisor {
    @Advice.OnMethodEnter
    public static void onMethodEnter(@Advice.Origin Method method, @Advice.AllArguments Object[] arguments) {
        if (method.getAnnotation(Prometheus.class) != null) {
            System.out.println("Enter " + method.getName() + " with arguments: " + Arrays.toString(arguments));
        }
    }

    @Advice.OnMethodExit(onThrowable = Exception.class)
    public static void onMethodExit(@Advice.Thrown Throwable throwable, @Advice.Origin Method method, @Advice.AllArguments Object[] arguments, @Advice.Return Object ret) {
        if (method.getAnnotation(Prometheus.class) != null) {
            System.out.println("Exit " + method.getName() + " with arguments: " + Arrays.toString(arguments) + " return: " + ret);
        }
        if(Objects.isNull(throwable)){
            return;
        }
        // 异常捕获
        System.out.println("agent exception: " + throwable.getMessage());
    }
}
