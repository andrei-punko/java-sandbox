package creational.builder;

import org.junit.Test;

/**
 * Отделяет конструирование сложного объекта от его представления, так что в результате одного и того же процесса
 * конструирования могут получаться разные представления.
 */
public class BuilderExampleTest {

    @Test
    public void test() {
        Waiter waiter = new Waiter();
        PizzaBuilder hawaiianPizzaBuilder = new HawaiianPizzaBuilder();
        waiter.setPizzaBuilder(hawaiianPizzaBuilder);
        waiter.constructPizza();

        Pizza pizza = waiter.getPizza();
    }
}
