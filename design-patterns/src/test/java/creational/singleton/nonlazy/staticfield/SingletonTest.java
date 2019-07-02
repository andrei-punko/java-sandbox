package creational.singleton.nonlazy.staticfield;

import org.junit.Test;

public class SingletonTest {

    @Test
    public void test() {
        Singleton singleton = Singleton.INSTANCE;
    }
}
