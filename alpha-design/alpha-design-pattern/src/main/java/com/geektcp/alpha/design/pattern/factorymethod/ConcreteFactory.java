package com.geektcp.alpha.design.pattern.factorymethod;

import com.geektcp.alpha.design.pattern.factorymethod.Factory;
import com.geektcp.alpha.design.pattern.simplefactory.ConcreteProduct;
import com.geektcp.alpha.design.pattern.simplefactory.Product;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class ConcreteFactory extends Factory {
    public Product factoryMethod() {
        return new ConcreteProduct();
    }
}
