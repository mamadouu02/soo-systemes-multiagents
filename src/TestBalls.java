import java.awt.*;

public class TestBalls {
    public static void main(String[] args) {
        Point ball1 = new Point(0, 0);
        Point ball2 = new Point(3, 2);
        Point ball3 = new Point(5, 0);

        Point[] points = { ball1, ball2, ball3 };
        Balls balls = new Balls(points);

        System.out.println(balls);
        balls.translate(-1, 7);
        System.out.println(balls);
        balls.reInit();
        System.out.println(balls);
    }
}

class Balls {

    private Point[] points;
    private Point[] posInit;

    public Balls(Point[] points) {
        setPoints(points);
    }

    public Balls(Balls balls) {
        setPoints(balls.getPoints());
    }

    public Point[] getPoints() {
        return this.points;
    }

    public void setPoints(Point[] points) {
        this.points = new Point[points.length];
        this.posInit = new Point[points.length];

        for (int i = 0; i < this.getNbBalls(); i++) {
            this.points[i] = new Point(points[i]);
            this.posInit[i] = new Point(points[i]);
        }
    }

    public int getNbBalls() {
        return this.points.length;
    }

    public void translate(int dx, int dy) {
        for (Point point : this.points) {
            point.translate(dx, dy);
        }
    }

    public void reInit() {
        for (int i = 0; i < this.getNbBalls(); i++) {
            this.points[i].setLocation(posInit[i]);
        }
    }

    @Override
    public String toString() {
        String s = "";
        int nbBalls = getNbBalls();

        for (int i = 0; i < nbBalls - 1; i++) {
            s += this.points[i] + ", ";
        }

        return "[" + s + points[nbBalls - 1] + "]";
    }
}
