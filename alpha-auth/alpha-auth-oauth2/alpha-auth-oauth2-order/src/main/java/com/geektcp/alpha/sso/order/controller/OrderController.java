package com.geektcp.alpha.sso.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tanghaiyang
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/list")
    public String list() {
        return "order/list";
    }

}
