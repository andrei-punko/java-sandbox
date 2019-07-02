package structural.adapter.inheritance;

import org.junit.Test;

/**
 * Система поддерживает требуемые данные и поведение, но имеет неподходящий интерфейс.
 * Адаптер предусматривает создание класса-оболочки с требуемым интерфейсом.
 * <p>
 * Similar patterns:
 * Adapter              Converts one interface to another so that it matches what the client is expecting
 * Decorator (Wrapper) 	Dynamically adds responsibility to the interface by wrapping the original code
 * Facade 	            Provides a simplified interface
 */
public class AdapterTest {

    @Test
    public void test() {
        Chief ch = new ChiefAdapter();
        Object dish = ch.makeBreakfast();
        eat(dish);
        dish = ch.makeLunch();
        eat(dish);
        dish = ch.makeDinner();
        eat(dish);
    }

    private void eat(Object dish) {
        System.out.println("Eat " + dish);
    }
}
