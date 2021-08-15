package com.geektcp.alpha.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tanghaiyang on 2019/7/14.
 */
@Data
public class SerializeObject implements Serializable {
    private String name;
    private int age;
    private String city;

    public static SerializeObject build(){
        return new SerializeObject();
    }
    public SerializeObject name(String name) {
        this.name = name;
        return this;
    }

    public SerializeObject age(int age) {
        this.age = age;
        return this;
    }

    public SerializeObject city(String city) {
        this.city = city;
        return this;
    }
}
