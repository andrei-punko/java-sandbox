package creational.prototype;

import org.junit.Test;

/**
 * Задаёт виды создаваемых объектов с помощью экземпляра-прототипа и создаёт новые объекты путём копирования этого прототипа.
 * Позволяет уйти от реализации и следовать принципу «программирование через интерфейсы».
 * В качестве возвращающего типа указывается интерфейс/абстрактный класс на вершине иерархии, а классы-наследники могут
 * подставить туда наследника, реализующего этот тип.
 * <p>
 * Проще говоря, это паттерн создания объекта через клонирование другого объекта вместо создания через конструктор.
 */
public class PrototypeTest {

    @Test
    public void test() throws CloneNotSupportedException {
        Cookie tempCookie = null;
        Cookie prot = new CoconutCookie();
        CookieMachine cm = new CookieMachine(prot);
        for (int i = 0; i < 100; i++) {
            tempCookie = cm.makeCookie();
        }
    }
}
