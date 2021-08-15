package com.geektcp.alpha.spring.shiro.common.properties;

import lombok.Data;

/**
 * @author tanghaiyang
 */
@Data
public class ShiroProperties {

    private long sessionTimeout;
    private int cookieTimeout;
    private String anonUrl;
    private String loginUrl;
    private String successUrl;
    private String logoutUrl;
    private String unauthorizedUrl;
}
