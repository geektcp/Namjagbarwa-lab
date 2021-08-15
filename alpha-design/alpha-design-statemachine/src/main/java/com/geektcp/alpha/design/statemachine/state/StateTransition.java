package com.geektcp.alpha.design.statemachine.state;

import java.util.List;

public interface StateTransition {

    /**
     * 前置状态
     */
    List<Integer> preState();

    /**
     * 执行的操作，返回操作后的状态
     */
    int operation(String uuid);

}
