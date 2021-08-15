package com.geektcp.alpha.spring.security;

import com.geektcp.alpha.spring.security.bean.SecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityApp {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApp.class, args);
	}

}
