package com.geektcp.alpha.design.pattern.builder;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class StringBuilder extends AbstractStringBuilder {
    public StringBuilder() {
        super(16);
    }

    @Override
    public String toString() {
        // Create a copy, don't share the array
        return new String(value, 0, count);
    }
}
