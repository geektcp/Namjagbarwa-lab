package com.geektcp.alpha.console.eureka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@Slf4j
@EnableEurekaServer
@SpringBootApplication
public class EurekaApp {

    public static void main(String[] args) {
        log.debug("AlphaEurekaApplication startup main");
        SpringApplication application = new SpringApplication(EurekaApp.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
