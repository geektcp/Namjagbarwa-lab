package com.geektcp.alpha.util.base.proxy.example.subject;

/**
 * @author tanghaiyang on 2019/8/15.
 */
public class SubjectStaticProxy implements Subject {

    private Subject realSubject = new RealSubject();

    public void doSomething() {
        realSubject.doSomething();
    }

}
