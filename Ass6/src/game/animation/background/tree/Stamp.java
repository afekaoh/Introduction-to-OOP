// ID 316044809
package game.animation.background.tree;

import biuoop.DrawSurface;
import game.animation.background.Background;
import game.collections.Sprite;

import java.awt.Color;
import java.awt.Polygon;

public class Stamp extends Background {
    private final Polygon stamp;
    private final Sprite background;
    private final Background head;

    public Stamp(final Sprite backgroundElement) {
        super(backgroundElement);
        this.background = backgroundElement;
        this.stamp = new Polygon();
        final int stampStartX = 390;
        final int stampStartY = 600;
        final int stampEndX = 410;
        final int stampEndY = 500;
        stamp.addPoint(stampStartX, stampStartY);
        stamp.addPoint(stampStartX, stampEndY);
        stamp.addPoint(stampEndX, stampEndY);
        stamp.addPoint(stampEndX, stampStartY);
        this.head = new Head(backgroundElement);
    }

    public Stamp(final Sprite backgroundElement, final Background stamp, final Background head) {
        super(backgroundElement);
        this.background = backgroundElement;
        this.head = head;
        this.stamp = stamp.getPolygon();
    }

    public Stamp(final Sprite backgroundElement, final Polygon newShape, final Background head) {
        super(backgroundElement);
        this.background = backgroundElement;
        this.stamp = newShape;
        this.head = head;
    }

    @Override
    public void drawOn(final DrawSurface canvas) {
        super.drawOn(canvas);
        canvas.setColor(Color.BLACK);
        canvas.drawPolygon(stamp);
        canvas.setColor(Color.ORANGE);
        canvas.fillPolygon(stamp);
        head.drawOn(canvas);
    }

    @Override
    public Background translate(final int deltaX, final int deltaY) {
        Background temp = super.translate(deltaX, deltaY);
        return new Stamp(background, temp, head.translate(deltaX, deltaY));
    }

    @Override
    public Background scale(final double scaleX, final double scaleY) {
        Background temp = super.scale(scaleX, scaleY);
        return new Stamp(background, temp, head.scale(scaleX, scaleY));
    }

    @Override
    public Background createNew(final Sprite backgroundElement, final Polygon newShape) {
        return new Stamp(backgroundElement, newShape, head);
    }

    @Override
    public Background createNew(final Sprite backgroundElement) {
        return new Stamp(backgroundElement);
    }

    @Override
    public Polygon getPolygon() {
        return stamp;
    }
}
