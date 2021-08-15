package com.geektcp.alpha.design.pattern.command;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class CommandTest {

    @Test
    public void main() {
        Invoker invoker = new Invoker();
        Light light = new Light();
        Command lightOnCommand = new LightOnCommand(light);
        Command lightOffCommand = new LightOffCommand(light);
        invoker.setOnCommand(lightOnCommand, 0);
        invoker.setOffCommand(lightOffCommand, 0);
        invoker.onButtonWasPushed(0);
        invoker.offButtonWasPushed(0);
        Assert.assertTrue(true);
    }
}
