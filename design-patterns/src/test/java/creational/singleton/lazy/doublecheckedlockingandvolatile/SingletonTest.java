package creational.singleton.lazy.doublecheckedlockingandvolatile;

import org.junit.Test;

public class SingletonTest {

    @Test
    public void getInstance() {
        Singleton instance = Singleton.getInstance();
    }
}
