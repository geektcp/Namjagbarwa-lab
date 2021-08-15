package pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author tanghaiyang on 2019/8/26.
 */
public class GuavaExecutorTest {
    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();

    private static ExecutorService pool = new ThreadPoolExecutor(
            5,
            200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024),
            namedThreadFactory,
            new ThreadPoolExecutor.AbortPolicy());

    private class SubThread implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getId());
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                //do nothing
            }
        }
    }

    @Test
    public void main() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            pool.execute(new SubThread());
        }
    }


    private static ExecutorService executor = Executors.newWorkStealingPool();

    @Test
    public void test1() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executor.execute(new SubThread());
        }
    }
}
