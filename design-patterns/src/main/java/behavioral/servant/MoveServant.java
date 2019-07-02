package behavioral.servant;

public class MoveServant {

    public void moveTo(Movable serviced, Position where) {
        // Do some other stuff to ensure it moves smoothly and nicely, this is the place to offer the functionality

        System.out.println("Object " + serviced + " moved to " + where);
        serviced.setPosition(where);
        System.out.println("Object " + serviced + " is here");
    }

    public void moveBy(Movable serviced, int dx, int dy) {
        System.out.println("Object " + serviced + " moved by (" + dx + ", " + dy + ")");
        dx += serviced.getPosition().xPosition;
        dy += serviced.getPosition().yPosition;
        serviced.setPosition(new Position(dx, dy));
        System.out.println("Object " + serviced + " is here");
    }
}
