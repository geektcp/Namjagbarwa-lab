package com.geektcp.alpha.socket.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * @author tanghaiyang on 2020/1/2 1:18.
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class GRpcApp {

    public static void main(String[] args) {
        SpringApplication.run(GRpcApp.class, args);
    }
}
