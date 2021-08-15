package com.geektcp.alpha.util.queue;

import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * @author tanghaiyang on 2019/8/25.
 队列特性: 无界非阻塞队列
 */
public class LinkedTransferQueueTest {

    private static LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();

    @Test
    public void addAndPoll(){
        queue.add("aaaa");
        String element = queue.poll();
        System.out.println(element);
    }
}
