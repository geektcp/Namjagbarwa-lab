package com.geektcp.alpha.agent.advice;//package com.geektcp.alpha.agent.advice;
//
//import com.casstime.agent.annotation.*;
//import net.bytebuddy.asm.Advice;
//
//import java.lang.reflect.Method;
//import java.util.Arrays;
//import java.util.Objects;
//
//import static com.casstime.agent.constant.AgentMetrics.*;
//
///**
// * @author tanghaiyang on 2019/11/24 20:54.
// */
//public class RequestMappingAdvisor {
//
//    private RequestMappingAdvisor() {
//    }
//
//    @Advice.OnMethodEnter
//    public static long onMethodEnter(@Advice.Origin Method method,
//                                     @Advice.AllArguments Object[] arguments) {
//        RequestMapping annotation = method.getAnnotation(RequestMapping.class);
//        String requestMethod =  AdviceUtil.getMethod(annotation);
//        long start = System.currentTimeMillis();
//               String path = AdviceUtil.getPath(annotation);
//        AdviceUtil.handleCount(path, requestMethod, CASS_REQUEST_COUNT_TOTAL);
//        System.out.println("Enter " + method.getName() + " with arguments: " + Arrays.toString(arguments));
//
//        return start;
//    }
//
//    @Advice.OnMethodExit(onThrowable = Exception.class)
//    public static void onMethodExit(@Advice.Origin Method method,
//                                    @Advice.AllArguments Object[] arguments,
//                                    @Advice.Return Object ret,
//                                    @Advice.Thrown Throwable throwable,
//                                    @Advice.Enter long start) {
//        RequestMapping annotation = method.getAnnotation(RequestMapping.class);
//        String requestMethod =  AdviceUtil.getMethod(annotation);
//        String path = AdviceUtil.getPath(annotation);
//        if (Objects.nonNull(throwable)) {
//            AdviceUtil.handleCount(path, requestMethod, CASS_REQUEST_ERR_COUNT);
//            return;
//        }
//        AdviceUtil.handleExit(path, start, CASS_REQUEST_COST_MILLISECONDS);
//        System.out.println("Exit " + method.getName() + " with arguments: " + Arrays.toString(arguments) + " return: " + ret);
//    }
//
//}