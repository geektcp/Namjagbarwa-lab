package com.geektcp.alpha.design.pattern.abstractfactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class AbstractFactoryTest {

    @Test
    public void main() {
        AbstractFactory abstractFactory = new ConcreteFactory1();
        AbstractProductA productA = abstractFactory.createProductA();
        AbstractProductB productB = abstractFactory.createProductB();
        // do something with productA and productB
        Assert.assertTrue(true);
    }
}
