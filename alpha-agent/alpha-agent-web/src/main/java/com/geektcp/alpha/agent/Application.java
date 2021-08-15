package com.geektcp.alpha.agent;

/**
 * @author haiyang.tang on 10.21 021 15:25:31.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@ServletComponentScan
@EnableScheduling
@EntityScan("com.casstime.alpha")
@ComponentScan("com")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
