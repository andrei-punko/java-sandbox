package structural.proxy;

import org.junit.Test;

/**
 * Предоставляет объект, который контролирует доступ к другому объекту, перехватывая все вызовы (выполняет функцию
 * контейнера).
 */
public class ProxyTest {

    @Test
    public void test() {
        Image image = new ProxyImage("test_10mb.jpg");

        // image will be loaded from disk
        image.display();

        // image will not be loaded from disk
        image.display();
    }
}
