package com.geektcp.alpha.dubbo.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.geektcp.alpha.dubbo.api.ThyService;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

@Slf4j
@Service
public class ThyServiceImpl implements ThyService {

    @Override
    public String sayHello(String name) {
        log.info("this is service sayHello");
        log.info("[" + Instant.now() + "] Hello " + name  + "by annotation");
        return "Hello " + name + " by annotation";
    }

}
