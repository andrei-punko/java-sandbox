package by.andd3dfx.multithreading.forkjoin2;

import static java.lang.Thread.sleep;

import java.util.concurrent.ForkJoinPool;
import org.junit.Before;
import org.junit.Test;

public class CustomRecursiveActionTest {

    private ForkJoinPool forkJoinPool;

    @Before
    public void setup() {
        forkJoinPool = ForkJoinPool.commonPool();
    }

    @Test
    public void testCustomRecursiveAction() throws InterruptedException {
        CustomRecursiveAction customRecursiveAction = new CustomRecursiveAction("Some very long and boring string");
        forkJoinPool.execute(customRecursiveAction);

        while (!customRecursiveAction.isDone()) {
            sleep(500);
        }
    }
}
