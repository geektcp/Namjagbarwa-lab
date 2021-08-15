package com.geektcp.alpha.console.common.passport.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(PassportClientConfiguration.class)
public @interface EnablePassportClient {

}
