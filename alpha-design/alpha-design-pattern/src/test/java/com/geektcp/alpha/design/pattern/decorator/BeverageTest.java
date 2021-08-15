package com.geektcp.alpha.design.pattern.decorator;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class BeverageTest {

    @Test
    public void main() {
        Beverage beverage = new HouseBlend();
        beverage = new Mocha(beverage);
        beverage = new Milk(beverage);
        System.out.println(beverage.cost());
        Assert.assertTrue(true);
    }
}
