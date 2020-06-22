// ID 316044809
package game.animation.background;

import biuoop.DrawSurface;
import game.collections.Sprite;

import java.awt.Color;
import java.awt.Polygon;

/**
 * The class Bush.
 */
public class Bush extends Background {

    private static final Color COLOR = Color.GREEN.darker().darker().darker();
    /**
     * The Bush.
     */
    private final Polygon bush;

    /**
     * Instantiates a new Bush.
     *
     * @param backgroundElement the background element
     * @param width             the width
     * @param height            the height
     */
    public Bush(final Sprite backgroundElement, final int width, final int height) {
        super(backgroundElement, width, height);
        bush = new Cloud(null, width, height).translate(200, 400).scale(0.8, 1.2).getPolygon();
    }

    /**
     * Instantiates a new Bush.
     *
     * @param backgroundElement the background element
     * @param newShape          the new shape
     * @param width             the width
     * @param height            the height
     */
    public Bush(final Sprite backgroundElement, final Polygon newShape, final int width, final int height) {
        super(backgroundElement, width, height);
        this.bush = newShape;
    }

    @Override
    public void drawOn(final DrawSurface canvas) {
        super.drawOn(canvas);
        canvas.setColor(Color.BLACK);
        canvas.drawPolygon(bush);
        canvas.setColor(Color.GREEN.darker().darker());
        canvas.fillPolygon(bush);
    }

    @Override
    protected Color getColor() {
        return COLOR;
    }

    @Override
    public Background createNew(final Sprite backgroundElement, final Polygon newShape) {
        return new Bush(backgroundElement, newShape, getWidth(), getHeight());
    }

    @Override
    public Background createNew(final Sprite backgroundElement) {
        return new Bush(backgroundElement, getWidth(), getHeight());
    }

    @Override
    public Polygon getPolygon() {
        return bush;
    }
}
