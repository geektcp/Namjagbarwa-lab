package com.geektcp.alpha.spring.shiro.config;

import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tanghaiyang
 */
@Configuration
public class AlphaMonitorConfig {

    @Bean
    public HttpTraceRepository inMemoryHttpTraceRepository(){
        return new InMemoryHttpTraceRepository();
    }
}
