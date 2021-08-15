package com.geektcp.alpha.design.statemachine.event;

import com.geektcp.alpha.design.statemachine.exception.BaseException;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 同步事件分发器
 */
@Slf4j
public class SyncDispatcher implements Dispatcher {

    private Map<Class<? extends Enum>, EventHandler> eventHandlerMap;

    public SyncDispatcher() {
        eventHandlerMap = new ConcurrentHashMap<>();
    }

    @Override
    public void register(Class<? extends Enum> eventType, EventHandler handler) {
        eventHandlerMap.put(eventType, handler);
    }

    public void dispatch(final AbstractEvent event) {
        final EventHandler eventHandler = eventHandlerMap.get(event.getType().getClass());
        if (eventHandler == null) {
            throw new BaseException("no matched event handler;type=" + event.getType());
        }

        try {
            log.info("SyncDispatcher handle event start;event=" + event);
            eventHandler.handle(event);
            log.info("SyncDispatcher handle event end;event=" + event);
        } catch (Exception e) {
            log.info("SyncDispatcher handle event exception;event=" + event);
            log.error(e.getMessage());
        }
    }

    @Override
    public void shutdown() {
        // do something
    }
}
