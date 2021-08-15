package com.geektcp.alpha.db.es6.query;

import java.io.Serializable;

/**
 * Created by nagle on 2018/1/4.
 */
public class Aggregation implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String DEFAULT_STATS = "count";
    protected String field;
    protected QType type;
    protected String key;
    protected String stats = DEFAULT_STATS;

    public Aggregation() {
    }

    public Aggregation(String field, QType type) {
        this.field = field;
        this.type = type;
        this.key = field;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }
}
