package com.geektcp.alpha.db.es6.query;

import java.io.Serializable;

/**
 * Created by nagle on 2018/1/5.
 */
public class BoostField implements Serializable{

    private static final long serialVersionUID = 1L;
    protected String field;
    protected float boost = 1.0F;
    protected QType type = QType.MATCH;

    public BoostField() {
    }

    public BoostField(String field) {
        this(field, 1.0F);
    }

    public BoostField(String field, float boost) {
        this.field = field;
        this.boost = boost <= 0 ? 1.0F : boost;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public float getBoost() {
        return boost;
    }

    public void setBoost(float boost) {
        this.boost = boost;
    }

    public QType getType() {
        return type;
    }

    public void setType(QType type) {
        this.type = type;
    }
}
