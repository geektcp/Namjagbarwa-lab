package com.geektcp.alpha.design.pattern.decorator;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class Milk extends CondimentDecorator {

    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return 1 + beverage.cost();
    }
}
