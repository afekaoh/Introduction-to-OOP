// ID 316044809
package game.animation.background.tree;

import biuoop.DrawSurface;
import game.animation.background.Background;
import game.collections.Sprite;

import java.awt.Color;
import java.awt.Polygon;

/**
 * The class Tree.
 */
public class Tree extends Background {
    /**
     * The Stamp.
     */
    private final Background stamp;

    /**
     * Instantiates a new Tree.
     *
     * @param backgroundElement the background element
     * @param width             the width
     * @param height            the height
     */
    public Tree(final Sprite backgroundElement, final int width, final int height) {
        super(backgroundElement, width, height);
        this.stamp = new Stamp(backgroundElement, getWidth(), getHeight());
    }

    @Override
    public void drawOn(final DrawSurface canvas) {
        super.drawOn(canvas);
        stamp.drawOn(canvas);
    }

    @Override
    protected Color getColor() {
        return null;
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
    public Background createNew(final Sprite backgroundElement) {
        return new Tree(backgroundElement, getWidth(), getHeight());
    }

    @Override
    public Polygon getPolygon() {
        return stamp.getPolygon();
    }
}
