// ID 316044809
package game.animation.background;

import biuoop.DrawSurface;
import game.collections.ElementsCollection;
import game.collections.Sprite;

import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.util.Random;

public abstract class Background implements Sprite {
    private final Sprite backgroundElement;

    public Background(final Sprite backgroundElement) {
        this.backgroundElement = backgroundElement;
    }

    public static Background createRandomElements(final int i, Background background, Random rand) {
        if (i == 0) {
            return background;
        }
        final double scaleX = (Math.random() * 6 + 7) / 10;
        final double scaleY = (Math.random() * 6 + 7) / 10;
        final Background b = background.createNew(background)
                                       .scale(scaleX, scaleY);
        final double elementHeight = b.getPolygon().getBounds().getHeight();
        final double elementBottom = b.getPolygon().getBounds().getLocation().getY() + elementHeight / 2;

        return createRandomElements(
                i - 1,
                b.translate((int) rand.nextInt(800) - 400, (int) (600 - elementBottom)),
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

    public abstract Background createNew(final Sprite bE, final Polygon newShape);

    public abstract Background createNew(final Sprite bE);

    public abstract Polygon getPolygon();
}
