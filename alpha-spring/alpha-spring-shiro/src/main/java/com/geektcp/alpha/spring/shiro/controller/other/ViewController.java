package com.geektcp.alpha.spring.shiro.controller.other;

import com.geektcp.alpha.spring.shiro.common.entity.AlphaConstant;
import com.geektcp.alpha.spring.shiro.utils.AlphaUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tanghaiyang
 */
@Controller("othersView")
@RequestMapping(AlphaConstant.VIEW_PREFIX + "others")
public class ViewController {

    @GetMapping("alpha/form")
    @RequiresPermissions("alpha:form:view")
    public String alphaForm() {
        return AlphaUtil.view("others/alpha/form");
    }

    @GetMapping("alpha/form/group")
    @RequiresPermissions("alpha:formgroup:view")
    public String alphaFormGroup() {
        return AlphaUtil.view("others/alpha/formGroup");
    }

    @GetMapping("alpha/tools")
    @RequiresPermissions("alpha:tools:view")
    public String alphaTools() {
        return AlphaUtil.view("others/alpha/tools");
    }

    @GetMapping("alpha/icon")
    @RequiresPermissions("alpha:icons:view")
    public String alphaIcon() {
        return AlphaUtil.view("others/alpha/icon");
    }

    @GetMapping("alpha/others")
    @RequiresPermissions("others:alpha:others")
    public String alphaOthers() {
        return AlphaUtil.view("others/alpha/others");
    }

    @GetMapping("apex/line")
    @RequiresPermissions("apex:line:view")
    public String apexLine() {
        return AlphaUtil.view("others/apex/line");
    }

    @GetMapping("apex/area")
    @RequiresPermissions("apex:area:view")
    public String apexArea() {
        return AlphaUtil.view("others/apex/area");
    }

    @GetMapping("apex/column")
    @RequiresPermissions("apex:column:view")
    public String apexColumn() {
        return AlphaUtil.view("others/apex/column");
    }

    @GetMapping("apex/radar")
    @RequiresPermissions("apex:radar:view")
    public String apexRadar() {
        return AlphaUtil.view("others/apex/radar");
    }

    @GetMapping("apex/bar")
    @RequiresPermissions("apex:bar:view")
    public String apexBar() {
        return AlphaUtil.view("others/apex/bar");
    }

    @GetMapping("apex/mix")
    @RequiresPermissions("apex:mix:view")
    public String apexMix() {
        return AlphaUtil.view("others/apex/mix");
    }

    @GetMapping("map")
    @RequiresPermissions("map:view")
    public String map() {
        return AlphaUtil.view("others/map/gaodeMap");
    }

    @GetMapping("eximport")
    @RequiresPermissions("others:eximport:view")
    public String eximport() {
        return AlphaUtil.view("others/eximport/eximport");
    }

    @GetMapping("eximport/result")
    public String eximportResult() {
        return AlphaUtil.view("others/eximport/eximportResult");
    }
}
