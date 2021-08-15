package com.geektcp.alpha.agent.service.impl;

import com.geektcp.alpha.agent.example.annotation.Prometheus;
import com.geektcp.alpha.agent.service.TestService;

/**
 * @author haiyang.tang on 12.03 003 15:10:20.
 */
public class TestServiceImpl implements TestService {

    public int foo(int value) {
        System.out.println("foo: " + value);
        return value;
    }

    @Prometheus
    public String thy() {
        System.out.println("thy: " + 1000);
        return "dddddddddddd";
    }

    @Prometheus
    public int bar(int value) {
        System.out.println("bar: " + value);
        return value;
    }

    public int exception(int value) {
        System.out.println("bar: " + value);
        testException();
        return value;
    }

    private void testException(){
        throwExceptionTest();
    }
    private void throwExceptionTest(){
        throw new RuntimeException("this is throw RuntimeException by thy");
    }
}
