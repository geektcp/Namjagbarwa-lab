package com.geektcp.alpha.socket.grpc.server;

import com.geektcp.alpha.socket.grpc.proto.demo.GreetingServiceGrpc;
import com.geektcp.alpha.socket.grpc.proto.demo.HelloRequest;
import com.geektcp.alpha.socket.grpc.proto.demo.HelloResponse;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author tanghaiyang on 2020/1/2 1:18.
 */
@Slf4j
public class ThyGRpcServerTest {

    @Test
    public void name() {
        Assert.assertTrue(true);
    }

    //    @Test
    public void startServer() throws IOException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        Server server = ServerBuilder.forPort(8080)
                .addService(new GreetingServiceImpl()).build();
        log.info("Starting server...");
        server.start();
        log.info("Server started!");
        server.awaitTermination();
        latch.await();
        Assert.assertTrue(true);
    }

    public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
        @Override
        public void greeting(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
            log.info("request: {}", request);
            String greeting = "Hello there, " + request.getName();
            HelloResponse response = HelloResponse.newBuilder().setGreeting(greeting).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();

        }
    }
}
