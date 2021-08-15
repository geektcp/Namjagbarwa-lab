package com.geektcp.alpha.util.base.proxy.example.subject;

import org.junit.Test;

/**
 * @author tanghaiyang on 2019/8/15.
 */
public class TestProxy {

    /**
     * 静态代理
     */
    @Test
    public void SubjectTest() {
        Subject sub = new SubjectStaticProxy();
        sub.doSomething();
    }


    /**
     * 动态代理
     */
    @Test
    public void ProxyHandlerTest() {
        DynamicProxyHandler proxy = new DynamicProxyHandler();

        //绑定该类实现的所有接口
        Subject sub = (Subject) proxy.bind(new RealSubject());

        sub.doSomething();
    }


}
