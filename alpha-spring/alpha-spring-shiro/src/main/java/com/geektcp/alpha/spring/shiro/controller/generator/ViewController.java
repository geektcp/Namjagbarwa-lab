package com.geektcp.alpha.spring.shiro.controller.generator;

import com.geektcp.alpha.spring.shiro.common.entity.AlphaConstant;
import com.geektcp.alpha.spring.shiro.utils.AlphaUtil;
import com.geektcp.alpha.spring.shiro.entity.generator.GeneratorConfig;
import com.geektcp.alpha.spring.shiro.service.IGeneratorConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tanghaiyang
 */
@Controller("generatorViews")
@RequestMapping(AlphaConstant.VIEW_PREFIX + "generator")
public class ViewController {

    @Autowired
    private IGeneratorConfigService generatorConfigService;

    @GetMapping("generator")
    @RequiresPermissions("generator:view")
    public String generator() {
        return AlphaUtil.view("generator/generator");
    }

    @GetMapping("configure")
    @RequiresPermissions("generator:configure:view")
    public String generatorConfigure(Model model) {
        GeneratorConfig generatorConfig = generatorConfigService.findGeneratorConfig();
        model.addAttribute("config", generatorConfig);
        return AlphaUtil.view("generator/configure");
    }
}
