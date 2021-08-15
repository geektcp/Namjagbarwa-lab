package com.geektcp.alpha.db.h2.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping(value = "/test")
@Slf4j
public class UserController {

    @GetMapping(value = "/aaa")
    public String addUser() {
        log.info("test case");
        return System.getProperty("spring.profiles.active");
    }

}
