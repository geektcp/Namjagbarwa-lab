package com.geektcp.alpha.agent.util;

import com.geektcp.alpha.agent.builder.AgentCacheBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

import static com.geektcp.alpha.agent.constant.AgentMethod.*;
import static com.geektcp.alpha.agent.util.LogUtil.log;

/**
 * @author haiyang.tang on 12.03 003 16:40:22.
 */
public class AdviceUtil {

    private AdviceUtil() {
    }

    public static void handleExit(String path, long timeCost, String metric) {
        try {
            String keyPath = String.format("%s { path = \"%s\"}", metric, path);
            AgentCacheBuilder.incrementAndGet(keyPath, timeCost);
        } catch (Exception e) {
            log(String.format("handleExit[path:%s] has exception: %s",path, e.getMessage()));
        }
    }

    public static void handleCount(String path, String method, String metric) {
        try {
            String keyPath = String.format("%s { method = \"%s\" , path = \"%s\"}", metric, method, path);
            AgentCacheBuilder.incrementAndGet(keyPath);
        } catch (Exception e) {
            log(String.format("handleCount[method:%s] has exception: %s",method, e.getMessage()));
        }
    }

    public static String getPath(Method method) {
        String path = "";
        try {
            for (Annotation annotationElement : method.getAnnotations()) {
                String simpleName = annotationElement.annotationType().getSimpleName().toLowerCase();
                if (simpleName.contains(ANNOTATION_PROMETHEUS)
                        || simpleName.contains(ANNOTATION_GET)
                        || simpleName.contains(ANNOTATION_POST)
                        || simpleName.contains(ANNOTATION_REQUEST)
                ) {
                    String[] pathArr = (String[]) annotationElement.getClass().getMethod(VALUE).invoke(annotationElement);
                    path = Arrays.toString(pathArr);
                }
            }
        } catch (Exception e) {
            log(String.format("getPath[%s]:%s",path, e.getMessage()));
        }
        return path;
    }

    public static String getMethod(Method method) {
        if (Objects.isNull(method)) {
            log("method is null!");
            return "";
        }
        try {
            for (Annotation annotationElement : method.getAnnotations()) {
                String simpleName = annotationElement.annotationType().getSimpleName().toLowerCase();
                if (simpleName.contains(ANNOTATION_REQUEST)) {
                    Object[] methodArr = (Object[]) annotationElement.getClass().getMethod(METHOD).invoke(annotationElement);
                    return Arrays.toString(methodArr);
                }
                if (simpleName.contains(ANNOTATION_PROMETHEUS)) {
                    return METHOD_PROMETHEUS;
                }
                if (simpleName.contains(ANNOTATION_GET)) {
                    return METHOD_GET;
                }
                if (simpleName.contains(ANNOTATION_POST)) {
                    return METHOD_POST;
                }
            }
        } catch (Exception e) {
            log(String.format("getMethod[%s]:%s",method.getName(), e.getMessage()));
        }
        return DEFAULT;
    }
}
