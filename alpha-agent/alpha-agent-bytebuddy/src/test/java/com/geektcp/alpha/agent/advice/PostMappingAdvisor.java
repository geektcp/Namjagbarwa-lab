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
//public class PostMappingAdvisor {
//
//    private PostMappingAdvisor() {
//    }
//
//    @Advice.OnMethodEnter
//    public static long onMethodEnter(@Advice.Origin Method method,
//                                     @Advice.AllArguments Object[] arguments) {
//        PostMapping annotation = method.getAnnotation(PostMapping.class);
//        long start = System.currentTimeMillis();
//        if (Objects.isNull(annotation)) {
//            return start;
//        }
//        String path = AdviceUtil.getPath(annotation);
//        AdviceUtil.handleCount(path, RequestMethod.METHOD_GET.toString(), CASS_REQUEST_COUNT_TOTAL);
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
//        PostMapping annotation = method.getAnnotation(PostMapping.class);
//        if (Objects.isNull(annotation)) {
//            return;
//        }
//        String path = AdviceUtil.getPath(annotation);
//        if (Objects.nonNull(throwable)) {
//            AdviceUtil.handleCount(path, RequestMethod.METHOD_GET.toString(), CASS_REQUEST_ERR_COUNT);
//            return;
//        }
//        AdviceUtil.handleExit(path, start, CASS_REQUEST_COST_MILLISECONDS);
//        System.out.println("Exit " + method.getName() + " with arguments: " + Arrays.toString(arguments) + " return: " + ret);
//    }
//
//
//}