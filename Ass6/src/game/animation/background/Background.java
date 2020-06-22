// ID 316044809
package game.animation.background;

import biuoop.DrawSurface;
import game.collections.ElementsCollection;
import game.collections.Sprite;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.util.Random;

/**
 * The class Background.
 */
public abstract class Background implements Sprite {
    /**
     * The Background element.
     */
    private final Sprite backgroundElement;
    /**
     * The Width.
     */
    private final int width;
    /**
     * The Height.
     */
    private final int height;

    /**
     * Instantiates a new Background.
     *
     * @param backgroundElement the background element
     * @param width             the width
     * @param height            the height
     */
    public Background(final Sprite backgroundElement, final int width, final int height) {
        this.backgroundElement = backgroundElement;
        this.width = width;
        this.height = height;
    }

    /**
     * Create random elements background.
     *
     * @param i          the
     * @param background the background
     * @param rand       the rand
     * @return the background
     */
    public static Background createRandomElements(final int i, Background background, Random rand) {
        if (i == 0) {
            return background;
        }
        final double scaleX = (Math.random() * 6 + 7) / 10;
        final double scaleY = (Math.random() * 6 + 7) / 10;
        final Background b = background.createNew(background)
                                       .scale(scaleX, scaleY);
        final int elementHeight = (int) b.getPolygon().getBounds().getHeight();
        final int elementBottom = (int) (b.getPolygon().getBounds().getLocation().getY() + elementHeight / 2);

        return createRandomElements(
                i - 1,
                b.translate(
                        rand.nextInt(background.width) - background.getWidth() / 2,
                        (background.height - elementBottom)
                           ),
                rand
                                   );
    }

    @Override
    public void drawOn(final DrawSurface canvas) {
        backgroundElement.drawOn(canvas);
    }

    @Override
    public void timePassed() {
        backgroundElement.timePassed();
    }

    /**
     * Get color.
     *
     * @return the color
     */
    protected abstract Color getColor();

    @Override
    public void addToGame(final ElementsCollection elementsCollection) {
        elementsCollection.addSprite(this);
    }

    @Override
    public void removeFromGame(final ElementsCollection elementsCollection) {
        elementsCollection.removeSprite(this);
    }


    /**
     * Translate the background element to a different location.
     *
     * @param deltaX the delta x
     * @param deltaY the delta y
     * @return the background translated
     */
    public Background translate(int deltaX, int deltaY) {
        AffineTransform tx = new AffineTransform();
        tx.translate(deltaX, deltaY);
        return applyTransformation(tx);
    }

    /**
     * Scale the background element.
     *
     * @param scaleX the scale x
     * @param scaleY the scale y
     * @return the background scaled
     */
    public Background scale(double scaleX, double scaleY) {
        AffineTransform tx = new AffineTransform();
        tx.scale(scaleX, scaleY);
        return applyTransformation(tx);
    }

    /**
     * Apply transformation background.
     *
     * @param tx the AffineTransform to apply
     * @return the background after the transformation
     */
    private Background applyTransformation(final AffineTransform tx) {
        Polygon oldShape = getPolygon();
        Polygon newShape = new Polygon();
        PathIterator pI = oldShape.getPathIterator(tx);
        double[] arr = new double[2];
        while (!pI.isDone()) {
            pI.currentSegment(arr);
            newShape.addPoint((int) arr[0], (int) arr[1]);
            pI.next();
        }
        return createNew(backgroundElement, newShape);
    }

    /**
     * Create new background.
     *
     * @param bE       the background element
     * @param newShape the new shape
     * @return the background
     */
    public abstract Background createNew(Sprite bE, Polygon newShape);

    /**
     * Create new background.
     *
     * @param bE the b e
     * @return the background
     */
    public abstract Background createNew(Sprite bE);

    /**
     * Get polygon.
     *
     * @return the polygon
     */
    public abstract Polygon getPolygon();

    /**
     * Get width.
     *
     * @return the width
     */
    protected int getWidth() {
        return width;
    }

    /**
     * Get height.
     *
     * @return the height
     */
    protected int getHeight() {
        return height;
    }
}
