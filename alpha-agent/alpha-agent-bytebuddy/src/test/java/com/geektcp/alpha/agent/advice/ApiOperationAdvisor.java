package com.geektcp.alpha.agent.advice;//package com.geektcp.alpha.agent.advice;
//
//import com.casstime.agent.annotation.*;
//import com.geektcp.alpha.agent.builder.AgentCacheBuilder;
//import net.bytebuddy.asm.Advice;
//
//import java.lang.reflect.Method;
//import java.util.Arrays;
//import java.util.Objects;
//import java.util.concurrent.atomic.AtomicLong;
//
///**
// * @author tanghaiyang on 2019/11/24 20:54.
// */
//public class ApiOperationAdvisor {
//
//    private ApiOperationAdvisor() {
//    }
//
//    @Advice.OnMethodEnter
//    public static long onMethodEnter(@Advice.Origin Method method,
//                                     @Advice.AllArguments Object[] arguments) {
//        ApiOperation annotation = method.getAnnotation(ApiOperation.class);
//        System.out.println("getDeclaredAnnotations: " + Arrays.toString(method.getDeclaredAnnotations()));
//        long start = System.currentTimeMillis();
//        getMappingHandle(annotation, start);
//        if (Objects.nonNull(annotation)) {
//            System.out.println("Enter " + method.getName() + " with arguments: " + Arrays.toString(arguments));
//        }
//
//        return start;
//    }
//
//    @Advice.OnMethodExit
//    public static void onMethodExit(@Advice.Origin Method method,
//                                    @Advice.AllArguments Object[] arguments,
//                                    @Advice.Return Object ret) {
//        GetMapping annotation = method.getAnnotation(GetMapping.class);
//        if (Objects.nonNull(annotation)) {
//            System.out.println("Exit " + method.getName() + " with arguments: " + Arrays.toString(arguments) + " return: " + ret);
//        }
//    }
//
//    public static void getMappingHandle(ApiOperation annotation,@Advice.Enter long start) {
//        if (Objects.isNull(annotation)) {
//            return;
//        }
//        try {
//            String desc = annotation.value();
//            String note = annotation.notes();
//            System.out.println("annotation: " + desc + " | " + note);
//            String keyCount = String.format("CASS_API_REQUEST_COUNT { desc = \"%s\"}", desc);
//            AgentCacheBuilder.incrementAndGet(keyCount);
//            long end = System.currentTimeMillis();
//            long timeCost = end - start;
//            String keyPath = String.format("CASS_API_COST_TIME { desc = \"%s\"}", desc);
//            System.out.println("||||||ApiOperationAdvisor||||||||" + timeCost + " took " + timeCost + " milliseconds ");
//            AgentCacheBuilder.put(keyPath, new AtomicLong(timeCost));
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//    }
//
//}