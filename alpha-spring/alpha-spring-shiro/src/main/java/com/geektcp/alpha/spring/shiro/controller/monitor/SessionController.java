package com.geektcp.alpha.spring.shiro.controller.monitor;

import com.geektcp.alpha.spring.shiro.common.entity.Response;
import com.geektcp.alpha.spring.shiro.entity.monitor.ActiveUser;
import com.geektcp.alpha.spring.shiro.service.ISessionService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tanghaiyang
 */
@RestController
@RequestMapping("session")
public class SessionController {

    @Autowired
    private ISessionService sessionService;

    @GetMapping("list")
    @RequiresPermissions("online:view")
    public Response list(String username) {
        List<ActiveUser> list = sessionService.list(username);
        Map<String, Object> data = new HashMap<>();
        data.put("rows", list);
        data.put("total", CollectionUtils.size(list));
        return new Response().success().data(data);
    }

    @GetMapping("delete/{id}")
    @RequiresPermissions("user:kickout")
    public Response forceLogout(@PathVariable String id) {
        sessionService.forceLogout(id);
        return new Response().success();
    }
}
