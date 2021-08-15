package com.geektcp.alpha.design.pattern.chain;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public abstract class Handler {
    protected Handler successor;


    public Handler(Handler successor) {
        this.successor = successor;
    }


    protected abstract void handleRequest(Request request);
}
