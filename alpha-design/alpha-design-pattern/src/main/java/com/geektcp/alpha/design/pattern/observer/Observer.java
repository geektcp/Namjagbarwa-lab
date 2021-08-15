package com.geektcp.alpha.design.pattern.observer;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public interface Observer {
    void update(float temp, float humidity, float pressure);
}
