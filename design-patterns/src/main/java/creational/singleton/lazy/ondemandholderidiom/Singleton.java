package creational.singleton.lazy.ondemandholderidiom;

/**
 * + Ленивая инициализация
 * + Высокая производительность
 * - Невозможно использовать для не статических полей класса
 */
public class Singleton {

    private Singleton() {
    }

    public static Singleton getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    private static class SingletonHolder {
        private static final Singleton HOLDER_INSTANCE = new Singleton();
    }
}
