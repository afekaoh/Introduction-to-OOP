// ID 316044809
package game.animation.background;

import biuoop.DrawSurface;
import game.collections.Sprite;

import java.awt.Color;
import java.awt.Polygon;

/**
 * The class Hill.
 */
public class Hill extends Background {
    /**
     * The constant COLOR.
     */
    public static final Color COLOR = Color.GREEN.darker();
    /**
     * The Hill.
     */
    private final Polygon hill;

    /**
     * Instantiates a new Hill.
     *
     * @param backgroundElement the background element
     * @param width             the width
     * @param height            the height
     */
    public Hill(final Sprite backgroundElement, final int width, final int height) {
        super(backgroundElement, width, height);
        this.hill = new Polygon();
        final int radius = (int) (Math.random() * 200 + 200);
        for (int i = 180; i <= 360; i++) {
            int x = (int) (radius * Math.cos(Math.toRadians(i))) + 400;
            int y = (int) (radius * Math.sin(Math.toRadians(i))) + 600;
            hill.addPoint(x, y);
        }
    }

    /**
     * Instantiates a new Hill.
     *
     * @param backgroundElement the background element
     * @param hill              the hill
     * @param width             the width
     * @param height            the height
     */
    public Hill(final Sprite backgroundElement, final Polygon hill, final int width, final int height) {
        super(backgroundElement, width, height);
        this.hill = hill;
    }

    @Override
    public void drawOn(final DrawSurface canvas) {
        super.drawOn(canvas);
        canvas.setColor(Color.BLACK);
        canvas.drawPolygon(hill);
        canvas.setColor(Color.GREEN.darker());
        canvas.fillPolygon(hill);
    }

    @Override
    protected Color getColor() {
        return COLOR;
    }

    @Override
    public Background createNew(final Sprite backgroundElement, final Polygon newShape) {
        return new Hill(backgroundElement, newShape, getWidth(), getHeight());
    }

    @Override
    public Background createNew(final Sprite backgroundElement) {
        return new Hill(backgroundElement, getWidth(), getHeight());
    }

    @Override
    public Polygon getPolygon() {
        return hill;
    }
}
