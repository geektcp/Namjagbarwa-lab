package com.geektcp.alpha.agent.advice;

import com.geektcp.alpha.agent.builder.AgentCacheBuilder;
import com.geektcp.alpha.agent.util.AdviceUtil;
import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;
import java.util.Objects;

import static com.geektcp.alpha.agent.constant.AgentMetrics.*;

/**
 * @author tanghaiyang on 2019/11/24 20:54.
 */
public class MappingAdvisor {

    private MappingAdvisor() {
    }

    @Advice.OnMethodEnter
    public static long onMethodEnter(@Advice.Origin Method method) {
        long start = System.currentTimeMillis();
        String path = AdviceUtil.getPath(method);
        if (path.length() > 0) {
            String methodStr = AdviceUtil.getMethod(method);
            AgentCacheBuilder.incrementAndGet(CASS_REQUEST_COUNT_TOTAL);
            AdviceUtil.handleCount(path, methodStr, CASS_REQUEST_COUNT);
        }
        return start;
    }

    @Advice.OnMethodExit(onThrowable = Exception.class)
    public static void onMethodExit(@Advice.Origin Method method,
                                    @Advice.AllArguments Object[] arguments,
                                    @Advice.Return Object ret,
                                    @Advice.Thrown Throwable throwable,
                                    @Advice.Enter long start) {
        String path = AdviceUtil.getPath(method);
        String methodStr = AdviceUtil.getMethod(method);
        if (Objects.nonNull(throwable)) {
            AgentCacheBuilder.incrementAndGet(CASS_REQUEST_ERR_COUNT_TOTAL);
            AdviceUtil.handleCount(path, methodStr, CASS_REQUEST_ERR_COUNT);
            return;
        }
        long end = System.currentTimeMillis();
        long timeCost = end - start;
        AgentCacheBuilder.incrementAndGet(CASS_REQUEST_COST_TOTAL_MILLISECONDS, timeCost);
        AdviceUtil.handleExit(path, timeCost, CASS_REQUEST_COST_MILLISECONDS);
    }

}