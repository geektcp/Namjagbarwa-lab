package com.geektcp.alpha.design.statemachine.example;

import com.geektcp.alpha.design.statemachine.event.Dispatcher;
import com.geektcp.alpha.design.statemachine.state.StateManager;

/**
 * 全局状态管理器（比如数据库保存状态)
 * 实现逻辑 TODO（跟 本地状态管理器一致，不同之处在于 当前状态读取 和 状态更新）
 */
public abstract class GlobalStateManager extends StateManager {

    public GlobalStateManager(Dispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public String managerKey() {
        return "global_state_manager";
    }

    @Override
    public int currentState(String uuid) {
        // 从 数据库 读取 记录状态
        return 0;
    }

    @Override
    public boolean setState(String uuid, int newState, int expectState) {
        // 更改 数据库 对应记录的状态，使用乐观锁机制
        return false;
    }

    
}