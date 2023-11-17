package Boids;

import java.awt.*;
import java.util.Random;

import gui.GUISimulator;

public class TestBoidsSimulator {

    public static void main(String[] args) {
        int height = 500;
        int width = 500;
        int nb = 100;
        Boid[] boids = new Boid[nb];
        Point pos = new Point();
        Point velocity = new Point();

        for (int i = 0; i < nb; i++) {
            Random r = new Random();
            pos.setLocation(r.nextInt(width), r.nextInt(height));
            velocity.setLocation(50, 0);
            boids[i] = new Boid(pos, velocity);
        }

        GUISimulator window = new GUISimulator(height, width, Color.WHITE);
        window.setSimulable(new BoidsSimulator(boids, nb, window, height, width));
    }
}
