package com.geektcp.alpha.spring.nacos.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanghaiyang on 2020/7/19 22:54.
 */
@RestController
public class DemoController {

    @NacosValue(value = "${name:example}", autoRefreshed = true)
    private String name;

    @NacosValue(value = "${sss:example}", autoRefreshed = true)
    private String sss;

    @NacosValue(value = "${cellphone:example}", autoRefreshed = true)
    private String cellphone;

    @GetMapping("/getName")
    public String getName() {
        System.out.println(name);
        return name;
    }

}
