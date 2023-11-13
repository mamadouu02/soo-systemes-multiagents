import java.awt.*;

public class Balls {

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
            s += this.points[i].toString() + ", ";
        }

        return "[" + s + points[nbBalls - 1].toString() + "]";
    }
}
