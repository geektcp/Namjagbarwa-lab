package pool;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author tanghaiyang on 2019/8/20.
 ThreadPoolExecutor参数说明:
 corePoolSize：      线程池核心线程数，当线程数少于该值是就新建线程，如果大于该值并且队列没有满，就放到队列里面缓冲
 maximumPoolSize：   线程池最大数，只有当队列workQueue满了的时候才会判断当前线程数量是否超过该值，
                     没有超过就直接新建线程并执行,如果超过了就抛出异常

 keepAliveTime：     空闲线程存活时间

 unit：              时间单位

 workQueue：         线程池所使用的缓冲队列，当队列满了的时候，就判断当前线程数量是否超过maximumPoolSize

 threadFactory：     线程池创建线程使用的工厂

 handler：           线程池对拒绝任务的处理策略
 */
public class ThyThreadPoolExecutorTest {

    private static final int corePoolSize = 10;

    /**
     * Executors通过包装ThreadPoolExecutor，得到了若干线程池
     * Executors得到的线程池容易OOM，原因是都没有对线程数量上限进行限制
     */
    @Test
    public void ThreadPoolExecutorTest() {
        Executors.newCachedThreadPool();                // ThreadPoolExecutor maximumPoolSize为Integer.MAX_VALUE， oom
        Executors.newFixedThreadPool(corePoolSize);     // ThreadPoolExecutor LinkedBlockingQueue长度无限，oom
        Executors.newScheduledThreadPool(corePoolSize); // ThreadPoolExecutor maximumPoolSize为Integer.MAX_VALUE， oom
        Executors.newSingleThreadExecutor();            // ThreadPoolExecutor LinkedBlockingQueue长度无限，oom
        Executors.newSingleThreadScheduledExecutor();   // ScheduledThreadPoolExecutor maximumPoolSize为Integer.MAX_VALUE， oom
        Executors.newWorkStealingPool();                // ForkJoinPool 固定线程数量，不会因为线程过多导致oom
    }

    @Test
    public void PoolTest() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 3, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));
        //任务1
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("-------------helloworld_001---------------" + Thread.currentThread().getName());
                try {
                    //主线程睡2秒
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }
        });

        //任务2
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("-------------helloworld_002---------------" + Thread.currentThread().getName());
                try {
                    //主线程睡2秒
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }
        });

        // 任务3
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("-------------helloworld_003---------------" + Thread.currentThread().getName());
                countDownLatch.countDown();
            }
        });

        // 任务4
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("-------------helloworld_004---------------" + Thread.currentThread().getName());
                countDownLatch.countDown();
            }
        });

        // 任务5
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("-------------helloworld_005---------------" + Thread.currentThread().getName());
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }




}
