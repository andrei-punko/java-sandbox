package behavioral.servant;

import org.junit.Test;

/**
 * Servant pattern defines an object used to offer some functionality to a group of classes without defining that
 * functionality in each of them
 */
public class ServantTest {

    @Test
    public void test() {
        Ellipse ellipse = new Ellipse();
        ellipse.setPosition(new Position(0, 0));

        Rectangle rectangle = new Rectangle();
        rectangle.setPosition(new Position(-5, 5));

        Triangle triangle = new Triangle();
        triangle.setPosition(new Position(1, -3));

        MoveServant servant = new MoveServant();
        servant.moveBy(ellipse, 2, 5);
        servant.moveTo(rectangle, new Position(5, 9));
        servant.moveBy(triangle, 3, 6);
    }
}
