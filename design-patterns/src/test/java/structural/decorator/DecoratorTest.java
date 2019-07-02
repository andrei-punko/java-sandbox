package structural.decorator;

import org.junit.Test;

/**
 * Decorator (он же - Wrapper) предназначен для динамического подключения дополнительного поведения к объекту.
 * Предоставляет гибкую альтернативу практике создания подклассов с целью расширения функциональности.
 * <p>
 * Similar patterns:
 * Adapter              Converts one interface to another so that it matches what the client is expecting
 * Decorator (Wrapper) 	Dynamically adds responsibility to the interface by wrapping the original code
 * Facade 	            Provides a simplified interface
 */
public class DecoratorTest {

    @Test
    public void test() {
        Decorator c = new DecoratorHello(new DecoratorComma(new DecoratorSpace(new MainComponent())));
        c.doOperation();    // Результат выполнения программы "Hello, World!"
        c.newOperation();   // New hello operation
    }
}
