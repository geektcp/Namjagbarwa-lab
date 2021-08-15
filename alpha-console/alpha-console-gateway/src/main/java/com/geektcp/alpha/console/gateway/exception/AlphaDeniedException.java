package com.geektcp.alpha.console.gateway.exception;

/**
 * 403 授权拒绝
 */
public class AlphaDeniedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AlphaDeniedException() {
    }

    public AlphaDeniedException(String message) {
        super(message);
    }

    public AlphaDeniedException(Throwable cause) {
        super(cause);
    }

    public AlphaDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlphaDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
