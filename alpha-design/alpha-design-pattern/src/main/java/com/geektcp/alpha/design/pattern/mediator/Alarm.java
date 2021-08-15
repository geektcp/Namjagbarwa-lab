package com.geektcp.alpha.design.pattern.mediator;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class Alarm extends Colleague {

    @Override
    public void onEvent(Mediator mediator) {
        mediator.doEvent("alarm");
    }

    public void doAlarm() {
        System.out.println("doAlarm()");
    }
}