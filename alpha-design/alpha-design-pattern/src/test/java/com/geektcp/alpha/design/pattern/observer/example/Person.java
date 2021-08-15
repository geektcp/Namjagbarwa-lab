package com.geektcp.alpha.design.pattern.observer.example;

/**
 * @author tanghaiyang on 2020/5/25 13:39.
 */
public class Person {

    private PersonListener listener;

    public void eat(){
        if(listener!=null){
            listener.doEat(new Event(this));
        }
    }

    public void run(){
        if(listener!=null){
            listener.doRun(new Event(this));
        }
    }

    public void registerListener(PersonListener listener){
        this.listener = listener;
    }
}
