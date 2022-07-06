package by.andd3dfx.grpc.hello;

import by.andd3dfx.grpc.dto.HelloRequest;
import by.andd3dfx.grpc.dto.HelloResponse;
import by.andd3dfx.grpc.dto.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class HelloGrpcClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
            .usePlaintext()
            .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);

        HelloRequest request = HelloRequest.newBuilder()
            .setFirstName("Andrei")
            .setLastName("Punko")
            .build();
        HelloResponse helloResponse = stub.hello(request);
        System.out.printf("Response received from server:\n%s", helloResponse);

        channel.shutdown();
    }
}
