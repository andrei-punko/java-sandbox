package creational.singleton.nonlazy.usingenum;

/**
 * + Остроумно
 * + Сериализация из коробки
 * + Потокобезопасность из коробки
 * + Возможность использования EnumSet, EnumMap и т.д.
 * + Поддержка switch
 * - Не ленивая инициализация
 */
public enum Singleton {
    INSTANCE;
}
