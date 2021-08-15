package com.geektcp.alpha.driver.mybatis2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MybatisApp {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApp.class, args);
    }

}
