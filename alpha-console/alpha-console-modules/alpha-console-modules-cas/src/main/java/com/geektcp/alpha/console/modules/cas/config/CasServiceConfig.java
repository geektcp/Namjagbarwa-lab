package com.geektcp.alpha.console.modules.cas.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "security.cas.service")
public class CasServiceConfig {
    private String host;
    private String login;
    private String logout;
    private Boolean sendRenew = false;
}
