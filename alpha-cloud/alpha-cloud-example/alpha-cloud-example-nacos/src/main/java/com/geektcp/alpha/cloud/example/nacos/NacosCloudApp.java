package com.geektcp.alpha.cloud.example.nacos;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosConfigurationProperties(dataId = "ns", autoRefreshed = true, groupId = "name_space_1")
public class NacosCloudApp {

    public static void main(String[] args) {
        SpringApplication.run(NacosCloudApp.class, args);
    }
}