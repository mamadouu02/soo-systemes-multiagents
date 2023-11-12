import gui.GUISimulator;
import gui.Simulable;
import gui.Oval;
import java.awt.*;

public class BallsSimulator implements Simulable {

    private final Balls balls;
    private GUISimulator gui;
    private Point[] pointsInit;

    public BallsSimulator(Balls balls, GUISimulator gui) {
        this.balls = balls;
        this.gui = gui;
        this.pointsInit = new Point[balls.getNbBalls()];
        for (int i = 0; i < balls.getNbBalls(); i++) {
            this.pointsInit[i] = new Point();
            this.pointsInit[i].x = balls.getPoint()[i].x;
            this.pointsInit[i].y = balls.getPoint()[i].y;
        }
    }

    @Override
    public void next() {
        this.balls.translate(10, 10);

        gui.reset();
        Point[] points = this.balls.getPoint();
        for (int i = 0; i < balls.getNbBalls(); i++) {
            gui.addGraphicalElement(new Oval(points[i].x, points[i].y, Color.WHITE, Color.WHITE, 10, 10));
        }
    }
    @Override
    public void restart() {
        for (int i = 0; i < balls.getNbBalls(); i++) {
            this.balls.getPoint()[i].x = pointsInit[i].x;
            this.balls.getPoint()[i].y = pointsInit[i].y;
        }

        gui.reset();
        Point[] points = this.balls.getPoint();
        for (int i = 0; i < balls.getNbBalls(); i++) {
            gui.addGraphicalElement(new Oval(points[i].x, points[i].y, Color.WHITE, Color.WHITE, 10, 10));
        }
    }
}
