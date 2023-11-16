package Boids;

import java.awt.*;

import gui.GUISimulator;
import gui.Simulable;

public class BoidsSimulator implements Simulable {

    private final Boid[] boids;
    private final Boid[] boidsInitial;
    private final int nb;
    private final int height;
    private final int width;
    private final GUISimulator window;

    public BoidsSimulator(Boid[] boids, int nb, GUISimulator window, int height, int width) {
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
        window.reset();

        for (Boid b : boids) {
            window.addGraphicalElement(new Triangle(b.getPos().x, b.getPos().y, b.getVelocity().x, b.getVelocity().y));
        }
    }

    private Point cohesion(Boid bj) {
        Point pcj = new Point();

        for (Boid b : boids) {
            if (!b.equals(bj)) {
                pcj.translate(b.getPos().x, b.getPos().y);
            }
        }

        pcj.setLocation(pcj.x / (nb - 1), pcj.y / (nb - 1));
        return new Point((pcj.x - bj.getPos().x) / 100, (pcj.y - bj.getPos().y) / 100);
    }

    private Point separation(Boid bj) {
        Point c = new Point();

        for (Boid b : boids) {
            if (!b.equals(bj)) {
                if (b.getPos().distance(bj.getPos()) < 10) {
                    c.translate(bj.getPos().x - b.getPos().x, bj.getPos().y - b.getPos().y);
                }
            }
        }

        return c;
    }

    private Point alignment(Boid bj) {
        Point pvj = new Point();

        for (Boid b : boids) {
            if (!b.equals(bj)) {
                pvj.translate(b.getVelocity().x, b.getVelocity().y);
            }
        }

        pvj.setLocation(pvj.x / (nb - 1), pvj.y / (nb - 1));
        return new Point((pvj.x - bj.getVelocity().x) / 8, (pvj.y - bj.getVelocity().y) / 8);
    }

    public void bound_position(Boid b) {
        if (b.getPos().x < 0) {
            b.getVelocity().x = 10;
        } else if (b.getPos().x > width) {
            b.getVelocity().x = -10;
        }

        if (b.getPos().y < 0) {
            b.getVelocity().y = 10;
        } else if (b.getPos().y > height) {
            b.getVelocity().y = -10;
        }
    }

    @Override
    public void next() {
        Point v1, v2, v3;

        for (Boid b : boids) {
            v1 = cohesion(b);
            v2 = separation(b);
            v3 = alignment(b);

            b.setVelocity(new Point(b.getVelocity().x + v1.x + v2.x + v3.x, b.getVelocity().y + v1.y + v2.y + v3.y));
            b.setPos(new Point(b.getPos().x + b.getVelocity().x, b.getPos().y + b.getVelocity().y));
            bound_position(b);
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
