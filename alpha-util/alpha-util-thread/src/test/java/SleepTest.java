import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tanghaiyang on 2019/8/25.
 */
public class SleepTest {

    @Test
    public void sleep()throws Exception{
        System.out.println(0);
        Thread.sleep(0);
        System.out.println(111111);
        String lock = "com/geektcp/alpha/util/thread/lock";

        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition =  reentrantLock.newCondition();
        reentrantLock.lock();
        try{
            reentrantLock.lock();
            condition.await(0, TimeUnit.SECONDS);
            System.out.println(33333);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(444444);
        LockSupport.parkNanos(0);   // 1纳秒等于十亿分之一秒
        System.out.println(555555);

        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await();

    }
}
