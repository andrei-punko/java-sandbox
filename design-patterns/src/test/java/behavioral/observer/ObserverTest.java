package behavioral.observer;

import org.junit.Test;

/**
 * Определяет зависимость типа «один ко многим» между объектами таким образом, что при изменении состояния одного
 * объекта все зависящие от него оповещаются об этом событии.
 */
public class ObserverTest {

    @Test
    public void test() {
        Subject subject = new Subject();

        new HexObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}
