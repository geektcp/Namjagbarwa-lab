package com.geektcp.alpha.spring.shiro.common.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author tanghaiyang
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:alpha.properties"})
@ConfigurationProperties(prefix = "alpha")
public class AlphaProperties {

    private ShiroProperties shiro = new ShiroProperties();
    private boolean autoOpenBrowser = true;
    private SwaggerProperties swagger = new SwaggerProperties();

    private int maxBatchInsertNum = 1000;

    private ValidateCodeProperties code = new ValidateCodeProperties();
}
