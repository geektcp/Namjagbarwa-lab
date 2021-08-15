package com.geektcp.alpha.design.pattern.nullobject;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class RealOperation extends AbstractOperation {
    @Override
    void request() {
        System.out.println("do something");
    }
}