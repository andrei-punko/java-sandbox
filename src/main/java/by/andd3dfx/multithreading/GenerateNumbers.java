package by.andd3dfx.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Generate 1 million random numbers using multiple threads
 */
public class GenerateNumbers {

    private ExecutorService pool;

    public GenerateNumbers() {
        pool = Executors.newFixedThreadPool(4);
    }

    public class RandomGeneratorTask implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 250; i++) {     //Should be 250_000 here
                System.out.println(ThreadLocalRandom.current().nextFloat());
            }
        }
    }

    public void generate() {
        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            RandomGeneratorTask randomGenerator = new RandomGeneratorTask();
            futures.add(pool.submit(randomGenerator));
        }

        while (!areTasksFinished(futures)) ;
        System.out.println("Bunch of numbers generated!");
    }

    private boolean areTasksFinished(List<Future<?>> futures) {
        for (Future<?> future : futures) {
            if (!future.isDone()) return false;
        }
        return true;
    }
}
