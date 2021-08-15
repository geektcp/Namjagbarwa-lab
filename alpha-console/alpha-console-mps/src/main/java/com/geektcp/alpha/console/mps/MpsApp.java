package com.geektcp.alpha.console.mps;

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
@EnableFeignClients(basePackages = {"com.geektcp.alpha.console.common.portal.fegin"})
@EnableHystrix                // 开启断路器
@EnableHystrixDashboard
@SpringBootApplication(scanBasePackages = {"com.geektcp.alpha.console.mps", "com.geektcp.alpha.console.common.core.bean","com.geektcp.alpha.console.common.portal"})
public class MpsApp {

    public static void main(String[] args) {
        log.debug("AlphaMpsApplication startup main");
        SpringApplication application = new SpringApplication(MpsApp.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
