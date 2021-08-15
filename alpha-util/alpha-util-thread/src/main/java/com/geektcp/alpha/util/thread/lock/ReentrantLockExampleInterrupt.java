package com.geektcp.alpha.util.thread.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tanghaiyang on 2019/8/20.
 * lockInterruptibly支持中断
 */
@Slf4j
public class ReentrantLockExampleInterrupt implements Runnable{

    //重入锁ReentrantLock
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    int lock;
    public ReentrantLockExampleInterrupt(int lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if(lock==1){
                lock1.lockInterruptibly();
                log.info("等待1秒");
                Thread.sleep(1000);
                lock2.lockInterruptibly();
                log.info("线程1执行完毕: " + Thread.currentThread().getId());
            }else{
                lock2.lockInterruptibly();
                log.info("等待1秒");
                Thread.sleep(1000);
                lock1.lockInterruptibly();
                log.info("线程2执行完毕: " + Thread.currentThread().getId());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }finally{
            if(lock1.isHeldByCurrentThread()){
                lock1.unlock();//释放锁
            }
            if(lock2.isHeldByCurrentThread()){
                lock2.unlock();
            }
            log.info(Thread.currentThread().getId() + "：线程退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockExampleInterrupt r1 = new ReentrantLockExampleInterrupt(1);
        ReentrantLockExampleInterrupt r2 = new ReentrantLockExampleInterrupt(2);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        Thread.sleep(5000);
        log.info("主线程等待5s后");
        //t2线程被中断，放弃锁申请，释放已获得的lock2，这个操作使得t1线程顺利获得lock2继续执行下去；
        //若没有此段代码，t2线程没有中断，那么会出现t1获取lock1，请求lock2，而t2获取lock2，请求lock1的相互等待死锁情况
        t2.interrupt();
    }

}
