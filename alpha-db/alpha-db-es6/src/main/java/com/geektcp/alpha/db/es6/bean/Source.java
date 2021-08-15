package com.geektcp.alpha.db.es6.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by nagle on 2017/12/29.
 */
@Data
@NoArgsConstructor
public class Source implements Serializable {

    private String id;
    private Map<String, Object> source = new LinkedHashMap<>();

    public Source(String id) {
        this.id = id;
    }

    public Source addField(String field, Object value) {
        if (field != null) {
            source.put(field, value);
        }
        return this;
    }

    public void setSource(Map<String, Object> source) {
        if (source == null) {
            return;
        }
        this.source.clear();
        this.source.putAll(source);
    }
}
