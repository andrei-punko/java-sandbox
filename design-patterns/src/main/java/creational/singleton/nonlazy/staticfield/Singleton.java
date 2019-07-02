package creational.singleton.nonlazy.staticfield;

/**
 * + Простая и прозрачная реализация
 * + Потокобезопасность
 * - Не ленивая инициализация
 */
public class Singleton {

    public static final Singleton INSTANCE = new Singleton();

    private Singleton() {
    }
}
