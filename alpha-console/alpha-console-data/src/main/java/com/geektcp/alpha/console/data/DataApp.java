package com.geektcp.alpha.console.data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix                // 开启断路器
@EnableHystrixDashboard
@SpringBootApplication(scanBasePackages = {"com.github.alpha.portal.data", "com.geektcp.alpha.console.common.core.bean"})
public class DataApp {

    public static void main(String[] args) {
        log.debug("AlphaPortalDataApplication startup main");
        SpringApplication application = new SpringApplication(DataApp.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
