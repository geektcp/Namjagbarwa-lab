package com.geektcp.alpha.spring.shiro.controller.monitor;

import com.geektcp.alpha.spring.shiro.common.entity.AlphaConstant;
import com.geektcp.alpha.spring.shiro.utils.AlphaUtil;
import com.geektcp.alpha.spring.shiro.entity.monitor.JvmInfo;
import com.geektcp.alpha.spring.shiro.entity.monitor.ServerInfo;
import com.geektcp.alpha.spring.shiro.entity.monitor.TomcatInfo;
import com.geektcp.alpha.spring.shiro.helper.AlphaActuatorHelper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.geektcp.alpha.spring.shiro.endpoint.AlphaMetricsEndpoint.AlphaMetricResponse;

/**
 * @author tanghaiyang
 */
@Controller("monitorView")
@RequestMapping(AlphaConstant.VIEW_PREFIX + "monitor")
public class ViewController {

    @Autowired
    private AlphaActuatorHelper actuatorHelper;

    @GetMapping("online")
    @RequiresPermissions("online:view")
    public String online() {
        return AlphaUtil.view("monitor/online");
    }

    @GetMapping("log")
    @RequiresPermissions("log:view")
    public String log() {
        return AlphaUtil.view("monitor/log");
    }

    @GetMapping("loginlog")
    @RequiresPermissions("loginlog:view")
    public String loginLog() {
        return AlphaUtil.view("monitor/loginLog");
    }

    @GetMapping("httptrace")
    @RequiresPermissions("httptrace:view")
    public String httptrace() {
        return AlphaUtil.view("monitor/httpTrace");
    }

    @GetMapping("jvm")
    @RequiresPermissions("jvm:view")
    public String jvmInfo(Model model) {
        List<AlphaMetricResponse> jvm = actuatorHelper.getMetricResponseByType("jvm");
        JvmInfo jvmInfo = actuatorHelper.getJvmInfoFromMetricData(jvm);
        model.addAttribute("jvm", jvmInfo);
        return AlphaUtil.view("monitor/jvmInfo");
    }

    @GetMapping("tomcat")
    @RequiresPermissions("tomcat:view")
    public String tomcatInfo(Model model) {
        List<AlphaMetricResponse> tomcat = actuatorHelper.getMetricResponseByType("tomcat");
        TomcatInfo tomcatInfo = actuatorHelper.getTomcatInfoFromMetricData(tomcat);
        model.addAttribute("tomcat", tomcatInfo);
        return AlphaUtil.view("monitor/tomcatInfo");
    }

    @GetMapping("server")
    @RequiresPermissions("server:view")
    public String serverInfo(Model model) {
        List<AlphaMetricResponse> jdbcInfo = actuatorHelper.getMetricResponseByType("jdbc");
        List<AlphaMetricResponse> systemInfo = actuatorHelper.getMetricResponseByType("system");
        List<AlphaMetricResponse> processInfo = actuatorHelper.getMetricResponseByType("process");

        ServerInfo serverInfo = actuatorHelper.getServerInfoFromMetricData(jdbcInfo, systemInfo, processInfo);
        model.addAttribute("server", serverInfo);
        return AlphaUtil.view("monitor/serverInfo");
    }

    @GetMapping("swagger")
    public String swagger() {
        return AlphaUtil.view("monitor/swagger");
    }
}
