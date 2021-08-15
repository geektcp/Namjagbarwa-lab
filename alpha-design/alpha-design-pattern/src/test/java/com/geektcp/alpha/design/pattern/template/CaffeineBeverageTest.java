package com.geektcp.alpha.design.pattern.template;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class CaffeineBeverageTest {

    @Test
    public void main() {
        CaffeineBeverage caffeineBeverage = new Coffee();
        caffeineBeverage.prepareRecipe();
        System.out.println("-----------");
        caffeineBeverage = new Tea();
        caffeineBeverage.prepareRecipe();
        Assert.assertTrue(true);
    }
}
