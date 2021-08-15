package com.geektcp.alpha.console.modules.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class MqApp {

    public static void main(String[] args) {
        log.debug("AlphaMqApplication startup main");
        SpringApplication application = new SpringApplication(MqApp.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
