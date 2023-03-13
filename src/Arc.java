import java.awt.Graphics;

public class Arc extends Shape {
    private static int startAngle = 30;
    private static int arcAngle = 70;
    private int myStartAngle;
    private int myArcAngle;

    public Arc() {
        super();
        myStartAngle = startAngle;
        myArcAngle = arcAngle;
    }

    public static int getStartAngle() {
        return startAngle;
    }

    public static int getArcAngle() {
        return arcAngle;
    }

    public static void setStartAngle(int newStartAngle) {
        startAngle = newStartAngle;
    }

    public static void setArcAngle(int newArcAngle) {
        arcAngle = newArcAngle;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());

        g.drawArc(getMinX(), getMinY(), getWidth(), getHeight(), myStartAngle, myArcAngle);
    }
}
