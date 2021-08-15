package com.geektcp.alpha.design.statemachine.example;

import com.geektcp.alpha.design.statemachine.constant.LocalStateEventType;
import com.geektcp.alpha.design.statemachine.constant.LocalStatus;
import com.geektcp.alpha.design.statemachine.event.Dispatcher;
import com.geektcp.alpha.design.statemachine.state.StateManager;
import com.geektcp.alpha.design.statemachine.state.StateTransition;

import java.util.ArrayList;
import java.util.List;

/**
 * 本地状态管理器
 */
public class LocalStateManager extends StateManager<LocalStateEvent> {

    private volatile LocalStatus currentState = LocalStatus.INIT;

    public LocalStateManager(Dispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    protected void init() {
        // 注册自动状态转移操作
        registerAutoTransition(new StateTransition() {
            @Override
            public List<Integer> preState() {
                List<Integer> list = new ArrayList<>();
                list.add(LocalStatus.PROCESSING.getCode());
                return list;
            }

            @Override
            public int operation(String uuid) {
                // do something than change state
                return LocalStatus.SUCCESS.getCode();
            }
        });
    }

    @Override
    public String managerKey() {
        return "local_state_manager";
    }

    @Override
    public int currentState(String uuid) {
        return currentState.getCode();
    }

    @Override
    public boolean setState(String uuid, int newState, int expectState) {
        if (expectState != currentState.getCode()) {
            return false;
        }
        synchronized (this) {
            if (expectState != currentState.getCode()) {
                return false;
            }
            currentState = LocalStatus.fromState(newState);
        }
        return true;
    }

    @Override
    public void handle(LocalStateEvent event) {
        // 处理同步事件
        if (event.getType() == LocalStateEventType.SYNC_SOMETHING) {
            autoSyncState(event.getUuid());
        }

        if (event.getType() == LocalStateEventType.DO_SOMETHING) {
            doTransition(event.getUuid(), new StateTransition() {
                @Override
                public List<Integer> preState() {
                    List<Integer> list = new ArrayList<>();
                    list.add(LocalStatus.INIT.getCode());
                    return list;
                }

                @Override
                public int operation(String uuid) {
                    // do something than change state
                    return LocalStatus.PROCESSING.getCode();
                }
            });
        }
    }

}
