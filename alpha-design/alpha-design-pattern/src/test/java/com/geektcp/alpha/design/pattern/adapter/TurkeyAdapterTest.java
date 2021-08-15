package com.geektcp.alpha.design.pattern.adapter;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class TurkeyAdapterTest {

    @Test
    public static void main() {
        Turkey turkey = new WildTurkey();
        Duck duck = new TurkeyAdapter(turkey);
        duck.quack();
        Assert.assertTrue(true);
    }
}
