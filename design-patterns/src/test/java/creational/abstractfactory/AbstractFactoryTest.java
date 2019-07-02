package creational.abstractfactory;

import org.junit.Test;

/**
 * Предоставляет интерфейс для создания семейств взаимосвязанных или взаимозависимых объектов, не специфицируя их
 * конкретных классов.
 */
public class AbstractFactoryTest {

    @Test
    public void test() {
        AbstractFactory factory1 = new ConcreteFactory1();
        Client client1 = new Client(factory1);
        client1.execute();

        AbstractFactory factory2 = new ConcreteFactory2();
        Client client2 = new Client(factory2);
        client2.execute();
    }
}
