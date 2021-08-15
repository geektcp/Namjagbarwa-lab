package com.geektcp.alpha.cache.util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author haiyang.tang on 11.27 027 18:27:09.
 */
public class GuavaCache {

    private GuavaCache() {
    }

    private static LoadingCache<String, AtomicLong> CACHE = CacheBuilder.newBuilder()
            .maximumSize(10000)
            .expireAfterAccess(30, TimeUnit.SECONDS)
            .build(new CacheLoader<String, AtomicLong>() {
                @Override
                public AtomicLong load(String s) {
                    return new AtomicLong(0);
                }
            });

    public static void put(String key, AtomicLong o) {
        CACHE.put(key, o);
    }

    public static AtomicLong incrementAndGet(String key) {
        AtomicLong value = get(key);
        value.incrementAndGet();
        return value;
    }

    public static AtomicLong get(String key) {
        AtomicLong value = CACHE.getIfPresent(key);
        if (Objects.isNull(value)) {
            return new AtomicLong(0);
        }
        return value;
    }

    public static List<String> listCache() {
        List<String> list = Lists.newArrayList();
        Set<String> keys = CACHE.asMap().keySet();
        try {
            for (String key : keys) {
                if (Objects.isNull(CACHE.getIfPresent(key))) {
                    continue;
                }
                list.add(key + " " + CACHE.getIfPresent(key));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }


}
