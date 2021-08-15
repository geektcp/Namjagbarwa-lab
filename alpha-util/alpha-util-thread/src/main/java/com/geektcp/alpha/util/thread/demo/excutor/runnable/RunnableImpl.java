package com.geektcp.alpha.util.thread.demo.excutor.runnable;


import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2017/2/17.
 */
public class RunnableImpl implements Runnable {
    private int taskNum;

    public RunnableImpl(int taskNum) {
        this.taskNum = taskNum;
    }

    public void run()  {
        System.out.println(">>> " + taskNum + " 任务启动");
        Date dateTmp1 = new Date();
        try {
            System.out.println( UUID.randomUUID() );
            Thread.sleep(1000);
        }catch (Exception e){
            //do nothing
        }
        Date dateTmp2 = new Date();
        long time = dateTmp2.getTime() - dateTmp1.getTime();
        System.out.println(">>>" + taskNum + "任务终止");

    }

    public static void main(String[] args) {
        Runnable runnable = new RunnableImpl(1024);
        long beginTime = System.currentTimeMillis();

        new Thread(runnable).start();
        long endTime = System.currentTimeMillis();
        System.out.println("cast : " + (endTime - beginTime) / 1000 + " second!");
    }

}

