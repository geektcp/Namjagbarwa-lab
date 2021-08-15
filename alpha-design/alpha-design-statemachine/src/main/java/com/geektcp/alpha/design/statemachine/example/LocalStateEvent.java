package com.geektcp.alpha.design.statemachine.example;

import com.geektcp.alpha.design.statemachine.constant.LocalStateEventType;
import com.geektcp.alpha.design.statemachine.event.AbstractEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LocalStateEvent extends AbstractEvent<LocalStateEventType> {

    private String uuid;


    public LocalStateEvent(String uuid, LocalStateEventType type) {
        super(type);
        this.uuid = uuid;
    }

}
