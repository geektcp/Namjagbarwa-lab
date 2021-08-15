package com.geektcp.alpha.util.queue;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author tanghaiyang on 2019/8/25.
 *
 线程安全机制 :ReentrantLock(单lock:lock)
 队列特性: 无界阻塞队列

 描述:
 类似于LinkedBlockQueue,但其所含对象的排序不是FIFO,
 而是依据对象的自然排序顺序或者是构造函数的Comparator决定的顺序.
 每次插入元素的时候，都会进行自然顺序升序排列。

 PriorityBlockingQueue实际通过Object[]数组存储数据，
 也是基于最小二叉堆实现，使用基于CAS实现的自旋锁来控制队列的动态扩容，
 保证了扩容操作不会阻塞take操作的执行。
 */
public class PriorityBlockingQueueTest {


    private static PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<>();

    @Test
    public void addAndPoll(){
        queue.add("cccc");
        queue.add("aaaa");
        queue.add("bbbb");
        String element = queue.poll();
        System.out.println(element);
    }
}
