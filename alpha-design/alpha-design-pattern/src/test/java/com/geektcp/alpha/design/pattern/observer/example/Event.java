package com.geektcp.alpha.design.pattern.observer.example;

/**
 * @author tanghaiyang on 2020/5/25 13:39.
 */
public class Event {

    private Person source;

    public Event() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Event(Person source) {
        super();
        this.source = source;
    }

    public Person getSource() {
        return source;
    }

    public void setSource(Person source) {
        this.source = source;
    }

}
