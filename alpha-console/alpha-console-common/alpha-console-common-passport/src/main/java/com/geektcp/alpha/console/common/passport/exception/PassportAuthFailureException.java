package com.geektcp.alpha.console.common.passport.exception;

import org.springframework.security.core.AuthenticationException;

public class PassportAuthFailureException extends AuthenticationException {

    public PassportAuthFailureException(String msg, Throwable t) {
        super(msg, t);
    }

    public PassportAuthFailureException(String msg){
        super(msg);
    }
}
