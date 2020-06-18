// ID 316044809
package game.animation.background.tree;

import biuoop.DrawSurface;
import game.animation.background.Background;
import game.collections.Sprite;

import java.awt.Color;
import java.awt.Polygon;

public class Head extends Background {
    private final Polygon head;

    public Head(final Sprite backgroundElement) {
        super(backgroundElement);
        this.head = new Polygon();
        this.head.addPoint(350, 500);
        this.head.addPoint(400, 350);
        this.head.addPoint(450, 500);
    }

    public Head(final Sprite backgroundElement, final Polygon newShape) {
        super(backgroundElement);
        this.head = newShape;
    }

    @Override
    public void drawOn(final DrawSurface canvas) {
        canvas.setColor(Color.BLACK);
        canvas.drawPolygon(head);
        canvas.setColor(Color.GREEN.darker());
        canvas.fillPolygon(head);
    }

    @Override
    public Background createNew(final Sprite backgroundElement, final Polygon newShape) {
        return new Head(backgroundElement, newShape);
    }

    @Override
    public Background createNew(final Sprite backgroundElement) {
        return new Head(backgroundElement);
    }

    @Override
    public Polygon getPolygon() {
        return this.head;
    }
}
