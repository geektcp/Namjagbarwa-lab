package com.geektcp.alpha.design.pattern.simplefactory;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class SimpleFactory {

    public Product createProduct(int type) {
        if (type == 1) {
            return new ConcreteProduct1();
        } else if (type == 2) {
            return new ConcreteProduct2();
        }
        return new ConcreteProduct();
    }
}
