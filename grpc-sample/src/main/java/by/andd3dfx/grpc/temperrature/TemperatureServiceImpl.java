package by.andd3dfx.grpc.temperrature;

import by.andd3dfx.grpc.dto.TemperatureRequest;
import by.andd3dfx.grpc.dto.TemperatureResponse;
import by.andd3dfx.grpc.dto.TemperatureServiceGrpc.TemperatureServiceImplBase;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TemperatureServiceImpl extends TemperatureServiceImplBase {

    private List<Double> temperatures = new ArrayList<>();

    @Override
    public void add(TemperatureRequest request, StreamObserver<TemperatureResponse> responseObserver) {
        temperatures.add(request.getNewValue());
        Double averageTemperature = temperatures.stream().collect(Collectors.averagingDouble(Double::doubleValue));

        TemperatureResponse response = TemperatureResponse.newBuilder()
            .setAverageValue(averageTemperature)
            .setItemsCount(temperatures.size())
            .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
