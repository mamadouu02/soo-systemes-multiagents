package Boids;

import java.awt.*;

public class Boid {

    private Point pos;
    private Point velocity;

    public Boid() {
        this(new Point(), new Point());
    }

    public Boid(Point pos, Point velocity) {
        setPos(pos);
        setVelocity(velocity);
    }

    public Boid(Boid other) {
        this(other.getPos(), other.getVelocity());
    }

    public Point getPos() {
        return this.pos;
    }

    public Point getVelocity() {
        return this.velocity;
    }

    public void setPos(Point pos) {
        this.pos = new Point(pos);
    }

    public void setVelocity(Point velocity) {
        this.velocity = new Point(velocity);
    }

    // Redéfiniton de la méthode equals de la classe Object.
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Boid)) {
            return false;
        }

        Boid other = (Boid) o;
        return this.pos.equals(other.pos) && this.velocity.equals(other.velocity);
    }
}
