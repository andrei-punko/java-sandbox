package behavioral.mediator;

import org.junit.Test;

/**
 * Медиатор обеспечивает взаимодействие множества объектов, формируя при этом слабую связанность и избавляя объекты
 * от необходимости явно ссылаться друг на друга.
 */
public class MediatorTest {

    @Test
    public void test() {
        User robert = new User("Robert");
        User john = new User("John");

        robert.sendMessage("Hi! John!");
        john.sendMessage("Hello! Robert!");
    }
}
