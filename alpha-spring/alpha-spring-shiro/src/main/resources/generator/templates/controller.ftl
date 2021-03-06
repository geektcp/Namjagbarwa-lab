package com.geektcp.alpha.spring.shiro.system.controller;

import com.geektcp.alpha.spring.shiro.annotation.ControllerEndpoint;
import com.geektcp.alpha.spring.shiro.utils.AlphaUtil;
import com.geektcp.alpha.spring.shiro.common.entity.AlphaConstant;
import com.geektcp.alpha.spring.shiro.common.controller.BaseController;
import com.geektcp.alpha.spring.shiro.common.entity.Response;
import com.geektcp.alpha.spring.shiro.common.entity.QueryRequest;
import ${basePackage}.${entityPackage}.${className};
import ${basePackage}.${servicePackage}.I${className}Service;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * ${tableComment} Controller
 *
 * @author ${author}
 * @date ${date}
 */
@Slf4j
@Validated
@Controller
public class ${className}Controller extends BaseController {

    @Autowired
    private I${className}Service ${className?uncap_first}Service;

    @GetMapping(AlphaConstant.VIEW_PREFIX + "${className?uncap_first}")
    public String ${className?uncap_first}Index(){
        return AlphaUtil.view("${className?uncap_first}/${className?uncap_first}");
    }

    @GetMapping("${className?uncap_first}")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:list")
    public AlphaResponse getAll${className}s(${className} ${className?uncap_first}) {
        return new AlphaResponse().success().data(${className?uncap_first}Service.find${className}s(${className?uncap_first}));
    }

    @GetMapping("${className?uncap_first}/list")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:list")
    public AlphaResponse ${className?uncap_first}List(QueryRequest request, ${className} ${className?uncap_first}) {
        Map<String, Object> dataTable = getDataTable(this.${className?uncap_first}Service.find${className}s(request, ${className?uncap_first}));
        return new AlphaResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "??????${className}", exceptionMessage = "??????${className}??????")
    @PostMapping("${className?uncap_first}")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:add")
    public AlphaResponse add${className}(@Valid ${className} ${className?uncap_first}) {
        this.${className?uncap_first}Service.create${className}(${className?uncap_first});
        return new AlphaResponse().success();
    }

    @ControllerEndpoint(operation = "??????${className}", exceptionMessage = "??????${className}??????")
    @GetMapping("${className?uncap_first}/delete")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:delete")
    public AlphaResponse delete${className}(${className} ${className?uncap_first}) {
        this.${className?uncap_first}Service.delete${className}(${className?uncap_first});
        return new AlphaResponse().success();
    }

    @ControllerEndpoint(operation = "??????${className}", exceptionMessage = "??????${className}??????")
    @PostMapping("${className?uncap_first}/update")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:update")
    public AlphaResponse update${className}(${className} ${className?uncap_first}) {
        this.${className?uncap_first}Service.update${className}(${className?uncap_first});
        return new AlphaResponse().success();
    }

    @ControllerEndpoint(operation = "??????${className}", exceptionMessage = "??????Excel??????")
    @PostMapping("${className?uncap_first}/excel")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:export")
    public void export(QueryRequest queryRequest, ${className} ${className?uncap_first}, HttpServletResponse response) {
        List<${className}> ${className?uncap_first}s = this.${className?uncap_first}Service.find${className}s(queryRequest, ${className?uncap_first}).getRecords();
        ExcelKit.$Export(${className}.class, response).downXlsx(${className?uncap_first}s, false);
    }
}
