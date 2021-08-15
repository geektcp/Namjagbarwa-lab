package com.geektcp.alpha.design.pattern.prototype;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class ConcretePrototype extends Prototype {

    private String filed;

    public ConcretePrototype(String filed) {
        this.filed = filed;
    }

    @Override
    Prototype myClone() {
        return new ConcretePrototype(filed);
    }

    @Override
    public String toString() {
        return filed;
    }
}
