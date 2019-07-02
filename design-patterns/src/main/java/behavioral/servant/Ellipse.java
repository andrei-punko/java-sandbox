package behavioral.servant;

public class Ellipse implements Movable {

    private Position p;

    public void setPosition(Position p) {
        this.p = p;
    }

    public Position getPosition() {
        return this.p;
    }

    @Override
    public String toString() {
        return "Ellipse{" +
                "p=" + p +
                '}';
    }
}
