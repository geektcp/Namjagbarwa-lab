package com.geektcp.alpha.design.pattern.strategy;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("quack!");
    }
}
