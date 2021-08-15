package com.geektcp.alpha.design.pattern.visitor;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public interface Visitor {
    void visit(Customer customer);

    void visit(Order order);

    void visit(Item item);
}
