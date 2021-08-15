package com.geektcp.alpha.spring.shiro.controller.user;


import com.geektcp.alpha.spring.shiro.annotation.ControllerEndpoint;
import com.geektcp.alpha.spring.shiro.common.entity.DeptTree;
import com.geektcp.alpha.spring.shiro.common.entity.Response;
import com.geektcp.alpha.spring.shiro.common.entity.QueryRequest;
import com.geektcp.alpha.spring.shiro.exception.AlphaException;
import com.geektcp.alpha.spring.shiro.entity.user.Dept;
import com.geektcp.alpha.spring.shiro.service.IDeptService;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author tanghaiyang
 */
@Slf4j
@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private IDeptService deptService;

    @GetMapping("select/tree")
    @ControllerEndpoint(exceptionMessage = "获取部门树失败")
    public List<DeptTree<Dept>> getDeptTree() throws AlphaException {
        return this.deptService.findDepts();
    }

    @GetMapping("tree")
    @ControllerEndpoint(exceptionMessage = "获取部门树失败")
    public Response getDeptTree(Dept dept) throws AlphaException {
        List<DeptTree<Dept>> depts = this.deptService.findDepts(dept);
        return new Response().success().data(depts);
    }

    @PostMapping
    @RequiresPermissions("dept:add")
    @ControllerEndpoint(operation = "新增部门", exceptionMessage = "新增部门失败")
    public Response addDept(@Valid Dept dept) {
        this.deptService.createDept(dept);
        return new Response().success();
    }

    @GetMapping("delete/{deptIds}")
    @RequiresPermissions("dept:delete")
    @ControllerEndpoint(operation = "删除部门", exceptionMessage = "删除部门失败")
    public Response deleteDepts(@NotBlank(message = "{required}") @PathVariable String deptIds) throws AlphaException {
        String[] ids = deptIds.split(StringPool.COMMA);
        this.deptService.deleteDepts(ids);
        return new Response().success();
    }

    @PostMapping("update")
    @RequiresPermissions("dept:update")
    @ControllerEndpoint(operation = "修改部门", exceptionMessage = "修改部门失败")
    public Response updateDept(@Valid Dept dept) throws AlphaException {
        this.deptService.updateDept(dept);
        return new Response().success();
    }

    @GetMapping("excel")
    @RequiresPermissions("dept:export")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    public void export(Dept dept, QueryRequest request, HttpServletResponse response) throws AlphaException {
        List<Dept> depts = this.deptService.findDepts(dept, request);
        ExcelKit.$Export(Dept.class, response).downXlsx(depts, false);
    }
}
