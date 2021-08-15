package com.geektcp.alpha.util.base.proxy.example.subject;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tanghaiyang on 2019/8/15.
 */
@Slf4j
public class RealSubject implements Subject {

    public void doSomething() {
        log.info("call doSomething()");
    }

}
