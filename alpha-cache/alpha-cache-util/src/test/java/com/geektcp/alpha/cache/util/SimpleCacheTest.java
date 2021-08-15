package com.geektcp.alpha.cache.util;

/**
 * @author tanghaiyang on 2019/9/26.
 */
public class SimpleCacheTest {

    public static void main(String[] args) {
        SimpleCache.set("testKey_1", "testValue_1");
        SimpleCache.set("testKey_2", "testValue_2", 1);
        SimpleCache.set("testKey_3", "testValue_3");
        SimpleCache.set("testKey_4", "testValue_4", 1);
        Object testKey_2 = SimpleCache.get("testKey_2");
        System.out.println(testKey_2);

    }

}
