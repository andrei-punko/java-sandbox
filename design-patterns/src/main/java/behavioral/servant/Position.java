package behavioral.servant;

public class Position {

    public int xPosition;
    public int yPosition;

    public Position(int dx, int dy) {
        xPosition = dx;
        yPosition = dy;
    }

    @Override
    public String toString() {
        return "Position{" +
                "xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                '}';
    }
}
