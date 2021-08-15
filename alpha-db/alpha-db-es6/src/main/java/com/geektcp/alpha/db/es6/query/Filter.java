package com.geektcp.alpha.db.es6.query;

import java.io.Serializable;

/**
 * Created by nagle on 2018/1/4.
 */
public class Filter implements Serializable {

    private static final long serialVersionUID = 1L;
    protected String field;
    protected QType type;
    protected boolean mustNot;

    public Filter() {
    }

    public Filter(String field, QType type) {
        this.field = field;
        this.type = type;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public QType getType() {
        return type;
    }

    public void setType(QType type) {
        this.type = type;
    }

    public boolean isMustNot() {
        return mustNot;
    }

    public void setMustNot(boolean mustNot) {
        this.mustNot = mustNot;
    }

}
