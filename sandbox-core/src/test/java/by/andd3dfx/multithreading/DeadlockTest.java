package by.andd3dfx.multithreading;

import org.junit.Ignore;
import org.junit.Test;

public class DeadlockTest {

    /**
     * Remove @Ignore to suggest that deadlock happened
     */
    @Ignore
    @Test
    public void main() throws Exception {
        new Deadlock().makeDeadlock();
    }
}
