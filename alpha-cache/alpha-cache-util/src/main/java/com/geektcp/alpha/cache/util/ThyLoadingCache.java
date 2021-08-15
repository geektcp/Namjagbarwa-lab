package com.geektcp.alpha.cache.util;

import com.google.common.cache.*;

import java.util.concurrent.TimeUnit;

/**
 * @author tanghaiyang on 2019/11/30 0:59.
 */
public class ThyLoadingCache {

    private static RemovalListener<String, Integer> myRemovalListener = new RemovalListener<String, Integer>() {
        @Override
        public void onRemoval(RemovalNotification<String, Integer> notification) {
            String tips = String.format("key=%s,value=%s,reason=%s in myRemovalListener", notification.getKey(), notification.getValue(), notification.getCause());
            System.out.println(tips);
            System.out.println("onRemoval thread id: " + Thread.currentThread().getId());
            //when expireAfterAccess to do
            if (notification.getCause().equals(RemovalCause.EXPIRED) && notification.getValue() != null) {
                System.out.printf("Remove %s in cacheConnection\n", notification.getKey());
            }
        }
    };

    /**
     * expireAfterAccess是指定项在一定时间内没有读写，会移除该key，下次取的时候从loading中取
     * expireAfterWrite  will clean cache and get default 是在指定项在一定时间内没有创建/覆盖时，会移除该key，下次取的时候从loading中取
     * refreshAfterWrite will delay old cache 是在指定时间内没有被创建/覆盖，则指定时间过后，再次访问时，会去刷新该缓存，在新值没有到来之前，始终返回旧值
     * refreshAfterWrite跟expireAfterWrite的区别是，指定时间过后，expireAfterWrite是remove该key，下次访问是同步去获取返回新值
     * 而refresh则是指定时间后，不会remove该key，下次访问会触发刷新，新值没有回来时返回旧值
     * */
    public static LoadingCache<String, Integer> CACHE = CacheBuilder.newBuilder()
            .refreshAfterWrite(7, TimeUnit.SECONDS)
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .removalListener(myRemovalListener)
            .build(new CacheLoader<String, Integer>() {
                @Override
                public Integer load(String key) throws Exception {
                    return 100;
                }
            });

}
