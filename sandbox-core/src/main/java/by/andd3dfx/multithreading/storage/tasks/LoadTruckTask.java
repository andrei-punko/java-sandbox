package by.andd3dfx.multithreading.storage.tasks;

import static by.andd3dfx.multithreading.storage.util.CustomUtil.convertToMillisScaled;
import static by.andd3dfx.multithreading.storage.util.CustomUtil.sleep;

import by.andd3dfx.multithreading.storage.model.Storage;
import by.andd3dfx.multithreading.storage.model.Truck;
import java.util.concurrent.LinkedBlockingQueue;

public class LoadTruckTask implements Runnable {

    private final Storage storage;
    private final Truck truck;
    private final LinkedBlockingQueue<Truck> resultQueue;

    public LoadTruckTask(Storage storage, Truck truck, LinkedBlockingQueue<Truck> resultQueue) {
        this.storage = storage;
        this.truck = truck;
        this.resultQueue = resultQueue;
    }

    @Override
    public void run() {
        System.out.println(truck + " arrived for load");
        if (storage.isStorageEmpty()) {
            System.out.println("Storage empty already, so not need to load truck " + truck);
            return;
        }
        double actualLoadedWeight = storage.needToLoadTruck(truck.getLoadCapacity());
        System.out.println(truck + " actual loaded weight = " + actualLoadedWeight);

        double deltaInMinutes = actualLoadedWeight * storage.getTimeSpendingForOneTonLoad();
        System.out.println(truck + " load: waiting for " + deltaInMinutes);
        sleep(convertToMillisScaled(deltaInMinutes));
        truck.incrementTimeSpent(deltaInMinutes);

        resultQueue.add(truck);
    }
}
