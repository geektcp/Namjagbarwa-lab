package com.geektcp.alpha.util.base.map;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author tanghaiyang on 2019/8/2.
 * 验证基础数据类型
 * 继承的执行顺序
 * CountDownLatch的执行效果
 */
@Slf4j
public class JavaUtil {

    @Test
    public void countDownLatchTest() throws Throwable{
        final int totalThread = 10;
        CountDownLatch countDownLatch = new CountDownLatch(totalThread);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalThread; i++) {
            executorService.execute(() -> {
                log.info("run..");
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        log.info("end");
        executorService.shutdown();
        Assert.assertTrue(true);
    }

    @Test
    public void cyclicBarrierTest(){
        final int totalThread = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(totalThread);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalThread; i++) {
            executorService.execute(() -> {
                log.info("before..");
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                log.info("after..");
            });
        }
        executorService.shutdown();
        Assert.assertTrue(true);
    }

    @Test
    public void semaphoreTest(){
        final int clientCount = 3;
        final int totalRequestCount = 10;
        Semaphore semaphore = new Semaphore(clientCount);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalRequestCount; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    log.info(semaphore.availablePermits() + " ");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }
        executorService.shutdown();
        Assert.assertTrue(true);
    }

    class A {

        public void show(A obj) {
            log.info("A.show(A)");
        }

        public void show(C obj) {
            log.info("A.show(C)");
        }
    }

    class B extends A {

        @Override
        public void show(A obj) {
            log.info("B.show(A)");
        }
    }

    class C extends B {
    }

    class D extends C {
    }


    /**
     * 执行顺序
     * this.func(this)
     * super.func(this)
     * this.func(super)
     * super.func(super)
     */
    @Test
    public void testClass() {
        A a = new A();
        B b = new B();
        C c = new C();
        D d = new D();

        // 在 A 中存在 show(A obj)，直接调用
        a.show(a); // A.show(A)
        // 在 A 中不存在 show(B obj)，将 B 转型成其父类 A
        a.show(b); // A.show(A)
        // 在 B 中存在从 A 继承来的 show(C obj)，直接调用
        b.show(c); // A.show(C)
        // 在 B 中不存在 show(D obj)，但是存在从 A 继承来的 show(C obj)，将 D 转型成其父类 C
        b.show(d); // A.show(C)

        // 引用的还是 B 对象，所以 ba 和 b 的调用结果一样
        A ba = new B();
        ba.show(c); // A.show(C)
        ba.show(d); // A.show(C)
    }

    @Test
    public void testObject(){
        HashMap<String,String> map = new HashMap<>();
        WeakReference<HashMap<String,String>> wf = new WeakReference<>(map);
        HashMap<String,String> map2 = wf.get();
        if(Objects.isNull(map2)){
            return;
        }
        map2.put("key", "value");
        Assert.assertTrue(true);
    }

}
