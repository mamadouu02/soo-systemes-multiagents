import java.awt.*;

class Boid {
    private Point pos;
    private Point velocity;

    public Boid() {
        this(new Point(0, 0), new Point(0, 0));
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
}
