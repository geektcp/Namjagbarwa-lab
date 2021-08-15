package com.geektcp.alpha.util.queue;

import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author tanghaiyang on 2019/8/25.
 线程安全机制: ReentrantLock(双lock:takeLock,putLock)
 队列特性: 无界阻塞队列
 描述:
 大小不定的BlockingQueue, 若其构造函数带一个规定大小的参数,
 生成的 BlockingQueue 有大小限制, 若不带大小参数,
 所生成的 BlockingQueue 的大小由Integer.MAX_VALUE来决定.
 其所含的对象是以FIFO(先入先出)顺序排序的
 */
public class LinkedBlockingQueueTest {


    private static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

    @Test
    public void addAndPoll(){
        queue.add("aaaa");
        String element = queue.poll();
        System.out.println(element);
    }
}
