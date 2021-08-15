package com.geektcp.alpha.agent.controller;

/**
 * @author haiyang.tang on 10.21 021 15:03:33.
 */

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Api("test")
@RestController
@RequestMapping("/")
public class BinderController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/binder")
    public String binderTest() {
        log.info("test binder");
        try{
            get();
            Thread.sleep(100);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return "success";
    }


    ////////////////////////////////////////////
    private String get(){
        log.info("this is bingder get method");
        return "test agent!";
    }

}

