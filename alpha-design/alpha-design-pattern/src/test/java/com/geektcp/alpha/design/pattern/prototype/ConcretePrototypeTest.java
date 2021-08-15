package com.geektcp.alpha.design.pattern.prototype;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class ConcretePrototypeTest {

    @Test
    public void main() {
        Prototype prototype = new ConcretePrototype("abc");
        Prototype clone = prototype.myClone();
        System.out.println(clone.toString());
        Assert.assertTrue(true);
    }
}
