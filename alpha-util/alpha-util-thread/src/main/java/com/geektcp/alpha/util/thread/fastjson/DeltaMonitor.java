package com.geektcp.alpha.util.thread.fastjson;

import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by sshss on 2017/2/18.
 */
/*
* 结论，下面这个类的测试结果发现，多线程中修改Map是没有任何问题，
* 之前的logserver监控统计kafka的数据之所以不准确，是因为每次把结果update到zookeeper里面，zookeeper的性能的存在严重问题。
* 高并发的情况不要频繁写入zookeeper
* */
@Slf4j
public class DeltaMonitor extends  Thread {

    public static boolean isStart = true;

    // default 10s
    public int deltaMonitorInterval = 5;

    private static AtomicInteger isLock = new AtomicInteger(0);

//    private static JSONObject monitorTable = new JSONObject();

//    private static volatile Hashtable<String, AtomicLong>[] monitor = new Hashtable[2];
    private static  Hashtable<String, AtomicLong>[] monitor = new Hashtable[2];
    private static int currentIndex = 0;

    public DeltaMonitor(){
        monitor[currentIndex] = new Hashtable<>();
        monitor[1-currentIndex] = new Hashtable<>();
    }

    /*
    * 这个方法在多线程消费kafka时会有同步问题，目前不会
    * fastjson内部使用linkhashmap或者hashmap，都不是线程安全的
    * */
    public static synchronized void addMonitorTable(String logName){
//        isLock.incrementAndGet();
        Hashtable<String, AtomicLong> monitorTable = getCurrentMonitor();

        AtomicLong offset = new AtomicLong(1);
        Object offsetObject = monitorTable.get(logName);
        if(offsetObject != null){
            offset = (AtomicLong)offsetObject;
            offset.getAndIncrement();
        }

        monitorTable.put(logName, offset);
//        isLock.decrementAndGet();

//        log.debug("isLock: {}, logName: {} | offset++: {} | monitorTable: {}", isLock, logName, offset, monitorTable);
    }


    private static synchronized Hashtable<String, AtomicLong> getCurrentMonitor(){
        return monitor[currentIndex];
    }

//    private static ConcurrentHashMap getIdleMonitor(){
//        return monitor[1 - currentIndex];
//    }

    private static synchronized void swap(){
//        JSONObject tempMonitorTable = monitor[currentIndex];
//        monitor[currentIndex] = monitor[1-currentIndex];
//        monitor[1-currentIndex] = tempMonitorTable;
        currentIndex = 1 - currentIndex;
    }

    /*
    * 消除高并发时异常 java.util.ConcurrentModificationException
    * */
    public void run() {
        while (isStart) {
            try {
                Thread.sleep(deltaMonitorInterval * 1000);

                Hashtable<String, AtomicLong> monitorTable = getCurrentMonitor();

                // com.geektcp.alpha.util.thread.cas
//                swap();

//                log.info("thread id: {} | monitorTable.size {} | monitorTable: {} ",
//                        Thread.currentThread().getId(), monitorTable.size(), monitorTable.toString());
//                ZKUtitl.updateTableStatistics(monitorTable);

                log.info("monitorTable: {}", monitorTable);
//                monitorTable.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
