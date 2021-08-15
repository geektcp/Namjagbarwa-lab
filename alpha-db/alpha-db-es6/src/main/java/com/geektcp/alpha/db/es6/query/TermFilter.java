package com.geektcp.alpha.db.es6.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nagle on 2018/1/4.
 */
public class TermFilter extends Filter {

    private static final long serialVersionUID = 1L;
    private List<Object> values = new ArrayList<>();

    public TermFilter() {
    }

    public TermFilter(String field) {
        super(field, QType.TERM);
    }

    public TermFilter addValues(Object... values) {
        this.values.addAll(Arrays.asList(values));
        return this;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        if (values == null) {
            return;
        }
        this.values = values;
    }
}
