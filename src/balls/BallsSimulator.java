package balls;

import java.awt.*;

import gui.GUISimulator;
import gui.Simulable;
import gui.Oval;

public class BallsSimulator implements Simulable {

    private final Balls balls;
    private Point[] dir;
    private Point[] dir_init;
    private final GUISimulator window;
    private final int width, height;
    private Color[] colors = { Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN, Color.WHITE };

    public BallsSimulator(Balls balls, GUISimulator window) {
        this.balls = new Balls(balls);
        this.dir = new Point[balls.getNbBalls()];
        this.dir_init = new Point[balls.getNbBalls()];
        this.window = window;
        this.width = window.getPanelWidth();
        this.height = window.getPanelHeight();

        for (int i = 0; i < balls.getNbBalls(); i++) {
            this.dir[i] = new Point(10, 10);
            this.dir_init[i] = new Point(10, 10);
        }
    }

    @Override
    public void next() {
        window.reset();

        for (int i = 0; i < balls.getNbBalls(); i++) {
            Point point = balls.getPoints()[i];
            point.translate(dir[i].x, dir[i].y);

            if (point.x > width - 10) {
                point.x = width - 20 - (point.x - width);
                dir[i].x *= -1;
            }

            if (point.y > height - 10) {
                point.y = height - 20 - (point.y - height);
                dir[i].y *= -1;
            }

            if (point.x < 10) {
                point.x -= 2 * (point.x - 10);
                dir[i].x *= -1;
            }

            if (point.y < 10) {
                point.y -= 2 * (point.y - 10);
                dir[i].y *= -1;
            }

            window.addGraphicalElement(
                    new Oval(point.x, point.y, colors[i % colors.length], colors[i % colors.length], 10));
        }
    }

    @Override
    public void restart() {
        window.reset();

        // On réinitialise les positions des ballzs.
        balls.reInit();

        for (int i = 0; i < balls.getNbBalls(); i++) {
            this.dir[i].setLocation(dir_init[i]);
            Point point = balls.getPoints()[i];
            window.addGraphicalElement(
                    new Oval(point.x, point.y, colors[i % colors.length], colors[i % colors.length], 10));
        }
    }
}
