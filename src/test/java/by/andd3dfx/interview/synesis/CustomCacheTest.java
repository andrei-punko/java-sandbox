package by.andd3dfx.interview.synesis;

import org.junit.Test;

import static java.lang.Math.random;
import static java.lang.Thread.sleep;

public class CustomCacheTest {
    private final int COUNT = 1000;

    @Test
    public void testCache() throws InterruptedException {
        CustomCache customCache = new CustomCache();
        for (int i = 0; i < COUNT; i++) {
            customCache.write(i, i * i + 5);
        }

        Thread readingThread1 = new Thread(() -> {
            while (true) {
                int index = (int) (COUNT * random());
                System.out.println("Read thread 1, A[" + index + "] = " + customCache.read(index));
            }
        });
        Thread readingThread2 = new Thread(() -> {
            while (true) {
                int index = (int) (COUNT * random());
                System.out.println("Read thread 2, A[" + index + "] = " + customCache.read(index));
            }
        });
        Thread writingThread = new Thread(() -> {
            while (true) {
                int index = (int) (COUNT * random());
                int value = index * 10 - 5;
                customCache.write(index, value);
                System.out.println("Write thread, A[" + index + "] := " + value);
            }
        });

        readingThread1.start();
        readingThread2.start();
        writingThread.start();

        sleep(10);
    }
}
