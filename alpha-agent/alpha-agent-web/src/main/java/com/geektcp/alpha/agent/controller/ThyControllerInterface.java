package com.geektcp.alpha.agent.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author haiyang.tang on 12.02 002 19:03:16.
 */
public interface ThyControllerInterface {

    @RequestMapping(method = RequestMethod.GET, value =  "configs")
    String saveConfigTemplate() ;

    @RequestMapping(method = RequestMethod.GET, value = "/templates")
    String configTemplateDetail() ;

    @RequestMapping(method = RequestMethod.POST, value = "/query")
    String listConfigTemplates();

}
