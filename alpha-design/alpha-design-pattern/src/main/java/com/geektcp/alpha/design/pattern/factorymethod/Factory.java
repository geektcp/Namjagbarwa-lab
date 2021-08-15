package com.geektcp.alpha.design.pattern.factorymethod;

import com.geektcp.alpha.design.pattern.simplefactory.Product;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public abstract class Factory {
    abstract public Product factoryMethod();
    public void doSomething() {
        Product product = factoryMethod();
        // do something with the product
    }
}
