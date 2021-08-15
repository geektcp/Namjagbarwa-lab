package com.geektcp.alpha.util.thread.demo.excutor.callable;

/* Created by Haiyang on 2018/1/8. */

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


public class CallableImpl implements Callable<String> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private int acceptInt;

    public CallableImpl(int acceptInt) {
        this.acceptInt = acceptInt;
    }

    @Override
    public String call() throws Exception {
        // 任务阻塞 1 秒
        Thread.sleep(1000);
        logger.info("dffffffffff");
        return this.acceptInt + " append some chars and return it!";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = new CallableImpl(100);
        FutureTask<String> task = new FutureTask<String>(callable);
        long beginTime = System.currentTimeMillis();

        // 创建线程
        new Thread(task).start();
        // 调用get()阻塞主线程，反之，线程不会阻塞
        String result = task.get();
        long endTime = System.currentTimeMillis();
        System.out.println("hello : " + result);
        System.out.println("cast : " + (endTime - beginTime) / 1000 + " second!");
    }
}
