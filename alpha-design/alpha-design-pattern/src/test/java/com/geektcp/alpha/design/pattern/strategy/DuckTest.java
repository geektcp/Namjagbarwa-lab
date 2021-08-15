package com.geektcp.alpha.design.pattern.strategy;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class DuckTest {

    @Test
    public void main() {
        Duck duck = new Duck();
        duck.setQuackBehavior(new Squeak());
        duck.performQuack();
        duck.setQuackBehavior(new Quack());
        duck.performQuack();
        Assert.assertTrue(true);
    }
}
