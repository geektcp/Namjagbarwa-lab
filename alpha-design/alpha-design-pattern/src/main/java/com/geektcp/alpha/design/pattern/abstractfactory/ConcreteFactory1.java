package com.geektcp.alpha.design.pattern.abstractfactory;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class ConcreteFactory1 extends AbstractFactory {
    AbstractProductA createProductA() {
        return new ProductA1();
    }

    AbstractProductB createProductB() {
        return new ProductB1();
    }
}
