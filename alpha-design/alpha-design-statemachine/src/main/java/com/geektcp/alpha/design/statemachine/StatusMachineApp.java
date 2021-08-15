package com.geektcp.alpha.design.statemachine;

import com.geektcp.alpha.design.statemachine.event.AsyncDispatcher;
import com.geektcp.alpha.design.statemachine.event.Dispatcher;
import com.geektcp.alpha.design.statemachine.example.LocalStateEvent;
import com.geektcp.alpha.design.statemachine.constant.LocalStateEventType;
import com.geektcp.alpha.design.statemachine.example.LocalStateManager;
import com.geektcp.alpha.design.statemachine.state.StateListener;
import com.geektcp.alpha.design.statemachine.state.StateManager;

/**
 * @author haiyang on 2020-03-31 15:38
 */
public class StatusMachineApp {

    /**
     * 本地状态管理器 调用示例
     * 注册状态变化监听器
     */
    public static void main(String[] args) {
//        Dispatcher dispatcher = new SyncDispatcher();
        Dispatcher dispatcher = new AsyncDispatcher();
        StateManager localStateManager = new LocalStateManager(dispatcher);

        localStateManager.registerListener(new StateListener() {
            @Override
            public void stateChanged(String uuid, int preState, int postState) {
                // do something
            }
        });

        dispatcher.register(LocalStateEventType.class, localStateManager);

        String uuid = "1223333";
        // 分发事件do something
        LocalStateEvent localStateEvent = new LocalStateEvent(uuid, LocalStateEventType.DO_SOMETHING);
        dispatcher.dispatch(localStateEvent);

        // 分发事件同步数据
        localStateEvent = new LocalStateEvent(uuid, LocalStateEventType.SYNC_SOMETHING);
        dispatcher.dispatch(localStateEvent);

        // 程序退出要关闭分发器
        dispatcher.shutdown();
    }
}
