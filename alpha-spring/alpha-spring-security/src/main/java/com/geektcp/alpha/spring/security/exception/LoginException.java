package com.geektcp.alpha.spring.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author haiyang on 2020-04-12 17:41
 */
public class LoginException extends AuthenticationException {

    public LoginException(String msg, Throwable t) {
        super(msg, t);
    }

    public LoginException(String msg) {
        super(msg);
    }
}
