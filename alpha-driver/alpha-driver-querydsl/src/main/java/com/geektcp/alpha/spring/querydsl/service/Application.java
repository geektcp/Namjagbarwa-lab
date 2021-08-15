package com.geektcp.alpha.spring.querydsl.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@SpringBootApplication

@ComponentScan({"com.geektcp.gap"})
@EnableJpaRepositories({"com.geektcp.gap"})
@EntityScan({"com.geektcp.gap"})

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

