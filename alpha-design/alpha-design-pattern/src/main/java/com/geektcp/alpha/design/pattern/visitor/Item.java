package com.geektcp.alpha.design.pattern.visitor;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class Item implements Element {

    private String name;

    Item(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
