package behavioral.iterator;

import org.junit.Test;

/**
 * Представляет собой объект, позволяющий получить последовательный доступ к элементам объекта-агрегата без
 * использования описаний каждого из агрегированных объектов.
 */
public class IteratorTest {

    @Test
    public void test() {
        NameRepository namesRepository = new NameRepository();

        for (Iterator iterator = namesRepository.getIterator(); iterator.hasNext(); ) {
            String name = (String) iterator.next();
            System.out.println("Name : " + name);
        }
    }
}
