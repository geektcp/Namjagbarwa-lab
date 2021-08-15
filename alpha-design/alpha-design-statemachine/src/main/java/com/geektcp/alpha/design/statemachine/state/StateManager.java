package com.geektcp.alpha.design.statemachine.state;

import com.geektcp.alpha.design.statemachine.event.AbstractEvent;
import com.geektcp.alpha.design.statemachine.event.Dispatcher;
import com.geektcp.alpha.design.statemachine.event.EventHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public abstract class StateManager<T extends AbstractEvent> implements EventHandler<T> {

    private List<StateListener> listeners = new ArrayList<>();
    private List<StateTransition> autoTransitions = new ArrayList<>();

    private Dispatcher dispatcher;

    public StateManager(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
        init();
    }

    /**
     * 初始话方法，子类实现
     */
    protected abstract void init();

    /**
     * 状态管理器标识
     */
    public abstract String managerKey();

    /**
     * 当前状态
     */
    protected abstract int currentState(String uuid);

    /**
     * 更新状态
     */
    protected abstract boolean setState(String uuid, int newState, int expectState);

    /**
     * 注册状态监听器
     */
    public synchronized void registerListener(StateListener listener) {
        if (listeners.contains(listener)) {
            return;
        }
        listeners.add(listener);
    }

    /**
     * 注册自动状态转换动作
     */
    private void registerAutoTransition() {
        // todo something
        dispatcher.shutdown();
    }

    /**
     * 注册自动状态转换动作
     */
    protected void registerAutoTransition(StateTransition transition) {
        if (autoTransitions.contains(transition)) {
            return;
        }
        autoTransitions.add(transition);
    }

    /**
     * 执行状态转换操作
     */
    protected void doTransition(String uuid, StateTransition transition) {
        int current = currentState(uuid);
        if (!transition.preState().contains(current)) {
            return;
        }

        log.info(managerKey() + " manual do: currentState=" + current);
        log.info(managerKey() + " manual do: do something");
        int newState = transition.operation(uuid);
        log.info(managerKey() + " manual do: newState=" + newState);
        boolean isSuccess = setState(uuid, newState, current);
        log.info(managerKey() + " manual do: setState result=" + isSuccess);
        if (isSuccess) {
            notifyListeners(uuid, current, newState);
        }
    }

    /**
     * 自动同步状态
     */
    protected void autoSyncState(String uuid) {
        List<StateTransition> innerAutoTransitions = new ArrayList<>(autoTransitions);
        if (innerAutoTransitions.isEmpty()) {
            return;
        }

        int current = currentState(uuid);
        while (true) {
            StateTransition matchedTransition = null;
            for (StateTransition transition : innerAutoTransitions) {
                if (transition.preState().contains(current)) {
                    matchedTransition = transition;
                }
            }
            if (matchedTransition == null) {
                break;
            }
            log.info(managerKey() + " autoSync: currentState=" + current);
            log.info(managerKey() + " autoSync: do something");
            int newState = matchedTransition.operation(uuid);
            log.info(managerKey() + " autoSync: newState=" + newState);
            boolean isSuccess = setState(uuid, newState, current);
            log.info(managerKey() + " autoSync: setState result=" + isSuccess);

            notifyListeners(uuid, current, newState);

            if (isSuccess) {
                current = newState;
            } else {
                current = currentState(uuid);
            }
        }
    }

    private void notifyListeners(String uuid, int current, int newState) {
        try {
            List<StateListener> innerListeners = new ArrayList<>(listeners);
            for (StateListener listener : innerListeners) {
                log.info(managerKey() + " notify listener; uuid=" + uuid + ";oldState=" + current + ";newState=" + newState);
                listener.stateChanged(uuid, current, newState);
            }
        } catch (Exception e) {
            log.error("notify listener managerKey: {} | exception : {}", managerKey(), e.getMessage());
            log.error(e.getMessage());
        }
    }
}
