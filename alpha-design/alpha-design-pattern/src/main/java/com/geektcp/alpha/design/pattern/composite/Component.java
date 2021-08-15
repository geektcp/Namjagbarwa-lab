package com.geektcp.alpha.design.pattern.composite;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public abstract class Component {
    protected String name;

    public Component(String name) {
        this.name = name;
    }

    public void print() {
        print(0);
    }

    abstract void print(int level);

    abstract public void add(Component component);

    abstract public void remove(Component component);
}
