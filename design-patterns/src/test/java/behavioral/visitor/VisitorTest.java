package behavioral.visitor;

import org.junit.Test;

/**
 * Применяется в случаях, когда необходимо для ряда классов сделать похожую (одну и ту же) операцию, но нужно избежать
 * загрязнения их кода.
 */
public class VisitorTest {

    @Test
    public void test() {
        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }
}
