package com.geektcp.alpha.util.thread.fastjson;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sshss on 2017/2/14.
 */

@Slf4j
public class ServerMonitor extends Thread {
    public static boolean isStart = true;

    // default 10s
    public int serverMonitorInterval = 10;

    private static AtomicInteger sendLogSucc = new AtomicInteger(0);
    private static AtomicInteger sendLogFail = new AtomicInteger(0);
    private static AtomicInteger getConfSucc = new AtomicInteger(0);
    private static AtomicInteger getConfFail = new AtomicInteger(0);
    private static AtomicInteger kafkaSendSucc = new AtomicInteger(0);
    private static AtomicInteger kafkaSendFail = new AtomicInteger(0);

    public static void addSendLogSucc() {
        sendLogSucc.incrementAndGet();
    }

    public static void addSendLogFail() {
        sendLogFail.incrementAndGet();
    }

    public static void addGetConfSucc() {
        getConfSucc.incrementAndGet();
    }

    public static void addGetConfFail() {
        getConfFail.incrementAndGet();
    }

    public static void addKafkaSendSucc() {
        kafkaSendSucc.incrementAndGet();
    }

    public static void addKafkaSendFail() {
        kafkaSendFail.incrementAndGet();
    }

    public void run() {
        while (isStart) {
            try {
                Thread.sleep(serverMonitorInterval * 1000);
            } catch (Exception e) { e.printStackTrace(); }

            String conversion = String.format("%.2f", 100 * sendLogSucc.doubleValue() / kafkaSendSucc.doubleValue());
            String sendlogSpeed = String.format("%.2f",  sendLogSucc.doubleValue() / serverMonitorInterval);
            String sendconfSpeed = String.format("%.2f", getConfSucc.doubleValue() / serverMonitorInterval);

            log.info("thread id: {} | ReceiveLogSucc: {} | ReceiveLogFail: {} || getConfSucc: {} | getConfFail: {} || kafkaSendSucc: {} | kafkaSendFail: {} " +
                    "| conversion: {}% | sendlogSpeed: {}/s | sendconfSpeed: {}/s",
                    Thread.currentThread().getId(), sendLogSucc , sendLogFail, getConfSucc, getConfFail, kafkaSendSucc, kafkaSendFail, conversion, sendlogSpeed, sendconfSpeed);

//            clear();
        }
    }

    private void clear(){
        sendLogSucc.set(0);
        sendLogFail.set(0);
        getConfSucc.set(0);
        getConfFail.set(0);
        kafkaSendSucc.set(0);
        kafkaSendFail.set(0);
    }

}
