package com.geektcp.alpha.spring.nacos;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableNacosConfig
@NacosConfigurationProperties(dataId = "ns", autoRefreshed = true, groupId = "name_space_1")
public class NacosSpringApp {

    public static void main(String[] args) {
        SpringApplication.run(NacosSpringApp.class, args);
    }
}

