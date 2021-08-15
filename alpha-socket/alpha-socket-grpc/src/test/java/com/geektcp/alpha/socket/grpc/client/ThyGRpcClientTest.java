package com.geektcp.alpha.socket.grpc.client;

import com.geektcp.alpha.socket.grpc.proto.demo.GreetingServiceGrpc;
import com.geektcp.alpha.socket.grpc.proto.demo.HelloRequest;
import com.geektcp.alpha.socket.grpc.proto.demo.HelloResponse;
import com.geektcp.alpha.socket.grpc.proto.demo.Sentiment;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author tanghaiyang on 2020/1/2 1:18.
 */
@Slf4j
public class ThyGRpcClientTest {

    @Test
    public void name() {
        Assert.assertTrue(true);
    }

    //    @Test
    public void startClient() throws Exception {
        CountDownLatch latch = new CountDownLatch(2);
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8080)
                .usePlaintext()
                .build();
        GreetingServiceGrpc.GreetingServiceBlockingStub stub =
                GreetingServiceGrpc.newBlockingStub(channel);
        HelloResponse helloResponse = stub.greeting(
                HelloRequest.newBuilder()
                        .setName("Ray")
                        .setAge(18)
                        .setSentiment(Sentiment.HAPPY)
                        .build());
        log.info("helloResponse:{}", helloResponse);
        channel.shutdown();
        latch.await();
        Assert.assertTrue(true);
    }
}
