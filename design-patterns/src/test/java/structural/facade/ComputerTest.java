package structural.facade;

import org.junit.Test;

/**
 * Позволяет скрыть сложность системы путём сведения всех возможных внешних вызовов к одному объекту, делегирующему их
 * соответствующим объектам системы.
 * <p>
 * Шаблон применяется для установки некоторого рода политики по отношению к другой группе объектов. Если политика
 * должна быть яркой и заметной, следует воспользоваться услугами шаблона Фасад. Если же необходимо обеспечить
 * скрытность и аккуратность (прозрачность), более подходящим выбором является шаблон Заместитель (Proxy).
 * <p>
 * Similar patterns:
 * Adapter              Converts one interface to another so that it matches what the client is expecting
 * Decorator (Wrapper) 	Dynamically adds responsibility to the interface by wrapping the original code
 * Facade 	            Provides a simplified interface
 */
public class ComputerTest {

    @Test
    public void test() {
        Computer computer = new Computer();
        computer.startComputer();
    }
}
