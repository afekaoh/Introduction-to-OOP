// ID 316044809
package game.animation.background;

import biuoop.DrawSurface;
import game.collections.ElementsCollection;
import game.collections.Sprite;

import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;

public abstract class Background implements Sprite {
    private final Sprite backgroundElement;

    public Background(final Sprite backgroundElement) {
        this.backgroundElement = backgroundElement;
    }

    @Override
    public void drawOn(final DrawSurface canvas) {
        backgroundElement.drawOn(canvas);
    }

    @Override
    public void timePassed() {
        backgroundElement.timePassed();
    }

    @Override
    public void addToGame(final ElementsCollection elementsCollection) {
        elementsCollection.addSprite(this);
    }

    @Override
    public void removeFromGame(final ElementsCollection elementsCollection) {
        elementsCollection.removeSprite(this);
    }


    public Background translate(int deltaX, int deltaY) {
        AffineTransform tx = new AffineTransform();
        tx.translate(deltaX, deltaY);
        return applyTransformation(tx);
    }

    public Background scale(double scaleX, double scaleY) {
        AffineTransform tx = new AffineTransform();
        tx.scale(scaleX, scaleY);
        return applyTransformation(tx);
    }

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

    public abstract Background createNew(final Sprite backgroundElement, final Polygon newShape);

    public abstract Polygon getPolygon();
}
