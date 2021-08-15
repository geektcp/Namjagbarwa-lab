package com.geektcp.alpha.spring.shiro.common.function;

import com.geektcp.alpha.spring.shiro.exception.RedisConnectException;

/**
 * @author tanghaiyang
 */
@FunctionalInterface
public interface JedisExecutor<T, R> {
    R excute(T t) throws RedisConnectException;
}
