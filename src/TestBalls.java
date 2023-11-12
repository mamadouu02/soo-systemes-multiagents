import java.awt.*;

public class TestBalls {
    public static void main(String[] args) {
        Point ball1 = new Point(0,0);
        Point ball2 = new Point(3, 2);
        Point ball3 = new Point(5, 0);

        Point[] points = new Point[] {ball1, ball2, ball3};
        Balls balls = new Balls(points, 3);

        System.out.println(balls);
        balls.translate(-1, 7);
        System.out.println(balls);
        balls.reInit();
        System.out.println(balls);
    }
}

