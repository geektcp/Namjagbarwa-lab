package com.geektcp.alpha.util.base.proxy.example.subject;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author tanghaiyang on 2019/8/15.
 */
@Slf4j
public class DynamicProxyHandler {

    //绑定委托对象，并返回代理类
    public Object bind(Object tar) {
        //绑定该类实现的所有接口，取得代理类
        return Proxy.newProxyInstance(
                tar.getClass().getClassLoader(),
                tar.getClass().getInterfaces(),
                new InvocationHandler(){
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        log.info("代理类做的事");
                        Object result = null;
                        //这里就可以进行所谓的AOP编程了
                        //在调用具体函数方法前，执行功能处理
                        result = method.invoke(tar, args);
                        //在调用具体函数方法后，执行功能处理
                        return result;
                    }
                }
        );
    }


}