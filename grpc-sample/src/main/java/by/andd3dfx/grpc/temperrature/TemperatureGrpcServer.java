package by.andd3dfx.grpc.temperrature;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class TemperatureGrpcServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
            .forPort(8081)
            .addService(new TemperatureServiceImpl()).build();

        server.start();
        server.awaitTermination();
    }
}
