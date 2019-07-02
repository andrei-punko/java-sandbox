package creational.singleton.lazy.doublecheckedlockingandvolatile;

/**
 * + Ленивая инициализация
 * + Высокая производительность
 * - Поддерживается только с JDK 1.5
 * <p>
 * Почему не работает без volatile?
 * Проблема идиомы Double Checked Lock заключается в модели памяти Java, точнее в порядке создания объектов.
 * Можно условно представить этот порядок следующими этапами:
 * <p>
 * Пусть мы создаем нового студента: Student s = new Student(), тогда
 * 1) local_ptr = malloc(sizeof(Student))   // выделение памяти под сам объект;
 * 2) s = local_ptr                         // инициализация указателя;
 * 3) Student::ctor(s);                     // конструирование объекта (инициализация полей);
 * <p>
 * Таким образом, между вторым и третьим этапом возможна ситуация, при которой другой поток может получить
 * и начать использовать (на основании условия, что указатель не нулевой) не полностью сконструированный объект.
 * На самом деле, эта проблема была частично решена в JDK 1.5, однако авторы JSR-133 рекомендуют использовать volatile
 * для Double Cheсked Lock.
 */
public class Singleton {

    private static volatile Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        Singleton localInstance = instance;
        if (localInstance == null) {
            synchronized (Singleton.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Singleton();
                }
            }
        }
        return localInstance;
    }
}
