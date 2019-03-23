package by.andd3dfx.multithreading.storage.tasks;

import static by.andd3dfx.multithreading.storage.util.CustomUtil.convertToMillisScaled;
import static by.andd3dfx.multithreading.storage.util.CustomUtil.sleep;

import by.andd3dfx.multithreading.storage.model.Storage;
import by.andd3dfx.multithreading.storage.model.Truck;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;

public class RoadToFirmTask implements Runnable {

    private final Storage storage;
    private final Truck truck;
    private final LinkedBlockingQueue<Truck> resultQueue;
    private final Function<Boolean, Void> stopProcessFunction;

    public RoadToFirmTask(Storage storage, Truck truck, LinkedBlockingQueue<Truck> resultQueue,
        Function<Boolean, Void> stopProcessFunction) {
        this.storage = storage;
        this.truck = truck;
        this.resultQueue = resultQueue;
        this.stopProcessFunction = stopProcessFunction;
    }

    @Override
    public void run() {
        System.out.println(truck + " going to firm...");
        sleep(convertToMillisScaled(truck.getTimeInRoad()));
        truck.incrementTripsCount();
        truck.incrementTimeSpent(truck.getTimeInRoad());
        System.out.println(truck + " arrived to firm");

        if (!storage.isStorageEmpty()) {
            resultQueue.add(truck);
            System.out.println("Send " + truck + " to storage");
        } else {
            stopProcessFunction.apply(false);
            System.out.println("Empty storage so don't send " + truck);
        }
    }
}
