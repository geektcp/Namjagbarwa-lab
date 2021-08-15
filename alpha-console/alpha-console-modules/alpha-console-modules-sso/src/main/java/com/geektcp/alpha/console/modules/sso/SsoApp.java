package com.geektcp.alpha.console.modules.sso;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

@Slf4j
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.geektcp.alpha.console.modules.sso", "com.geektcp.alpha.console.common.core.bean"})
public class SsoApp {

    public static void main(String[] args) {
        log.debug("AlphaSsoclientApplication startup main");
        SpringApplication application = new SpringApplication(SsoApp.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }


    @LoadBalanced
    @Bean
    public OAuth2RestTemplate restTemplate(UserInfoRestTemplateFactory factory) {
        return factory.getUserInfoRestTemplate();
    }
}
