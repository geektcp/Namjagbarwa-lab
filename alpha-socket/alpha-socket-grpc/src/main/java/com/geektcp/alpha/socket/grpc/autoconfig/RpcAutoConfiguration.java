package com.geektcp.alpha.socket.grpc.autoconfig;

import io.grpc.ServerBuilder;
import io.grpc.services.HealthStatusManager;
import com.geektcp.alpha.socket.grpc.annotation.RpcBuilderConfigurer;
import com.geektcp.alpha.socket.grpc.runner.GRpcRunner;
import com.geektcp.alpha.socket.grpc.annotation.RpcService;
import com.geektcp.alpha.socket.grpc.annotation.RpcPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author tanghaiyang on 2020/1/2 1:18.
 */
@Slf4j
@Configuration
@ConditionalOnBean(annotation = RpcService.class)
@EnableConfigurationProperties(RpcProperties.class)
public class RpcAutoConfiguration {

    @RpcPort
    private int port;

    @Autowired
    private RpcProperties serverProperties;

    @Bean
    public GRpcRunner gRpcServerRunner(ServerBuilder serverBuilder, RpcBuilderConfigurer serverBuilderConfigurer) {
        return new GRpcRunner(serverBuilder, serverBuilderConfigurer);
    }

    @Bean
    public HealthStatusManager healthStatusManager() {
        return new HealthStatusManager();
    }

    @Bean
    @ConditionalOnMissingBean(RpcBuilderConfigurer.class)
    public RpcBuilderConfigurer defaultServerBuilderConfigurer() {
        return serverBuilder -> log.info("configure in defaultServerBuilderConfigurer, no op...");
    }

}
