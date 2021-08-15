package com.geektcp.alpha.spring.shiro.controller.monitor;

import com.geektcp.alpha.spring.shiro.annotation.ControllerEndpoint;
import com.geektcp.alpha.spring.shiro.common.entity.Response;
import com.geektcp.alpha.spring.shiro.utils.DateUtil;
import com.geektcp.alpha.spring.shiro.endpoint.AlphaHttpTraceEndpoint;
import com.geektcp.alpha.spring.shiro.entity.monitor.AlphaHttpTrace;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.geektcp.alpha.spring.shiro.endpoint.AlphaHttpTraceEndpoint.AlphaHttpTraceDescriptor;

/**
 * @author tanghaiyang
 */
@Slf4j
@RestController
@RequestMapping("alpha/actuator")
public class AlphaActuatorController {

    @Autowired
    private AlphaHttpTraceEndpoint httpTraceEndpoint;

    @GetMapping("httptrace")
    @RequiresPermissions("httptrace:view")
    @ControllerEndpoint(exceptionMessage = "请求追踪失败")
    public Response httpTraces(String method, String url) {
        AlphaHttpTraceDescriptor traces = httpTraceEndpoint.traces();
        List<HttpTrace> httpTraceList = traces.getTraces();
        List<AlphaHttpTrace> alphaHttpTraces = new ArrayList<>();
        httpTraceList.forEach(t -> {
            AlphaHttpTrace alphaHttpTrace = new AlphaHttpTrace();
            alphaHttpTrace.setRequestTime(DateUtil.formatInstant(t.getTimestamp(), DateUtil.FULL_TIME_SPLIT_PATTERN));
            alphaHttpTrace.setMethod(t.getRequest().getMethod());
            alphaHttpTrace.setUrl(t.getRequest().getUri());
            alphaHttpTrace.setStatus(t.getResponse().getStatus());
            alphaHttpTrace.setTimeTaken(t.getTimeTaken());
            if (StringUtils.isNotBlank(method) && StringUtils.isNotBlank(url)) {
                if (StringUtils.equalsIgnoreCase(method, alphaHttpTrace.getMethod())
                        && StringUtils.containsIgnoreCase(alphaHttpTrace.getUrl().toString(), url))
                    alphaHttpTraces.add(alphaHttpTrace);
            } else if (StringUtils.isNotBlank(method)) {
                if (StringUtils.equalsIgnoreCase(method, alphaHttpTrace.getMethod()))
                    alphaHttpTraces.add(alphaHttpTrace);
            } else if (StringUtils.isNotBlank(url)) {
                if (StringUtils.containsIgnoreCase(alphaHttpTrace.getUrl().toString(), url))
                    alphaHttpTraces.add(alphaHttpTrace);
            } else {
                alphaHttpTraces.add(alphaHttpTrace);
            }
        });
        Map<String, Object> data = new HashMap<>();
        data.put("rows", alphaHttpTraces);
        data.put("total", alphaHttpTraces.size());
        return new Response().success().data(data);
    }
}
