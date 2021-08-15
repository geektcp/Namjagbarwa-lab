
package com.geektcp.alpha.socket.grpc.annotation;

import io.grpc.ServerBuilder;
/**
 * @author tanghaiyang on 2020/1/2 1:18.
 */
public interface RpcBuilderConfigurer {
    void configure(ServerBuilder<?> serverBuilder);
}
