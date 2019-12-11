package by.andd3dfx.hello;

import by.andd3dfx.grpc.HelloRequest;
import by.andd3dfx.grpc.HelloResponse;
import by.andd3dfx.grpc.HelloServiceGrpc.HelloServiceImplBase;
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
