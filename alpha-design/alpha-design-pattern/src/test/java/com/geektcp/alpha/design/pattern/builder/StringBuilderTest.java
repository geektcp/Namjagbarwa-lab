package com.geektcp.alpha.design.pattern.builder;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class StringBuilderTest {

    @Test
    public void main() {
        StringBuilder sb = new StringBuilder();
        final int count = 26;
        for (int i = 0; i < count; i++) {
            sb.append((char) ('a' + i));
        }
        System.out.println(sb.toString());
        Assert.assertTrue(true);
    }

}
