package com.geektcp.alpha.api.controller;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author tanghaiyang on 2018/05/22.
 */
@Slf4j
@Api("prometheus web hook")
@RestController
@RequestMapping("/")
public class HookController {

    @RequestMapping("/hook")
    public String hook(@RequestBody String body) {
        //处理预警信息(邮件、短信、钉钉)
        log.info("web hook警报系统,body:\n{}",body);
        return "success";
    }
}
