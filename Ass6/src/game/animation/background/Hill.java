// ID 316044809
package game.animation.background;

import biuoop.DrawSurface;
import game.collections.Sprite;

import java.awt.Color;
import java.awt.Polygon;

public class Hill extends Background {
    private final Polygon hill;

    public Hill(final Sprite backgroundElement) {
        super(backgroundElement);
        this.hill = new Polygon();
        final int radius = (int) (Math.random() * 200 + 200);
        for (int i = 0; i <= 360; i++) {
            int x = (int) (radius * Math.cos(Math.toRadians(i))) + 400;
            int y = (int) (radius * Math.sin(Math.toRadians(i))) + 600;
            hill.addPoint(x, y);
        }
    }

    public Hill(final Sprite backgroundElement, final Polygon hill) {
        super(backgroundElement);
        this.hill = hill;
    }


    @Override
    public void drawOn(final DrawSurface canvas) {
        super.drawOn(canvas);
        canvas.setColor(Color.GREEN);
        canvas.fillPolygon(hill);
        canvas.setColor(Color.BLACK);
        canvas.drawPolygon(hill);
    }

    @Override
    public Background createNew(final Sprite backgroundElement, final Polygon newShape) {
        return new Hill(backgroundElement, newShape);
    }

    @Override
    public Background createNew(final Sprite backgroundElement) {
        return new Hill(backgroundElement);
    }

    @Override
    public Polygon getPolygon() {
        return hill;
    }
}
