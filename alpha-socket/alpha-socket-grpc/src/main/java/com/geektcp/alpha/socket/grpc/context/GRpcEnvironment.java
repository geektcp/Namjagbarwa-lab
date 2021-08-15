package com.geektcp.alpha.socket.grpc.context;

import com.geektcp.alpha.socket.grpc.annotation.RpcPort;
import com.geektcp.alpha.socket.grpc.autoconfig.RpcProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.util.SocketUtils;

import java.util.Properties;
/**
 * @author tanghaiyang on 2020/1/2 1:18.
 */
public class GRpcEnvironment implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        MutablePropertySources sources = environment.getPropertySources();
        Properties properties = new Properties();
        Integer configuredPort = environment.getProperty("grpc.server.port", Integer.class);

        if (null == configuredPort) {
            properties.put(RpcPort.propertyName, RpcProperties.DEFAULT_SERVER_PORT);
        } else if (0 == configuredPort) {
            properties.put(RpcPort.propertyName, SocketUtils.findAvailableTcpPort());
        } else {
            properties.put(RpcPort.propertyName, configuredPort);
        }

        sources.addLast(new PropertiesPropertySource("grpc", properties));
    }
}
