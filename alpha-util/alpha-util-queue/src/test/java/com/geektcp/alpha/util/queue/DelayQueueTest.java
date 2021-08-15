package com.geektcp.alpha.util.queue;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author tanghaiyang on 2019/8/25.
 * 队列特性: 无界阻塞队列
 * 线程安全机制: ReentrantLock(单lock:lock)
 *
 *
 */
public class DelayQueueTest {

    private static DelayQueue<DelayBean> queue = new DelayQueue<>();

    @Test
    public void addAndPoll(){
        DelayBean delayBean = new DelayBean();
        queue.add(delayBean);
        DelayBean element = queue.poll();
        System.out.println(element);
    }

    @Data
    private class DelayBean implements Delayed {
        private int age;
        private String name;

        @Override
        public long getDelay(TimeUnit unit) {
            return 0;
        }

        @Override
        public int compareTo(Delayed o) {
            return 0;
        }
    }
}
