package com.geektcp.alpha.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.geektcp.alpha.dubbo.api.ThyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ThyController {

    @Reference
    private ThyService thyService;

    @GetMapping("sayHello")
    public String sayHello(){
        log.info("this controller: sayHello");
        return thyService.sayHello("Dubbo");
    }
}
