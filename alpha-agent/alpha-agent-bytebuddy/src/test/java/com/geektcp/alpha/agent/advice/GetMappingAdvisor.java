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
//public class GetMappingAdvisor {
//
//    private GetMappingAdvisor() {
//    }
//
//    @Advice.OnMethodEnter
//    public static long onMethodEnter(@Advice.Origin Method method,
//                                     @Advice.AllArguments Object[] arguments) {
//        long start = System.currentTimeMillis();
//        try {
//            String path = AdviceUtil.getPath(method);
//            if(path.length()==0){
//                return start;
//            }
//            AdviceUtil.handleCount(path, RequestMethod.METHOD_GET.toString(), CASS_REQUEST_COUNT_TOTAL);
//            System.out.println("Enter " + method.getName() + " with arguments: " + Arrays.toString(arguments));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return start;
//    }
//
//    @Advice.OnMethodExit(onThrowable = Exception.class)
//    public static void onMethodExit(@Advice.Origin Method method,
//                                    @Advice.AllArguments Object[] arguments,
//                                    @Advice.Return Object ret,
//                                    @Advice.Thrown Throwable throwable,
//                                    @Advice.Enter long start) {
//        GetMapping annotation = method.getAnnotation(GetMapping.class);
//        if (Objects.isNull(annotation)) {
//            return;
//        }
//        String path = AdviceUtil.getPath(method);
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