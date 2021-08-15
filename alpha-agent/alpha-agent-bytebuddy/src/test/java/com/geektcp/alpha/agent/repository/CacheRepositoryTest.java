package com.geektcp.alpha.agent.repository;//package com.geektcp.alpha.agent.repository;//package com.geektcp.alpha.agent.repository;
//
//import com.google.common.cache.*;
//import com.google.common.collect.Lists;
//
//import java.util.List;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * @author haiyang.tang on 11.29 029 10:26:03.
// */
//public class CacheRepositoryTest {
//
//    private static RemovalListener<String, AtomicInteger> myRemovalListener = new RemovalListener<String, AtomicInteger>() {
//        @Override
//        public void onRemoval(RemovalNotification<String, AtomicInteger> notification) {
//            String tips = String.format("key=%s,value=%s,reason=%s in myRemovalListener", notification.getKey(), notification.getValue(), notification.getCause());
//            System.out.println(tips);
//            System.out.println("onRemoval thread id: " + Thread.currentThread().getId());
//            //when expireAfterAccess to do
//            if (notification.getCause().equals(RemovalCause.EXPIRED) && notification.getValue() != null) {
//                System.out.printf("Remove %s in cacheConnection", notification.getKey());
//            }
//        }
//    };
//
//    private static LoadingCache<String, AtomicInteger> CACHE = CacheBuilder.newBuilder()
//            .refreshAfterWrite(7, TimeUnit.SECONDS)
//            .expireAfterWrite(5, TimeUnit.SECONDS)
//            .removalListener(myRemovalListener)
//            .build(new CacheLoader<String, AtomicInteger>() {
//                @Override
//                public AtomicInteger load( String key) throws Exception {
//                    return new AtomicInteger(0);
//                }
//            });
//
//
//    public static void main(String[] args)throws Exception {
//        CACHE.put("aaa", new AtomicInteger(1));
//        CACHE.put("bbb", new AtomicInteger(2));
//        CACHE.put("cc", new AtomicInteger(3));
//        CACHE.put("dddd", new AtomicInteger(4));
//
////        CACHE.cleanUp();
//        List<String> list = Lists.newArrayList();
//        Set<String> keys = CACHE.asMap().keySet();
//        for(String key: keys){
//            System.out.println(CACHE.get(key));
//            list.add(key + " " + CACHE.get(key));
//        }
//
//        Thread.sleep(5000);
//
//        List<String> list2 = Lists.newArrayList();
//        Set<String> keys2 = CACHE.asMap().keySet();
//        for(String key: keys){
//            System.out.println(CACHE.get(key));
//            list2.add(key + " " + CACHE.get(key));
//        }
//        System.out.println(list2.size());
//    }
//
//}
