// ID 316044809
package game.animation.background.tree;

import biuoop.DrawSurface;
import game.animation.background.Background;
import game.collections.Sprite;

import java.awt.Color;
import java.awt.Polygon;

/**
 * The class Stamp.
 */
public class Stamp extends Background {
    /**
     * The Stamp.
     */
    private final Polygon stamp;
    /**
     * The Background.
     */
    private final Sprite background;
    /**
     * The Head.
     */
    private final Background head;

    /**
     * Instantiates a new Stamp.
     *
     * @param backgroundElement the background element
     * @param width             the width
     * @param height            the height
     */
    public Stamp(final Sprite backgroundElement, final int width, final int height) {
        super(backgroundElement, width, height);
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
        this.head = new Head(backgroundElement, width, height);
    }

    /**
     * Instantiates a new Stamp.
     *
     * @param backgroundElement the background element
     * @param stamp             the stamp
     * @param head              the head
     * @param width             the width
     * @param height            the height
     */
    public Stamp(final Sprite backgroundElement, final Background stamp, final Background head, final int width,
                 final int height) {
        super(backgroundElement, width, height);
        this.background = backgroundElement;
        this.head = head;
        this.stamp = stamp.getPolygon();
    }

    /**
     * Instantiates a new Stamp.
     *
     * @param backgroundElement the background element
     * @param newShape          the new shape
     * @param head              the head
     * @param width             the width
     * @param height            the height
     */
    public Stamp(final Sprite backgroundElement, final Polygon newShape, final Background head, final int width,
                 final int height) {
        super(backgroundElement, width, height);
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
    protected Color getColor() {
        return null;
    }

    @Override
    public Background translate(final int deltaX, final int deltaY) {
        Background temp = super.translate(deltaX, deltaY);
        return new Stamp(background, temp, head.translate(deltaX, deltaY), getWidth(), getHeight());
    }

    @Override
    public Background scale(final double scaleX, final double scaleY) {
        Background temp = super.scale(scaleX, scaleY);
        return new Stamp(background, temp, head.scale(scaleX, scaleY), getWidth(), getHeight());
    }

    @Override
    public Background createNew(final Sprite backgroundElement, final Polygon newShape) {
        return new Stamp(backgroundElement, newShape, head, getWidth(), getHeight());
    }

    @Override
    public Background createNew(final Sprite backgroundElement) {
        return new Stamp(backgroundElement, getWidth(), getHeight());
    }

    @Override
    public Polygon getPolygon() {
        return stamp;
    }
}
