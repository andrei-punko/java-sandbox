package creational.singleton.lazy.synchronizedaccessor;

/**
 * Этот вариант блокирует метод getInstance() вне зависимости от того, создали ли мы единственный экземпляр или нет.
 * Это замедляет работу программы, если требуется часто получать объект Singleton из разных потоков.
 * <p>
 * + Ленивая инициализация
 * - Низкая производительность (критическая секция) в наиболее типичном доступе
 */
public class Singleton {

    private static Singleton instance;

    private Singleton() {
    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
