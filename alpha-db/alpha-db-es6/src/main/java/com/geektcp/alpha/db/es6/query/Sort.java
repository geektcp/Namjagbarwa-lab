package com.geektcp.alpha.db.es6.query;

import java.io.Serializable;

/**
 * Created by nagle on 2018/1/4.
 */
public class Sort implements Serializable {

    private static final long serialVersionUID = 1L;
    private String field;
    private Order order;

    public Sort() {
    }

    public Sort(String field, Order order) {
        super();
        this.field = field;
        this.order = order;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
