package com.geektcp.alpha.cloud.feign;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author tanghaiyang on 2019/10/13 23:40.
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class FeignApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(FeignApplication.class).web(true).run(args);
    }
}