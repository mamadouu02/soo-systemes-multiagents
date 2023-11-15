import java.awt.*;
import java.util.Random;

import gui.GUISimulator;
import gui.Simulable;

public class TestBoids {

    public static void main(String[] args) {
        int height = 500;
        int width = 500;
        int nb = 150;
        GUISimulator window = new GUISimulator(height, width, Color.WHITE);
        Boid[] boids = new Boid[nb];

        for (int i = 0; i < nb; i++) {
            Random r = new Random();
            Point pos = new Point(r.nextInt(height), r.nextInt(width));
            Point velocity = new Point(r.nextInt(200), r.nextInt(200));
            boids[i] = new Boid(pos, velocity);
        }

        window.setSimulable(new Boids(boids, nb, window, height, width));
    }
}

class Boids implements Simulable {
    
    private Boid[] boids;
    private Boid[] boidsInitial;
    private int nb;
    private int height, width;
    private GUISimulator window;

    public Boids(Boid[] boids, int nb, GUISimulator window, int height, int width) {

        this.boidsInitial = new Boid[nb];
        this.boids = new Boid[nb];

        for (int i = 0; i < nb; i++) {
            this.boidsInitial[i] = new Boid(boids[i]);
            this.boids[i] = new Boid(boids[i]);
        }

        this.nb = nb;
        this.height = height;
        this.width = width;
        this.window = window;
    }

    private void draw_boids() {
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

        pcj = new Point(pcj.x / (nb-1), pcj.y / (nb-1));
        return new Point((pcj.x - bj.getPos().x) / 100, (pcj.y - bj.getPos().y) / 100);
    }

    private Point rule2(Boid bj) {
        Point c = new Point(0, 0);

        for (Boid b : boids) {            
            if (!b.equals(bj)) {
                if (b.getPos().distanceSq(bj.getPos()) < 100) {
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

        pvj = new Point(pvj.x / (nb-1), pvj.y / (nb-1));
        return new Point((pvj.x - bj.getVelocity().x) / 100, (pvj.y - bj.getVelocity().y) / 100);
    }

    @Override
    public void next() {
        window.reset();
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
        window.reset();

        for (int i = 0; i < nb; i++) {
            this.boids[i] = new Boid(boidsInitial[i]);
        }

        draw_boids();
    }
}
