import java.awt.*;
import java.util.Random;

import gui.GUISimulator;
import gui.Simulable;
import gui.Rectangle;

public class TestBoids {

    public static void main(String[] args) {
        int height = 1901;
        int width = 901;
        int nb = 1000;
        Boid[] boids = new Boid[nb];
        Point pos = new Point(0, 0);
        Point velocity = new Point(0, 0);

        for (int i = 0; i < nb; i++) {
            boolean bool = true;

            while (bool) {
                bool = false;
                Random r = new Random();
                pos = new Point(r.nextInt(height), r.nextInt(width));
                velocity = new Point(r.nextInt(50), r.nextInt(50));

                for (int j = 0; j < i; j++) {
                    if (pos.distance(boids[j].getPos()) < 24) {
                        bool = true;
                    }
                }
            }

            boids[i] = new Boid(pos, velocity);
        }

        GUISimulator window = new GUISimulator(height, width, Color.WHITE);
        window.setSimulable(new Boids(boids, nb, window));
    }
}

class Boids implements Simulable {
    
    private final Boid[] boids;
    private final Boid[] boidsInitial;
    private final int nb;
    private final GUISimulator window;

    public Boids(Boid[] boids, int nb, GUISimulator window) {

        this.boidsInitial = new Boid[nb];
        this.boids = new Boid[nb];

        for (int i = 0; i < nb; i++) {
            this.boidsInitial[i] = new Boid(boids[i]);
            this.boids[i] = new Boid(boids[i]);
        }

        this.nb = nb;
        this.window = window;
    }

    private void draw_boids() {
        window.reset();

        for (Boid b : boids) {
            window.addGraphicalElement(new Triangle(b.getPos().x, b.getPos().y, b.getVelocity().x, b.getVelocity().y));
        }
    }

    private Point rule1(Boid bj) {
        Point pcj = new Point(0, 0);

        for (Boid b : boids) {
            if (!b.equals(bj)) {
                pcj = new Point(pcj.x + b.getPos().x, pcj.y + b.getPos().y);
            }
        }

        pcj = new Point(pcj.x / (nb - 1), pcj.y / (nb - 1));
        return new Point((pcj.x - bj.getPos().x) / 100, (pcj.y - bj.getPos().y) / 100);
    }

    private Point rule2(Boid bj) {
        Point c = new Point(0, 0);

        for (Boid b : boids) {            
            if (!b.equals(bj)) {
                if (b.getPos().distance(bj.getPos()) < 24) {
                    c = new Point(c.x - (b.getPos().x - bj.getPos().x), c.y - (b.getPos().y - bj.getPos().y));
                }
            }
        }

        return c;
    }

    private Point rule3(Boid bj) {
        Point pvj = new Point(0, 0);

        for (Boid b : boids) {
            if (!b.equals(bj)){
                pvj = new Point(pvj.x + b.getVelocity().x, pvj.y + b.getVelocity().y);
            }
        }

        pvj = new Point(pvj.x / (nb - 1), pvj.y / (nb - 1));
        return new Point((pvj.x - bj.getVelocity().x) / 8, (pvj.y - bj.getVelocity().y) / 8);
    }

    @Override
    public void next() {
        Point v1, v2, v3;

        for (Boid b : boids){
            v1 = rule1(b);
            v2 = rule2(b);
            v3 = rule3(b);

            b.setVelocity(new Point(b.getVelocity().x + v1.x + v2.x + v3.x, b.getVelocity().y + v1.y + v2.y + v3.y));
            b.setPos(new Point(b.getPos().x + b.getVelocity().x, b.getPos().y + b.getVelocity().y));
        }

        draw_boids();
    }

    @Override
    public void restart() {
        for (int i = 0; i < nb; i++) {
            this.boids[i] = new Boid(boidsInitial[i]);
        }

        draw_boids();
    }
}
