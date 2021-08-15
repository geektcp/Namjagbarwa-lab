package com.geektcp.alpha.db.es6.query;

/**
 * Created by nagle on 2018/1/5.
 */
public class ExistsField extends BoostField{

    private static final long serialVersionUID = 1L;
    /* mustNot=true -> mustNot exists field(missing field) */
    private boolean mustNot;

    public ExistsField() {
        this.type = QType.EXISTS;
    }

    public ExistsField(String field) {
        super(field);
        this.type = QType.EXISTS;
    }

    public ExistsField(String field, float boost) {
        this(field, boost, false);
    }

    public ExistsField(String field, float boost, boolean mustNot) {
        super(field, boost);
        this.mustNot = mustNot;
        this.type = QType.EXISTS;
    }

    public boolean isMustNot() {
        return mustNot;
    }

    public void setMustNot(boolean mustNot) {
        this.mustNot = mustNot;
    }
}
