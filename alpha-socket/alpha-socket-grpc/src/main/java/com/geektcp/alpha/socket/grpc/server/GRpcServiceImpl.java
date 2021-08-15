package com.geektcp.alpha.socket.grpc.server;

import com.geektcp.alpha.socket.grpc.proto.greet.GreetServiceGrpc;
import com.geektcp.alpha.socket.grpc.proto.greet.Greeting;
import com.geektcp.alpha.socket.grpc.proto.greet.Person;
import lombok.extern.slf4j.Slf4j;
import io.grpc.stub.StreamObserver;
import com.geektcp.alpha.socket.grpc.annotation.RpcService;

/**
 * @author tanghaiyang on 2020/1/2 1:18.
 */
@Slf4j
@RpcService
public class GRpcServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {

    @Override
    public void sayHello(Person request, StreamObserver<Greeting> responseObserver) {
        log.info("server received {}", request);
        String message = "Hello " + request.getFirstName() + " " + request.getLastName() + "!";
        Greeting greeting = Greeting.newBuilder().setMessage(message).build();
        log.info("server responded {}", greeting);
        responseObserver.onNext(greeting);
        responseObserver.onCompleted();
    }
}
