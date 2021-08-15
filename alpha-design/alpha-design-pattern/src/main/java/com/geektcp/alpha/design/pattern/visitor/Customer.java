package com.geektcp.alpha.design.pattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class Customer implements Element {

    private String name;
    private List<Order> orders = new ArrayList<>();

    Customer(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    void addOrder(Order order) {
        orders.add(order);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (Order order : orders) {
            order.accept(visitor);
        }
    }
}
