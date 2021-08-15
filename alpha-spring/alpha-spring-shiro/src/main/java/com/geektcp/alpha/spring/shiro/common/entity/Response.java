package com.geektcp.alpha.spring.shiro.common.entity;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * @author tanghaiyang
 */
public class Response extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public Response code(HttpStatus status) {
        this.put("code", status.value());
        return this;
    }

    public Response message(String message) {
        this.put("message", message);
        return this;
    }

    public Response data(Object data) {
        this.put("data", data);
        return this;
    }

    public Response success() {
        this.code(HttpStatus.OK);
        return this;
    }

    public Response fail() {
        this.code(HttpStatus.INTERNAL_SERVER_ERROR);
        return this;
    }

    @Override
    public Response put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
