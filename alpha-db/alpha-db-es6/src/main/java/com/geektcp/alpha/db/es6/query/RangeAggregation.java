package com.geektcp.alpha.db.es6.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nagle on 2018/1/4.
 */
public class RangeAggregation extends Aggregation {
    private static final long serialVersionUID = 1L;
    private List<Range> ranges = new ArrayList<>();

    public RangeAggregation(String field) {
        super(field, QType.RANGE);
    }

    /**
     * Add a new range to this aggregation.
     *
     * @param key  the key to use for this range in the response
     * @param from the lower bound on the distances, inclusive
     * @param to   the upper bound on the distances, exclusive
     */
    public RangeAggregation addRange(String key, Object from, Object to) {
        ranges.add(new Range(key, from, to));
        return this;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<Range> getRanges() {
        return ranges;
    }

    public static class Range implements Serializable {
        private static final long serialVersionUID = 1L;
        private String key;
        private Object from;
        private Object to;

        public Range(String key, Object from, Object to) {
            this.key = key;
            this.from = from;
            this.to = to;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Object getFrom() {
            return from;
        }

        public void setFrom(Object from) {
            this.from = from;
        }

        public Object getTo() {
            return to;
        }

        public void setTo(Object to) {
            this.to = to;
        }
    }
}
