package com.geektcp.alpha.console.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@Slf4j
@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class ConfigApp {
    public static void main(String[] args) {
        log.debug("AlphaConfigApplication startup main");
        SpringApplication application = new SpringApplication(ConfigApp.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
