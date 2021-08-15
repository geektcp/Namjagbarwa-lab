package com.geektcp.alpha.spring.shiro.controller.user;


import com.geektcp.alpha.spring.shiro.annotation.ControllerEndpoint;
import com.geektcp.alpha.spring.shiro.common.controller.BaseController;
import com.geektcp.alpha.spring.shiro.common.entity.Response;
import com.geektcp.alpha.spring.shiro.common.entity.QueryRequest;
import com.geektcp.alpha.spring.shiro.exception.AlphaException;
import com.geektcp.alpha.spring.shiro.entity.user.Role;
import com.geektcp.alpha.spring.shiro.service.IRoleService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @author tanghaiyang
 */
@Slf4j
@RestController
@RequestMapping("role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;

    @GetMapping
    public Response getAllRoles(Role role) {
        return new Response().success().data(roleService.findRoles(role));
    }

    @GetMapping("list")
    @RequiresPermissions("role:view")
    public Response roleList(Role role, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.roleService.findRoles(role, request));
        return new Response().success().data(dataTable);
    }

    @PostMapping
    @RequiresPermissions("role:add")
    @ControllerEndpoint(operation = "新增角色", exceptionMessage = "新增角色失败")
    public Response addRole(@Valid Role role) {
        this.roleService.createRole(role);
        return new Response().success();
    }

    @GetMapping("delete/{roleIds}")
    @RequiresPermissions("role:delete")
    @ControllerEndpoint(operation = "删除角色", exceptionMessage = "删除角色失败")
    public Response deleteRoles(@NotBlank(message = "{required}") @PathVariable String roleIds) {
        this.roleService.deleteRoles(roleIds);
        return new Response().success();
    }

    @PostMapping("update")
    @RequiresPermissions("role:update")
    @ControllerEndpoint(operation = "修改角色", exceptionMessage = "修改角色失败")
    public Response updateRole(Role role) {
        this.roleService.updateRole(role);
        return new Response().success();
    }

    @GetMapping("excel")
    @RequiresPermissions("role:export")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest queryRequest, Role role, HttpServletResponse response) throws AlphaException {
        List<Role> roles = this.roleService.findRoles(role, queryRequest).getRecords();
        ExcelKit.$Export(Role.class, response).downXlsx(roles, false);
    }

}
