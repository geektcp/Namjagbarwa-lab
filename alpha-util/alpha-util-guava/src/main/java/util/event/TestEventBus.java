package util.event;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.junit.Test;

import java.util.concurrent.Executors;

/**
 * @author tanghaiyang on 2019/7/22.
 */
/*
* EventBus是guava的一个已经实现好的单进程消息订阅机制。
* 有了它，一个服务内部就可以很方便实现消息的订阅发送，支持多个listener同时监听一个事件
* */
public class TestEventBus {



    @Test
    public void testReceiveEvent() throws Exception {

        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();

        eventBus.register(listener);

        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));
        eventBus.post(new TestEvent(400));

        System.out.println("LastMessage:"+listener.getLastMessage());

    }

    @Test
    public void testAysncEventBus() throws Exception{
        AsyncEventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(3));
        EventListener listener = new EventListener();
        eventBus.register(listener);
        eventBus.post(new TestEvent(100));
        Thread.sleep(10000);
        System.out.println("==");
    }
}