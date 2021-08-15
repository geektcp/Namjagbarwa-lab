package com.geektcp.alpha.util.queue;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author tanghaiyang on 2019/8/25.
 * 队列特性: 无界非阻塞队列
 * 线程安全机制: cas(UNSAFE.compareAndSwapObject)
 *
 * ConcurrentLinkedQueue是一个基于链接节点的无界线程安全队列，
 它采用先进先出的规则对节点进行排序，当我们添加一个元素的时候，
 它会添加到队列的尾部，当我们获取一个元素时，它会返回队列头部的元素。

 线程安全机制是基于CAS：
 private boolean casHead(Node<E> cmp, Node<E> val) {
 return UNSAFE.compareAndSwapObject(this, headOffset, cmp, val);
 }
 */
public class ConcurrentLinkedQueueTest {

    private static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

    @Test
    public void addAndPoll(){
        queue.add("aaaa");
        String element = queue.poll();
        System.out.println(element);
    }

}
