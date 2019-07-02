package structural.composite;

import org.junit.Test;

/**
 * Композиция определяет иерархию классов, которые одновременно могут состоять из примитивных и сложных объектов,
 * упрощает архитектуру клиента, делает процесс добавления новых видов объекта более простым.
 */
public class CompositeTest {

    @Test
    public void test() {
        Ellipse ellipse1 = new Ellipse();
        Ellipse ellipse2 = new Ellipse();
        Ellipse ellipse3 = new Ellipse();
        Ellipse ellipse4 = new Ellipse();

        CompositeGraphic graphic1 = new CompositeGraphic();
        CompositeGraphic graphic2 = new CompositeGraphic();
        CompositeGraphic graphic3 = new CompositeGraphic();

        graphic2.add(ellipse1);
        graphic2.add(ellipse2);
        graphic2.add(ellipse3);

        graphic3.add(ellipse4);

        graphic1.add(graphic2);
        graphic1.add(graphic3);

        graphic1.print();
    }
}
