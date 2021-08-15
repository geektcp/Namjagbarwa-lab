package com.geektcp.alpha.agent.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haiyang.tang on 12.02 002 19:08:28.
 */

@Api(tags = "TmsService：物流运费配置信息")
@RestController
public class ThyServiceInterface implements ThyControllerInterface {

    @Override
    @RequestMapping(method = RequestMethod.GET, value =  "configs")
    public String saveConfigTemplate(){
        System.out.println("===========this is saveConfigTemplate body!");
        return "saveConfigTemplate";
    }

    public String configTemplateDetail() {
        System.out.println("===========this is configTemplateDetail body!");
        return "configTemplateDetail";
    }

    public String listConfigTemplates() {
        return "listConfigTemplates";
    }



}
