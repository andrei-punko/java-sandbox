package by.andd3dfx.multithreading.storage.control;

import by.andd3dfx.multithreading.storage.model.Storage;
import by.andd3dfx.multithreading.storage.model.Truck;
import by.andd3dfx.multithreading.storage.tasks.LoadTruckTask;
import by.andd3dfx.multithreading.storage.tasks.RoadToFirmTask;
import by.andd3dfx.multithreading.storage.tasks.RoadToStorageTask;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class LogisticManager {

    private ExecutorService loadThreadPoolExecutor;
    private ExecutorService roadThreadPoolExecutor;
    private LinkedBlockingQueue<Truck> loadQueue = new LinkedBlockingQueue<>();
    private LinkedBlockingQueue<Truck> roadToFirmQueue = new LinkedBlockingQueue<>();
    private LinkedBlockingQueue<Truck> roadToStorageQueue = new LinkedBlockingQueue<>();
    private Storage storage;
    private volatile boolean processStarted = true;

    public LogisticManager(double storageCapacity, int gatesCount, double timeSpendingForOneTonLoad,
        List<Truck> trucks) {
        loadThreadPoolExecutor = Executors.newFixedThreadPool(gatesCount);
        roadThreadPoolExecutor = Executors.newFixedThreadPool(trucks.size());

        loadQueue.addAll(trucks);

        storage = new Storage(storageCapacity, timeSpendingForOneTonLoad);
    }

    public void start() throws InterruptedException {
        Function<Boolean, Void> stopProcessFunction = aBool -> {
            processStarted = aBool;
            return null;
        };

        Thread loadTruckThread = new Thread(() -> {
            while (processStarted) {
                Truck truck = loadQueue.poll();
                if (truck != null) {
                    loadThreadPoolExecutor.execute(new LoadTruckTask(storage, truck, roadToFirmQueue));
                }
            }
        });

        Thread sendToStorageThread = new Thread(() -> {
            while (processStarted) {
                Truck truck = roadToStorageQueue.poll();
                if (truck != null) {
                    roadThreadPoolExecutor.execute(new RoadToStorageTask(storage, truck, loadQueue));
                }
            }
        });

        Thread sendToFirmThread = new Thread(() -> {
            while (processStarted) {
                Truck truck = roadToFirmQueue.poll();
                if (truck != null) {
                    roadThreadPoolExecutor
                        .execute(new RoadToFirmTask(storage, truck, roadToStorageQueue, stopProcessFunction));
                }
            }
        });

        loadTruckThread.start();
        sendToStorageThread.start();
        sendToFirmThread.start();

        while (processStarted || !roadToFirmQueue.isEmpty()) {
        }

        loadThreadPoolExecutor.shutdown();
        roadThreadPoolExecutor.shutdown();
        boolean done = loadThreadPoolExecutor.awaitTermination(10, TimeUnit.SECONDS)
            && roadThreadPoolExecutor.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("Status of executors shutdown: " + done);
    }
}
