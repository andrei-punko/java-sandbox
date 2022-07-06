package by.andd3dfx.grpc.hello;

import by.andd3dfx.grpc.dto.HelloRequest;
import by.andd3dfx.grpc.dto.HelloResponse;
import by.andd3dfx.grpc.dto.HelloServiceGrpc.HelloServiceImplBase;
import io.grpc.stub.StreamObserver;

public class HelloServiceImpl extends HelloServiceImplBase {

    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        String greeting = String.format("Hello, %s %s", request.getFirstName(), request.getLastName());
        HelloResponse response = HelloResponse.newBuilder()
            .setGreeting(greeting)
            .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
