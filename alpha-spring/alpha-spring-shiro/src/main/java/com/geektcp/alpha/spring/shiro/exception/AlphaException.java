package com.geektcp.alpha.spring.shiro.exception;

/**
 * ALPHA系统内部异常
 *
 * @author tanghaiyang
 */
public class AlphaException extends RuntimeException  {

    private static final long serialVersionUID = -994962710559017255L;

    public AlphaException(String message) {
        super(message);
    }
}
