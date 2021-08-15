package com.geektcp.alpha.db.es6.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by nagle on 2018/1/11.
 */
@Data
@NoArgsConstructor
public class Field {

    private String name;
    private Object value;
    private UpdateMode updateMode;

    public Field(String name, Object value) {
        this(name, value, UpdateMode.REPLACE);
    }

    public Field(String name, Object value, UpdateMode updateMode) {
        this.name = name;
        if (value instanceof BigDecimal) {
            value = ((BigDecimal) value).doubleValue();
        }
        this.value = value;
        this.updateMode = updateMode == null ? UpdateMode.REPLACE : updateMode;
    }

    @Override
    public String toString() {
        return "Field [name=" + name + ", value=" + value + ", updateMode=" + updateMode + "]";
    }
}
