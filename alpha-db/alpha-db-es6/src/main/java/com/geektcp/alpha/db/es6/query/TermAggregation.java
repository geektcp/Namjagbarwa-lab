package com.geektcp.alpha.db.es6.query;

/**
 * Created by nagle on 2018/1/4.
 */
public class TermAggregation extends Aggregation {

    private static final long serialVersionUID = 1L;
    private int size = 30;
    private TermAggregation subAggregation;

    public TermAggregation(){
    }

    public TermAggregation(String field) {
        super(field, QType.TERM);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public TermAggregation getSubAggregation() {
        return subAggregation;
    }

    public void setSubAggregation(TermAggregation subAggregation) {
        this.subAggregation = subAggregation;
    }
}
