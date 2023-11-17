package Boids;

import java.awt.*;
import java.awt.geom.*;

import gui.GraphicalElement;

class Triangle implements GraphicalElement {

    private int posX;
    private int posY;
    private int velX;
    private int velY;

    private static int size = 3;
    private static Path2D shape = new Path2D.Double();

    static {
        shape.moveTo(0, -size * 2);
        shape.lineTo(-size, size * 2);
        shape.lineTo(size, size * 2);
        shape.closePath();
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public int getVelX() {
        return this.velX;
    }

    public int getVelY() {
        return this.velY;
    }

    public Triangle(int posX, int posY, int velX, int velY) {
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;
    }

    @Override
    public void paint(Graphics2D g) {
        AffineTransform save = g.getTransform();
        g.translate(this.posX, this.posY);
        g.rotate(Math.atan2(this.velX, -this.velY));
        g.setColor(Color.BLUE);
        g.fill(shape);
        g.draw(shape);
        g.setTransform(save);
    }
}
