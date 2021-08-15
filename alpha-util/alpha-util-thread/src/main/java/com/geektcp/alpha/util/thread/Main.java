package com.geektcp.alpha.util.thread;

import com.geektcp.alpha.util.thread.fastjson.DeltaMonitor;
import com.geektcp.alpha.util.thread.fastjson.MyThread;
import com.geektcp.alpha.util.thread.fastjson.MyThread2;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tanghaiyang on 2019/2/25.
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
//        ServerMonitor serverWatcher = new ServerMonitor();
//        serverWatcher.serverMonitorInterval = 5;
//        serverWatcher.start();

        DeltaMonitor deltaMonitor = new DeltaMonitor();
        deltaMonitor.start();

        MyThread h1 = new MyThread("A");
        MyThread h2 = new MyThread("B");
        MyThread h3 = new MyThread("C");
        MyThread2 h4 = new MyThread2("D");
        MyThread2 h5 = new MyThread2("E");
        MyThread2 h6 = new MyThread2("F");

        log.info("==============");
        h1.start();
        log.info("==============");
        h2.start();
        log.info("==============");
        h3.start();
        log.info("==============");
        h4.start();
        log.info("==============");
        h5.start();
        log.info("==============");
        h6.start();

        try {
            h1.join();
            h2.join();
            h3.join();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
