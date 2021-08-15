package com.geektcp.alpha.spring.security.controller;

import com.geektcp.alpha.spring.security.service.UserService;
import com.geektcp.alpha.spring.security.domain.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * Created by tanghaiyang
 * 22:56 2018/9/2
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
//    @PreAuthorize("hasAuthority('admin')")
    public UserVo getUserByName(@RequestParam("username") String userName) {
        log.info("getUserByName!");
        return userService.getUserByUserName(userName);
    }

    @GetMapping(value = "/test0")
    @PreAuthorize("hasAuthority('admin')")
    public UserVo test0() {
        log.info("test0!");
        return new UserVo("test1");
    }

    @GetMapping(value = "/test1")
    @PreAuthorize("hasAuthority('employ')")
    public UserVo test1() {
        log.info("test1!");
        return new UserVo("test1");
    }

    @GetMapping(value = "/test2")
    @PreAuthorize("@el.check('storage:list')")
    public UserVo test2() {
        log.info("test2!");
        return new UserVo("test2");
    }

    @GetMapping(value = "/test3")
    @PreAuthorize("hasRole('sys:admin')")
    public UserVo test3() {
        log.info("test3!");
        return new UserVo("test3");
    }

    @GetMapping(value = "/test4")
    @PreAuthorize("hasRole('sys:user:add')")
    public UserVo test4() {
        log.info("test4!");
        return new UserVo("test4");
    }

}
