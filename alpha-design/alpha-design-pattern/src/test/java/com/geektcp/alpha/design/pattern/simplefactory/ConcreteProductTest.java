package com.geektcp.alpha.design.pattern.simplefactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class ConcreteProductTest {

    @Test
    public void main() {
        int type = 1;
        Product product;
        if (type == 1) {
            product = new ConcreteProduct1();
        } else if (type == 2) {
            product = new ConcreteProduct2();
        } else {
            product = new ConcreteProduct();
        }
        // do something with the product
        Assert.assertTrue(true);
    }
}
