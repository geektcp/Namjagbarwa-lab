package com.geektcp.alpha.util.thread.demo.excutor.runnable;

/**
 * Created by Administrator on 2017/2/17.
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 有返回值的线程
 */
@SuppressWarnings("unchecked")
public class PoolStartRunnable {
    public static void main(String[] args) throws ExecutionException,
            InterruptedException {
        System.out.println("----程序开始运行----");
        Date date1 = new Date();
        int taskSize = 10;

        if(args.length == 1) {
            taskSize = Integer.parseInt(args[0]);
        }

        System.out.println("-------- taskSize --------: " + taskSize);

        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);

        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < taskSize; i++) {
            Runnable r = new RunnableImpl(i);
            pool.submit(r);
        }


        // 关闭线程池
        pool.shutdown();
        while (true){
            if( pool.isTerminated()) {
                System.out.println("任务完成关闭线程池");
                break;
            }else {
                Thread.sleep(1000);
            }
        }

        System.out.println("pool: " + pool);
        System.out.println(pool.isShutdown());
        System.out.println(pool.isTerminated());

        Date date2 = new Date();
        System.out.println("----程序结束运行----，程序运行时间【"
                + (date2.getTime() - date1.getTime()) + "毫秒】");
    }
}





