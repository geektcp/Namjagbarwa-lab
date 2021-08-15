package com.geektcp.alpha.agent.advice;

import com.geektcp.alpha.agent.builder.AgentCacheBuilder;
import com.geektcp.alpha.agent.util.AdviceUtil;
import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.geektcp.alpha.agent.constant.AgentFilter.*;
import static com.geektcp.alpha.agent.constant.AgentMetrics.*;
import static com.geektcp.alpha.agent.util.LogUtil.log;

public class AnyAdvice {

    private AnyAdvice() {
    }

    @Advice.OnMethodEnter
    static Map<String, Object> begin(@Advice.Origin Method method, @Advice.AllArguments Object[] arguments) {
        Map<String, Object> enterRet = new HashMap<>();
        long httpStart = System.currentTimeMillis();
        enterRet.put(FILTER_METRIC_HTTP_START, httpStart);
        if (!method.getName().equals(FILTER_METHOD_PROCESS_REQUEST)) {
            return enterRet;
        }
        try {
            Object httpServletRequest = arguments[0];
            Class<?> clsHttpServletRequest = httpServletRequest.getClass();
            String requestClsName = clsHttpServletRequest.getSimpleName();
            if (FILTER_PARAMETER_REQUEST_FACADE.equals(requestClsName)||
                    FILTER_PARAMETER_APPLICATION_HTTP_REQUEST.equals(requestClsName)) {
                String httpPath = (String) clsHttpServletRequest.getMethod(FILTER_METHOD_GET_REQUEST_URI).invoke(httpServletRequest);
                String httpMethod = (String) clsHttpServletRequest.getMethod(FILTER_METHOD_GET_METHOD).invoke(httpServletRequest);
                enterRet.put(FILTER_METRIC_HTTP_PATH, httpPath);
                enterRet.put(FILTER_METRIC_HTTP_METHOD, httpMethod);
                if (httpPath.length() > 0) {
                    AgentCacheBuilder.incrementAndGet(CASS_REQUEST_COUNT_TOTAL);
                    AdviceUtil.handleCount(httpPath, httpMethod, CASS_REQUEST_COUNT);
                }
            }
        } catch (Exception e) {
            log("begin exception: " + e.getMessage());
        }
        return enterRet;
    }

    @Advice.OnMethodExit(onThrowable = Exception.class)
    public static void end(@Advice.Origin Method method,
                           @Advice.Thrown Throwable throwable,
                           @Advice.Enter Map<String, Object> enterRet) {
        if (!method.getName().equals(FILTER_METHOD_PROCESS_REQUEST)) {
            return;
        }
        try {
            long httpStart = (long) enterRet.get(FILTER_METRIC_HTTP_START);
            String httpPath = enterRet.get(FILTER_METRIC_HTTP_PATH).toString();
            String httpMethod = enterRet.get(FILTER_METRIC_HTTP_METHOD).toString();
            if (Objects.nonNull(throwable)) {
                AgentCacheBuilder.incrementAndGet(CASS_REQUEST_ERR_COUNT_TOTAL);
                AdviceUtil.handleCount(httpPath, httpMethod, CASS_REQUEST_ERR_COUNT);
                return;
            }
            long httpEnd = System.currentTimeMillis();
            long timeCost = httpEnd - httpStart;
            AgentCacheBuilder.incrementAndGet(CASS_REQUEST_COST_TOTAL_MILLISECONDS, timeCost);
            AdviceUtil.handleExit(httpPath, timeCost, CASS_REQUEST_COST_MILLISECONDS);
        } catch (Exception e) {
            log("end exception: " + e.getMessage());
        }
    }
}