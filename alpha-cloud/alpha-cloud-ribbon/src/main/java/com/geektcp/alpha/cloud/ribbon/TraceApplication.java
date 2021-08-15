package com.geektcp.alpha.cloud.ribbon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author haiyang.tang on 10.15 015 19:38:58.
 */
@RestController
@EnableDiscoveryClient
@SpringBootApplication
@Slf4j
public class TraceApplication {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(value = "/trace-1", method = RequestMethod.GET)
    public String trace() {
        log.info("===call trace-1===");
        return restTemplate().getForEntity("http://trace-2/trace-2", String.class).getBody();
    }

    public static void main(String[] args) {
        SpringApplication.run(TraceApplication.class, args);
    }

}