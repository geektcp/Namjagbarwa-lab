package com.geektcp.alpha.design.pattern.nullobject;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class AbstractOperationTest {

    @Test
    public void main() {
        AbstractOperation abstractOperation = func(-1);
        abstractOperation.request();
        Assert.assertTrue(true);
    }

    private AbstractOperation func(int para) {
        if (para < 0) {
            return new NullOperation();
        }
        return new RealOperation();
    }
}
