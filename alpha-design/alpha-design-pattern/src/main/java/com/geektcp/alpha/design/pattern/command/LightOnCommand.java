package com.geektcp.alpha.design.pattern.command;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class LightOnCommand implements Command {
    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}
