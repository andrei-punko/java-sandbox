package structural.bridge;

import org.junit.Test;

/**
 * Мост разделяет абстракцию и реализацию так, чтобы они могли изменяться независимо.
 * <p>
 * Использует инкапсуляцию, агрегирование и может использовать наследование для того, чтобы разделить ответственность
 * между классами.
 */
public class BridgeTest {

    @Test
    public void test() {
        Shape[] shapes = {
                new Circle(5, 10, 10, new LargeCircleDrawer()),
                new Circle(20, 30, 100, new SmallCircleDrawer())};

        for (Shape next : shapes) {
            next.draw();
        }
    }
}
