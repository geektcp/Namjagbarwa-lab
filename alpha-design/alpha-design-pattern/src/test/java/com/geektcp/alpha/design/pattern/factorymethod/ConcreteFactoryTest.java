package com.geektcp.alpha.design.pattern.factorymethod;

import com.geektcp.alpha.design.pattern.simplefactory.Product;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class ConcreteFactoryTest {

    @Test
    public void main() {
        ConcreteFactory concreteFactory = new ConcreteFactory();
        Product product = concreteFactory.factoryMethod();
        Assert.assertTrue(true);
    }
}
