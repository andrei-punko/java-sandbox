package by.andd3dfx.temperrature;

import by.andd3dfx.grpc.TemperatureRequest;
import by.andd3dfx.grpc.TemperatureResponse;
import by.andd3dfx.grpc.TemperatureServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.Random;

public class TemperatureGrpcClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8081)
            .usePlaintext()
            .build();

        TemperatureServiceGrpc.TemperatureServiceBlockingStub stub = TemperatureServiceGrpc.newBlockingStub(channel);
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            TemperatureRequest request = TemperatureRequest.newBuilder()
                .setNewValue(random.nextDouble())
                .build();
            TemperatureResponse response = stub.add(request);
            System.out.printf("Response received from server:\n%s", response);
        }

        channel.shutdown();
    }
}
