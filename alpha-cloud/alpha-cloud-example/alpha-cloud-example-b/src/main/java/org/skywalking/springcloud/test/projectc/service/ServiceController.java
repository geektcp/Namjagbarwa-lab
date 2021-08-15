package org.skywalking.springcloud.test.projectc.service;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    @Autowired
    private HttpClientCaller httpClientCaller;

    @Trace
    @RequestMapping("/projectC/{value}")
    public String home(@PathVariable("value") String value) throws InterruptedException, IOException {
//        Thread.sleep(new Random().nextInt(2) * 1000);
        httpClientCaller.call("http://www.baidu.com");
        return value + "-" + UUID.randomUUID().toString();
    }

    @RequestMapping("/projectC2/{value}")
    public String home2(@PathVariable("value") String value) {
        throw new RuntimeException("runtime error");
    }
}
