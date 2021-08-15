package com.geektcp.alpha.design.pattern.simplefactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tanghaiyang on 2019/9/21.
 */
@Slf4j
public class ConcreteProduct implements Product {

    public ConcreteProduct(){
        log.info("build ConcreteProduct!");
    }

}
