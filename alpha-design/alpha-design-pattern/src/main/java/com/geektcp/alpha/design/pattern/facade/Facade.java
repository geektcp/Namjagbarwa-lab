package com.geektcp.alpha.design.pattern.facade;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class Facade {
    private SubSystem subSystem = new SubSystem();

    public void watchMovie() {
        subSystem.turnOnTV();
        subSystem.setCD("a movie");
        subSystem.startWatching();
    }
}
