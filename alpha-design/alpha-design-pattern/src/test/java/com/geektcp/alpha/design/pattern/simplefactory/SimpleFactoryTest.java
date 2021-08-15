package com.geektcp.alpha.design.pattern.simplefactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class SimpleFactoryTest {

    @Test
    public void main() {
        SimpleFactory simpleFactory = new SimpleFactory();
        Product product = simpleFactory.createProduct(1);
        // do something with the product
        Assert.assertTrue(true);
    }
}
