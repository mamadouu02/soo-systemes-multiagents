import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import gui.GraphicalElement;

public class TestBoids {

    public static void main(String[] args) {
        int length = 500;
        int width = 500;
        GUISimulator window = new GUISimulator(length, width, Color.WHITE);
        int nb = 150;
        Boid[] boids = new Boid[nb];

        for (int i = 0; i < nb; i++) {
            Random random_x = new Random();
            Random random_y = new Random();

            Point pos = new Point(random_x.nextInt(length), random_y.nextInt(width));
            Point velocity = new Point(random_x.nextInt(200), random_y.nextInt(200));

            boids[i] = new Boid(pos, velocity);
        }

        window.setSimulable(new Boids(boids, nb, window, length, width));
    }
}

class Boid {
    private Point pos;
    private Point velocity;

    public Boid() {
        setPos(new Point(0,0));
        setVelocity(new Point(0,0));
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

    public void setPos(Point pos) {
        this.pos = new Point(pos);
    }

    public Point getVelocity() {
        return this.velocity;
    }

    public void setVelocity(Point velocity) {
        this.velocity = new Point(velocity);
    }
}

abstract class CenteredGraphicalElement implements GraphicalElement {
    private int posX;
    private int posY;
    private int velX;
    private int velY;


    public CenteredGraphicalElement(int var1, int var2, int var3, int var4) {
        this.posX = var1;
        this.posY = var2;
        this.velX = var3;
        this.velY = var4;
    }

    public void translate(int var1, int var2) {
        this.posX += var1;
        this.posY += var2;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public int getVelX(){ return this.velX; }
    public int getVelY() {
        return this.velY;
    }

}
class Triangle extends CenteredGraphicalElement {
    static Path2D shape = new Path2D.Double();
    static {
        shape.moveTo(0,-3*2);
        shape.lineTo(-3, 3*2);
        shape.lineTo(3,3*2);
        shape.closePath();
    }

    public Triangle(int var1, int var2, int var3, int var4) {
        super(var1, var2, var3, var4);
    }

    @Override
    public void paint(Graphics2D g) {
        AffineTransform save = g.getTransform();
        g.translate(super.getPosX(), super.getPosY());
        g.rotate(Math.atan((double) super.getVelY()/super.getVelX())*360/(2*Math.PI));
        g.setColor(Color.BLUE);
        g.fill(shape);
        g.draw(shape);
        g.setTransform(save);
    }
}

class Boids implements Simulable {
    private Boid[] boids;
    private Boid[] boidsInitial;
    private int nb;
    private int length, width;
    private GUISimulator window;

    public Boids(Boid[] boids, int nb, GUISimulator window, int length, int width) {

        this.boidsInitial = new Boid[nb];
        this.boids = new Boid[nb];

        for (int i = 0; i < nb; i++) {
            this.boidsInitial[i] = new Boid(boids[i]);
            this.boids[i] = new Boid(boids[i]);
        }

        this.nb = nb;
        this.length = length;
        this.width = width;
        this.window = window;
    }

    private void draw_boids() {
        for (int i = 0; i < nb; i++) {
            window.addGraphicalElement(new Triangle(boids[i].getPos().x,
                    boids[i].getPos().y, boids[i].getVelocity().x, boids[i].getVelocity().y));
        }
    }

    private Point rule1(Boid bj) {
        Point pcj = new Point(0,0);
        for (int i = 0; i < nb; i++) {
            Boid b = boids[i];
            if (!b.equals(bj)){
                pcj = new Point(pcj.x + b.getPos().x, pcj.y + b.getPos().y);
            }
        }
        pcj = new Point(pcj.x/(nb-1), pcj.y/(nb-1));
        return new Point((pcj.x - bj.getPos().x)/100, (pcj.y - bj.getPos().y)/100);
    }

    private Point rule2(Boid bj) {
        Point c = new Point(0,0);
        for (int i = 0; i < nb; i++) {
            Boid b = boids[i];
            if (!b.equals(bj)) {
                if ((Math.pow((b.getPos().x - bj.getPos().x), 2) + Math.pow((b.getPos().y - bj.getPos().y), 2)) < 100) {
                    c = new Point(c.x - (b.getPos().x - bj.getPos().x), c.y - (b.getPos().y - bj.getPos().y));
                }
            }
        }
        return c;
    }

    private Point rule3(Boid bj) {
        Point pvj = new Point(0,0);
        for (int i = 0; i < nb; i++) {
            Boid b = boids[i];
            if (!b.equals(bj)){
                pvj = new Point(pvj.x + b.getVelocity().x, pvj.y + b.getVelocity().y);
            }
        }
        pvj = new Point(pvj.x/(nb-1), pvj.y/(nb-1));
        return new Point((pvj.x - bj.getVelocity().x)/100, (pvj.y - bj.getVelocity().y)/100);
    }

    @Override
    public void next() {
        window.reset();

        Point v1, v2, v3;
        Boid b;
        for (int i = 0; i < nb; i++){
            b = boids[i];

            v1 = rule1(b);
            v2 = rule2(b);
            v3 = rule3(b);

            boids[i].setVelocity(new Point(b.getVelocity().x + v1.x + v2.x + v3.x, b.getVelocity().y + v1.y + v2.y + v3.y));
            boids[i].setPos(new Point(b.getPos().x + b.getVelocity().x, b.getPos().y + b.getVelocity().y));
        }
        draw_boids();
    }

    @Override
    public void restart() {
        window.reset();

        for (int i = 0; i < nb; i++) {
            this.boids[i].setPos(new Point(this.boidsInitial[i].getPos().x, this.boidsInitial[i].getPos().y));
            this.boids[i].setVelocity(new Point(this.boidsInitial[i].getVelocity().x, this.boidsInitial[i].getVelocity().y));
        }

        draw_boids();
    }
}
