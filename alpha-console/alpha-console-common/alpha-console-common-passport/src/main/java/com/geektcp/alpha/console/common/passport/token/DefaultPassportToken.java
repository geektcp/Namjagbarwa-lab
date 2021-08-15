package com.geektcp.alpha.console.common.passport.token;

import com.geektcp.alpha.console.common.passport.auth.PassportAuthentication;
import lombok.Data;


@Data
public class DefaultPassportToken implements PassportToken<PassportAuthentication> {

    private String ip;

    private PassportAuthentication value;

    private String key;

    private long expire = 3600;

    public DefaultPassportToken() {
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public PassportAuthentication getValue() {
        return value;
    }

    @Override
    public String getIp() {
        return ip;
    }
}
