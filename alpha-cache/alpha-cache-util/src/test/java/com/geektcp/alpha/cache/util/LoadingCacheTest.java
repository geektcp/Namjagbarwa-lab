package com.geektcp.alpha.cache.util;

import com.google.common.cache.*;

import java.util.concurrent.TimeUnit;

/**
 * @author tanghaiyang on 2019/9/26.
 */
public class LoadingCacheTest {


    /**
     * CacheBuilder 完全没有使用任何线程，它的listener是通过回调函数实现的
     * 下面的例子表明当达到listener的时间即第5s，listener函数依然没有执行，除非执行get方法，
     * 这是先执行钩子函数，然后返回get结果。
     * */
    public static void main(String[] args) throws Exception{
        System.out.println("thread id: " + Thread.currentThread().getId());
        ThyLoadingCache.CACHE.put("aaa", 55);

//        System.out.println(CACHE.get("aaa"));
        Thread.sleep(3000);System.out.println("过了3秒");

        System.out.println(ThyLoadingCache.CACHE.get("aaa"));

        Thread.sleep(1000); System.out.println("过了4秒");
        Thread.sleep(1000); System.out.println("过了5秒");
        Thread.sleep(1000); System.out.println("过了6秒");
        Thread.sleep(1000); System.out.println("过了7秒");
        Thread.sleep(1000); System.out.println("过了8秒");
//        System.out.println(CACHE.get("aaa"));

        System.out.println("dddddddddddd");
        System.out.println(ThyLoadingCache.CACHE.get("aaa"));
    }
}
