// ID 316044809
package game.animation.background.tree;

import biuoop.DrawSurface;
import game.animation.background.Background;
import game.collections.Sprite;

import java.awt.Polygon;

public class Tree extends Background {
    private final Background stamp;

    public Tree(final Sprite backgroundElement) {
        super(backgroundElement);
        this.stamp = new Stamp(backgroundElement);
    }

    @Override
    public void drawOn(final DrawSurface canvas) {
        super.drawOn(canvas);
        stamp.drawOn(canvas);
    }

    @Override
    public Background translate(final int deltaX, final int deltaY) {
        return stamp.translate(deltaX, deltaY);
    }

    @Override
    public Background scale(final double scaleX, final double scaleY) {
        return stamp.scale(scaleX, scaleY);
    }

    @Override
    public Background createNew(final Sprite backgroundElement, final Polygon newShape) {
        return stamp.createNew(backgroundElement, newShape);
    }

    @Override
    public Polygon getPolygon() {
        return stamp.getPolygon();
    }
}
