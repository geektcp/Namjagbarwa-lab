package com.geektcp.alpha.design.pattern.iterator;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public interface Iterator<Item> {

    Item next();

    boolean hasNext();
}
