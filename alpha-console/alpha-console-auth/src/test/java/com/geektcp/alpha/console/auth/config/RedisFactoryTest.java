package com.geektcp.alpha.console.auth.config;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Objects;

/**
 * @author tanghaiyang on 2020/2/4 11:49.
 */
public class RedisFactoryTest {

    @Test
    public void jedisConnectionFactory(){
        RedisConfig redisConfig = new RedisConfig();
        redisConfig.setHost("alpha-server");
        redisConfig.setPort(6379);
        redisConfig.setDatabase(0);
        redisConfig.setPassword("111111");
        redisConfig.setMaxIdle(50);
        redisConfig.setMinIdle(5);
        redisConfig.setMaxActive(8);
        redisConfig.setTimeout("3000ms");
        redisConfig.setMaxWait("3000ms");

        RedisStandaloneConfiguration rf = new RedisStandaloneConfiguration();
        rf.setDatabase(redisConfig.database);
        rf.setHostName(redisConfig.host);
        rf.setPort(redisConfig.port);
        rf.setPassword(RedisPassword.of(redisConfig.password));

//        int to = Integer.parseInt(redisConfig.timeout.substring(0, redisConfig.timeout.length() - 2));
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpb =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisConfig.maxIdle);
        jedisPoolConfig.setMinIdle(redisConfig.minIdle);
        jedisPoolConfig.setMaxTotal(redisConfig.maxActive);

        int l = Integer.parseInt(redisConfig.maxWait.substring(0, redisConfig.maxWait.length() - 2));
        jedisPoolConfig.setMaxWaitMillis(l);
        jpb.poolConfig(jedisPoolConfig);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(rf, jpb.build());
        Jedis jedis = (Jedis)jedisConnectionFactory.getConnection().getNativeConnection();
        jedis.set("aaa","bbbb");
        System.out.println("11111");
//        Assert.assertTrue(Objects.isNull(redisConnection));
    }
}
