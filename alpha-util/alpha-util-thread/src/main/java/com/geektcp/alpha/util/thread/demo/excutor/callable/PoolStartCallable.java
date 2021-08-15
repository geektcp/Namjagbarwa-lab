package com.geektcp.alpha.util.thread.demo.excutor.callable;

/**
 * Created by Administrator on 2017/2/17.
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * 有返回值的线程
 */
@SuppressWarnings("unchecked")
public class PoolStartCallable {
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
            Callable r = new CallableImpl(i);
            Future f = pool.submit(r);

            list.add(f);
        }

        // 关闭线程池
        pool.shutdown();

        // 获取所有并发任务的运行结果
        for (Future f : list) {
            //传入Callable类型的类给submit时，可以从Future对象上获取任务的返回值，并输出到控制台
            System.out.println(">>>f.get() | " + f.get().toString());
        }

        Date date2 = new Date();
        System.out.println("----程序结束运行----，程序运行时间【"
                + (date2.getTime() - date1.getTime()) + "毫秒】");
    }
}





