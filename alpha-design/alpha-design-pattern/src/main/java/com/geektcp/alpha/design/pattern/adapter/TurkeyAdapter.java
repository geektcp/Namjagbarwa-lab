package com.geektcp.alpha.design.pattern.adapter;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class TurkeyAdapter implements Duck {
    Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }
}
