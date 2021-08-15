package com.geektcp.alpha.console.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
public class RedisFactory {

    private RedisConfig redisConfig;

    @Autowired
    public RedisFactory(RedisConfig redisConfig){
        this.redisConfig = redisConfig;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory () {
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
        return jedisConnectionFactory;
    }
}
