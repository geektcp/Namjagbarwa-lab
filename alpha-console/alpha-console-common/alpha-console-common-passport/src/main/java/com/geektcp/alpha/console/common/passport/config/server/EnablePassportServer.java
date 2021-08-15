package com.geektcp.alpha.console.common.passport.config.server;

import com.geektcp.alpha.console.common.passport.config.EnablePassportClient;
import com.geektcp.alpha.console.common.passport.config.PassportSsoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnablePassportClient
@EnableConfigurationProperties({PassportSsoProperties.class,PassportServerProperties.class})
@Import({PassportServerConfiguration.class})
public @interface EnablePassportServer {

}
