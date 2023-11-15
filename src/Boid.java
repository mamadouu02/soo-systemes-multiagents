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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Boid)) {
            return false;
        }

        Boid other = (Boid) o;
        return other.pos.x == this.pos.x && other.pos.y == this.pos.y &&
                other.velocity.x == this.velocity.x && other.velocity.y == this.velocity.y;
    }
}
