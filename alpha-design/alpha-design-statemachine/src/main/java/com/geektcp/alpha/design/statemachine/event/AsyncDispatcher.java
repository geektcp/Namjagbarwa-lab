package com.geektcp.alpha.design.statemachine.event;

import com.geektcp.alpha.design.statemachine.exception.BaseException;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步事件分发器，内部使用线程池来处理事件
 */
@Slf4j
public class AsyncDispatcher<T> implements Dispatcher {

    private Map<Class<? extends Enum>, EventHandler> eventHandlerMap;
    private ExecutorService executor;

    public AsyncDispatcher() {
        eventHandlerMap = new ConcurrentHashMap<>();
        this.executor = Executors.newSingleThreadExecutor();
    }

    public AsyncDispatcher(int threadSize) {
        eventHandlerMap = new ConcurrentHashMap<>();
        this.executor = Executors.newFixedThreadPool(threadSize);
    }

    public AsyncDispatcher(ExecutorService executor) {
        eventHandlerMap = new ConcurrentHashMap<>();
        this.executor = executor;
    }

    @Override
    public void register(Class<? extends Enum> eventType, EventHandler handler) {
        eventHandlerMap.put(eventType, handler);
    }

    public void  dispatch(final AbstractEvent event) {
        final EventHandler eventHandler = eventHandlerMap.get(event.getType().getClass());
        if (eventHandler == null) {
            throw new BaseException("no matched event handler;type=" + event.getType());
        }

        executor.execute(() -> {
            try {
                log.info("AsyncDispatcher handle event start;event=" + event);
                eventHandler.handle(event);
                log.info("AsyncDispatcher handle event end;event=" + event);
            } catch (Exception e) {
                log.error("AsyncDispatcher handle event exception;event=" + event);
            }
        });
    }

    @Override
    public void shutdown() {
        executor.shutdown();
        log.info("AsyncDispatcher shutdown");
    }
}
