import gui.GUISimulator ;
import gui.Oval;

import java.awt.Color ;
import java.awt.*;

public class TestBallsSimulator {

    public static void main (String[] args ) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);

        Point ball1 = new Point(0, 0);
        Point ball2 = new Point(30, 20);
        Point ball3 = new Point(50, 0);

        Point[] points = new Point[] {ball1, ball2, ball3};
        Balls balls = new Balls(points, 3);

        gui.setSimulable(new BallsSimulator(balls, gui));
    }
}
