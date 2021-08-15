package com.geektcp.alpha.util.base.map;

import java.util.Map;

/**
 * @author tanghaiyang on 2019/8/14.
 */
public class EntryMap<K,V> implements Map.Entry<K,V>{

    private int size;
    private K key;
    private V value;

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        this.value = value;
        return value;
    }

}
