package com.geektcp.alpha.design.pattern.template;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class Coffee extends CaffeineBeverage {
    @Override
    void brew() {
        System.out.println("Coffee.brew");
    }

    @Override
    void addCondiments() {
        System.out.println("Coffee.addCondiments");
    }
}
