import java.awt.*;

public class Balls {

    private Point[] points;
    private int nbBalls;

    public Balls() {
        this.points = new Point[0];
        this.nbBalls = 0;
    }

    public Balls(Point[] point, int nbBalls) {
        this.points = point;
        this.nbBalls = nbBalls;
    }

    public Point[] getPoint() {
        return this.points;
    }

    public void setPoint(Point[] points) {
        for (int i = 0; i < this.getNbBalls(); i++) {
            this.points[i] = new Point();
            this.points[i].x = points[i].x;
            this.points[i].y = points[i].y;
        }
    }

    public int getNbBalls() {
        return this.nbBalls;
    }

    public void setNbBalls(int nbBalls) {
        this.nbBalls = nbBalls;
    }

    public void translate(int dx, int dy) {
        for (int i = 0; i < this.nbBalls; i++) {
            points[i].translate(dx, dy);
        }
    }

    public void reInit() {
        for (int i = 0; i < this.nbBalls; i++) {
            points[i].setLocation(0, 0);
        }
    }

    @Override
    public String toString() {
        String r = " ";
        for (int i = 0; i < this.nbBalls - 1; i++){
            r += points[i].toString() + " ; ";
        }
        r += points[nbBalls-1].toString();
        return r;
    }
}
