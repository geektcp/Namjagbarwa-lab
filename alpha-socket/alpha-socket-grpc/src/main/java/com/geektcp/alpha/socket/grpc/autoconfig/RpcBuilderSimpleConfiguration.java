package com.geektcp.alpha.socket.grpc.autoconfig;

import io.grpc.ServerBuilder;
import com.geektcp.alpha.socket.grpc.annotation.RpcService;
import com.geektcp.alpha.socket.grpc.annotation.RpcPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
public class RpcBuilderSimpleConfiguration {

    @RpcPort
    private int port;

    @Bean
    @ConditionalOnProperty(value = "grpc.server.model", havingValue = RpcProperties.SERVER_MODEL_SIMPLE)
    public ServerBuilder getServerBuilder() {
        ServerBuilder serverBuilder;

        log.info("gRPC Server will run without tls. recommend only use in internal service");
        log.info("gRPC Server will listen on port {}", port);
        serverBuilder = ServerBuilder.forPort(port);

        return serverBuilder;
    }

}
