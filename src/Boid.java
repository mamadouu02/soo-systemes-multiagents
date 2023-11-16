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

    public void bound_position(int xMax, int yMax) {
        if (this.pos.x < 0) {
            this.velocity.x = 10;
        } else if (this.pos.x > xMax) {
            this.velocity.x = -10;
        }

        if (this.pos.y < 0) {
            this.velocity.y = 10;
        } else if (this.pos.y > yMax) {
            this.velocity.y = -10;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Boid)) {
            return false;
        }

        Boid other = (Boid) o;
        return other.pos.x == this.pos.x && other.pos.y == this.pos.y
                && other.velocity.x == this.velocity.x && other.velocity.y == this.velocity.y;
    }
}
