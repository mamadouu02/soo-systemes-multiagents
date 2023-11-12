import java.awt.*;

public class Balls {

    private Point[] point;
    private int nbBalls;

    public Balls() {
        this.point = new Point[0];
        this.nbBalls = 0;
    }

    public Balls(Point[] point, int nbBalls) {
        this.point = point;
        this.nbBalls = nbBalls;
    }

    public void translate(int dx, int dy) {
        for (int i = 0; i < this.nbBalls; i++) {
            point[i].translate(dx, dy);
        }
    }

    public void reInit() {
        for (int i = 0; i < this.nbBalls; i++){
            point[i].setLocation(0,0);
        }
    }
    @Override
    public String toString() {
        String r = " ";
        for (int i = 0; i < this.nbBalls - 1; i++){
            r += point[i].toString() + " ; ";
        }
        r += point[nbBalls-1].toString();
        return r;
    }
}
