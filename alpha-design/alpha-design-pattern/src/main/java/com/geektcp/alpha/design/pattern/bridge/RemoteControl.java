package com.geektcp.alpha.design.pattern.bridge;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public abstract class RemoteControl {
    protected TV tv;

    public RemoteControl(TV tv) {
        this.tv = tv;
    }

    public abstract void on();

    public abstract void off();

    public abstract void tuneChannel();
}
