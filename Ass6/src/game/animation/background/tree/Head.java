// ID 316044809
package game.animation.background.tree;

import game.animation.background.Background;
import game.collections.Sprite;
import game.elements.shapes.Point;

import java.awt.Color;
import java.awt.Polygon;
import java.util.List;

/**
 * The class Head.
 */
public class Head extends Background {
    private static final Color COLOR = Color.GREEN.darker();
    /**
     * The Head.
     */
    private final Polygon head;

    /**
     * Instantiates a new Head.
     *
     * @param backgroundElement the background element
     * @param width             the width
     * @param height            the height
     */
    public Head(final Sprite backgroundElement, final int width, final int height) {
        super(backgroundElement, width, height);
        this.head = new Polygon();
        final Point start = new Point(getWidth() / 2 - 50, getHeight() - getHeight() / 6);
        List<Point> shape = List.of(
                start,
                start.translate(50, 150),
                start.translate(100, 0)
                                   );
        shape.forEach(p -> head.addPoint((int) p.getX(), (int) p.getY()));
    }

    /**
     * Instantiates a new Head.
     *
     * @param backgroundElement the background element
     * @param newShape          the new shape
     * @param width             the width
     * @param height            the height
     */
    public Head(final Sprite backgroundElement, final Polygon newShape, final int width, final int height) {
        super(backgroundElement, width, height);
        this.head = newShape;
    }

    @Override
    protected Color getColor() {
        return COLOR;
    }

    @Override
    public Background createNew(final Sprite backgroundElement, final Polygon newShape) {
        return new Head(backgroundElement, newShape, getWidth(), getHeight());
    }

    @Override
    public Background createNew(final Sprite backgroundElement) {
        return new Head(backgroundElement, getWidth(), getHeight());
    }

    @Override
    public Polygon getPolygon() {
        return this.head;
    }
}
