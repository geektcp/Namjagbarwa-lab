package com.geektcp.alpha.design.statemachine.constant;

public enum LocalStateEventType {

    DO_SOMETHING(1, "do something"),
    SYNC_SOMETHING(2, "同步"),
    ;

    int type;
    String remark;

    LocalStateEventType(int type, String remark) {
        this.type = type;
        this.remark = remark;
    }
}
