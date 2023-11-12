import gui.GUISimulator ;
import gui.Oval;

import java.awt.Color ;
import java.awt.*;

public class TestBallsSimulator {

    public static void main (String[] args ) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);

        Point ball1 = new Point(200, 0);
        Point ball2 = new Point(0, 200);
        Point ball3 = new Point(100, 100);
        Point ball4 = new Point(200, 200);
        Point ball5 = new Point(0, 0);
        Point balti = new Point(50, 70);

        Point[] points = new Point[] { ball1, ball2, ball3, ball4, ball5, balti };
        Balls balls = new Balls(points, 6);

        gui.setSimulable(new BallsSimulator(balls, gui));
    }
}
