package com.geektcp.alpha.db.es6.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nagle on 2018/1/4.
 */
public class RangeFilter extends Filter {

    private static final long serialVersionUID = 1L;
    private List<Range> ranges = new ArrayList<>();

    public RangeFilter() {
    }

    public RangeFilter(String field) {
        super(field, QType.RANGE);
    }

    public RangeFilter addRange(Object from, Object to) {
        this.ranges.add(new Range(from, to));
        return this;
    }

    public List<Range> getRanges() {
        return ranges;
    }

    public void setRanges(List<Range> ranges) {
        if (ranges == null) {
            return;
        }
        this.ranges = ranges;
    }

    public static class Range implements Serializable {
        private static final long serialVersionUID = 1L;
        private Object from;
        private Object to;
        private boolean includeUpper = true;
        private boolean includeLower = true;

        public Range() {
        }

        public Range(Object from, Object to) {
            this.from = from;
            this.to = to;
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

        public boolean isIncludeUpper() {
            return includeUpper;
        }

        public void setIncludeUpper(boolean includeUpper) {
            this.includeUpper = includeUpper;
        }

        public boolean isIncludeLower() {
            return includeLower;
        }

        public void setIncludeLower(boolean includeLower) {
            this.includeLower = includeLower;
        }
    }
}
