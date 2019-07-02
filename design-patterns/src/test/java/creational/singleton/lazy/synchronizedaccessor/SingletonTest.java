package creational.singleton.lazy.synchronizedaccessor;

import org.junit.Test;

public class SingletonTest {

    @Test
    public void getInstance() {
        Singleton instance = Singleton.getInstance();
    }
}
