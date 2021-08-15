package com.geektcp.alpha.db.es6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author tanghaiyang on 2018/1/4.
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling
@EntityScan({"com.geektcp.alpha"})
@ComponentScan({"com.geektcp.alpha"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
