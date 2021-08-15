package com.geektcp.alpha.agent.controller;

/**
 * @author haiyang.tang on 10.21 021 15:03:33.
 */

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api("test")
@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping("/test")
    public String test() {
        log.info("test api");
        try{
            get();
            Thread.sleep(200);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return "success";
    }

    @GetMapping(value = "/prom")
    public String prom() {
        log.info("test api");
        String a = get();
        log.info("a: {}", a);
        return "ddddddddd";
    }

    @PostMapping(value = "/test2")
    public String test2() {
        log.info("test api post");
        String a = get();
        log.info("a: {}", a);
        return "post";
    }

    @PostMapping(value = "/test3")
    public String test3() {
        log.info("test3 api post");
        String a = get();
        throw new RuntimeException();
    }

    @RequestMapping(value = "/test4", method = RequestMethod.GET)
    public String test4() {
        log.info("test4 api gc");
        String a = get();
        System.gc();
        return "test4!";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test5")
    @ResponseBody
    public String test5() {
        log.info("test5 api post");
        String a = get();
        return a;
    }

    ////////////////////////////////////////////
    private String get(){
        log.info("333333333333");
        return "test agent!";
    }

}

