package com.geektcp.alpha.socket.netty.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author haiyang on 2020-04-30 10:29
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "netty")
public class NettyProperty {

    @NotNull
    @Size(min=1000, max=65535)
    private int tcpPort;

    @NotNull
    @Min(1)
    private int bossCount;

    @NotNull
    @Min(2)
    private int workerCount;

    @NotNull
    private boolean keepAlive;

    @NotNull
    private int backlog;
}
