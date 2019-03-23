package by.andd3dfx.multithreading.storage.tasks;

import static by.andd3dfx.multithreading.storage.util.CustomUtil.convertToMillisScaled;
import static by.andd3dfx.multithreading.storage.util.CustomUtil.sleep;

import by.andd3dfx.multithreading.storage.model.Storage;
import by.andd3dfx.multithreading.storage.model.Truck;
import java.util.concurrent.LinkedBlockingQueue;

public class RoadToStorageTask implements Runnable {

    private final Storage storage;
    private final Truck truck;
    private final LinkedBlockingQueue<Truck> resultQueue;

    public RoadToStorageTask(Storage storage, Truck truck, LinkedBlockingQueue<Truck> resultQueue) {
        this.storage = storage;
        this.truck = truck;
        this.resultQueue = resultQueue;
    }

    @Override
    public void run() {
        System.out.println(truck + " going to storage...");
        long delay = convertToMillisScaled(truck.getTimeInRoad());
        sleep(delay);
        truck.incrementTripsCount();
        truck.incrementTimeSpent(truck.getTimeInRoad());

        resultQueue.add(truck);
    }
}
