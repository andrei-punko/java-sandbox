package creational.factorymethod;

import org.junit.Test;

/**
 * Определяет интерфейс для создания объекта, но оставляет подклассам решение о том, какой класс инстанцировать.
 * <p>
 * Фабричный метод позволяет классу делегировать создание подклассов. Используется, когда:
 * - классу заранее неизвестно, объекты каких подклассов ему нужно создавать.
 * - класс спроектирован так, чтобы объекты, которые он создаёт, специфицировались подклассами.
 * - класс делегирует свои обязанности одному из нескольких вспомогательных подклассов,
 * и планируется локализовать знание о том, какой класс принимает эти обязанности на себя
 * <p>
 * Factory vs Factory method:
 * - Factory - separate class to create complex object
 * - Factory method - instead of introducing separate class for factory - we just add one method in that class itself
 * as a factory
 */
public class FactoryMethodTest {

    @Test
    public void test() {
        Creator[] creators = {new ConcreteCreatorA(), new ConcreteCreatorB()};

        for (Creator creator : creators) {
            Product product = creator.factoryMethod();
            System.out.printf("Created {%s}\n", product.getClass());
        }
    }
}
