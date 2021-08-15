package com.geektcp.alpha.util.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tanghaiyang on 2019/8/19.
 * 只用一把ReentrantLock锁，锁住多个线程，而每个线程可以通过使用同一个lock的不同的Condition对象，来分别进行等待和唤醒
 * 这点用synchronized做不到，因为synchronized锁对应Object，只有一个，不能new出多个Object来锁定同一组线程
 */
public class ReentrantLockExampleAwait {

    private static ReentrantLock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();
    private static Condition condition2 = lock.newCondition();

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                lock.lock();//请求锁
                try {
                    System.out.println(Thread.currentThread().getName() + "==》进入等待");
                    condition.await();//设置当前线程进入等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();//释放锁
                }
                System.out.println(Thread.currentThread().getName() + "==》继续执行");
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                lock.lock();//请求锁
                try {
                    System.out.println(Thread.currentThread().getName() + "=》进入");
                    Thread.sleep(2000);//休息2秒
                    condition.signal();//随机唤醒等待队列中的一个线程
                    System.out.println(Thread.currentThread().getName() + "休息结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();//释放锁
                }
            }
        }.start();
    }

}
