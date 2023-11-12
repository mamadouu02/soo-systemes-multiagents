import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import gui.Oval;
import java.awt.*;

public class BallsSimulator implements Simulable {

    private final Balls balls;
    private GUISimulator gui;
    private Point[] pointsInit;
    private Point[] translate;

    public BallsSimulator(Balls balls, GUISimulator gui) {
        this.balls = balls;
        this.gui = gui;

        this.pointsInit = new Point[balls.getNbBalls()];
        this.translate = new Point[balls.getNbBalls()];
        for (int i = 0; i < balls.getNbBalls(); i++) {
            this.pointsInit[i] = new Point();
            this.pointsInit[i].x = balls.getPoint()[i].x;
            this.pointsInit[i].y = balls.getPoint()[i].y;
            this.translate[i] = new Point(10,10);
        }
    }

    @Override
    public void next() {
        for (int i = 0; i < balls.getNbBalls(); i++){
            this.balls.getPoint()[i].translate(translate[i].x, translate[i].y);
        }

        gui.reset();
        Point[] points = this.balls.getPoint();
        for (int i = 0; i < balls.getNbBalls(); i++) {
            int width = gui.getPanelWidth();
            int height = gui.getPanelHeight();
            if (points[i].x > width - 10) {
                points[i].x = width - 20 - (points[i].x - width);
                translate[i].x = -translate[i].x;
            }
            if (points[i].y > height - 10) {
                points[i].y = height - 20 - (points[i].y - height);
                translate[i].y = -translate[i].y;
            }
            if (points[i].x < 10) {
                points[i].x -= 2*(points[i].x-10);
                translate[i].x = -translate[i].x;
            }
            if (points[i].y < 10) {
                points[i].y -= 2*(points[i].y-10);
                translate[i].y = -translate[i].y;
            }
            Color[] colors = new Color[] {Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN, Color.WHITE};
            gui.addGraphicalElement(new Oval(points[i].x, points[i].y, colors[i%5], colors[i%5], 10, 10));
        }
    }
    @Override
    public void restart() {
        balls.setPoint(pointsInit);

        gui.reset();
        Point[] points = this.balls.getPoint();
        for (int i = 0; i < balls.getNbBalls(); i++) {
            gui.addGraphicalElement(new Oval(points[i].x, points[i].y, Color.WHITE, Color.WHITE, 10, 10));
        }
    }
}
