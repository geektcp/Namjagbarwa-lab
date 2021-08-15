package com.geektcp.alpha.socket.netty.server;

import com.geektcp.alpha.socket.netty.exception.ServerException;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/**
 * @author haiyang on 2020-04-30 10:29
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class TCPServer {

    private final ServerBootstrap serverBootstrap;

    private final InetSocketAddress tcpPort;

    private Channel serverChannel;

    public void start()  {
        try {
            ChannelFuture serverChannelFuture = serverBootstrap.bind(tcpPort).sync();
            log.info("Server is started : port {}", tcpPort.getPort());
            serverChannel = serverChannelFuture.channel().closeFuture().sync().channel();
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @PreDestroy
    public void stop() {
        if ( serverChannel != null ) {
            serverChannel.close();
            serverChannel.parent().close();
        }
    }
}
