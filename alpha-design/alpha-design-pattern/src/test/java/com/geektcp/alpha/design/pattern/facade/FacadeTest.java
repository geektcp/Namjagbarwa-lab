package com.geektcp.alpha.design.pattern.facade;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class FacadeTest {

    @Test
    public void main() {
        Facade facade = new Facade();
        facade.watchMovie();
        Assert.assertTrue(true);
    }
}
