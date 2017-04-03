package by.andd3dfx.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.lang.Math.random;

/**
 * Generate 1 million random numbers using multiple threads
 */
public class GenerateNumbers {

    private static ExecutorService pool;

    public GenerateNumbers() {
        pool = Executors.newFixedThreadPool(4);
    }

    public static class RandomGenerator implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 250; i++) {     //Should be 250_000 here
                System.out.println(random());
            }
        }
    }

    public static void main(String[] args) {
        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            RandomGenerator randomGenerator = new RandomGenerator();
            futures.add(pool.submit(randomGenerator));
        }

        while (!areTasksFinished(futures)) ;
        System.out.println("Bunch of numbers generated!");
    }

    private static boolean areTasksFinished(List<Future<?>> futures) {
        for (Future<?> future : futures) {
            if (!future.isDone()) return false;
        }
        return true;
    }
}
