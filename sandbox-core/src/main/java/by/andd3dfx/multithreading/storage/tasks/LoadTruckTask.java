package by.andd3dfx.multithreading.storage.tasks;

import static by.andd3dfx.multithreading.storage.util.CustomUtil.convertToMillisScaled;
import static by.andd3dfx.multithreading.storage.util.CustomUtil.sleep;

import by.andd3dfx.multithreading.storage.model.Storage;
import by.andd3dfx.multithreading.storage.model.Truck;
import java.util.concurrent.ExecutorService;

public class LoadTruckTask extends BaseTask {

    public LoadTruckTask(Storage storage, Truck truck, ExecutorService roadExecutor, ExecutorService loadExecutor) {
        super(storage, truck, roadExecutor, loadExecutor);
    }

    @Override
    public void run() {
        System.out.println(truck + " arrived for load");
        if (storage.isStorageEmpty()) {
            System.out.println("Storage empty already, so not need to load truck " + truck);
            return;
        }

        double actualLoadedWeight = storage.needToLoadTruck(truck.getLoadCapacity());
        double deltaInMinutes = actualLoadedWeight * storage.getTimeSpendingForOneTonLoad();

        System.out.println(truck + " load of " + actualLoadedWeight + ": waiting for " + deltaInMinutes);
        sleep(convertToMillisScaled(deltaInMinutes));
        System.out.println(truck + " loaded with " + actualLoadedWeight);
        truck.incrementTimeSpent(deltaInMinutes);

        roadExecutor.execute(new RoadToFirmTask(storage, truck, roadExecutor, loadExecutor));
    }
}
