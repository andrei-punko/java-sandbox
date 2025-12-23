package by.andd3dfx.grpc.temperrature;

import by.andd3dfx.grpc.dto.TemperatureRequest;
import by.andd3dfx.grpc.dto.TemperatureResponse;
import by.andd3dfx.grpc.dto.TemperatureServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
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
            log.info("Response received from server:\n{}", response);
        }

        channel.shutdown();
    }
}
