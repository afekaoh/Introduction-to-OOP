// ID 316044809
package game.geometry.objects;

import biuoop.DrawSurface;
import game.collections.ElementsCollection;
import game.collections.Sprite;
import game.geometry.shapes.Rectangle;
import game.tools.Counter;

import java.awt.Color;


/**
 * The class Score indicator.
 */
public class ScoreIndicator implements Sprite {
    /**
     * The Boundary.
     */
    private final Rectangle boundary;
    /**
     * The Color.
     */
    private final Color color;
    /**
     * The Score.
     */
    private final Counter score;

    /**
     * Instantiates a new Score indicator.
     *
     * @param boundary the boundary
     * @param color    the color
     * @param score    the score
     */
    public ScoreIndicator(final Rectangle boundary, final Color color, final Counter score) {
        this.boundary = boundary;
        this.color = color;
        this.score = score;
    }

    @Override
    public void drawOn(final DrawSurface canvas) {
        canvas.setColor(color);
        canvas.fillRectangle(boundary.left(), boundary.top(), boundary.getWidth(), boundary.getHeight());
        canvas.setColor(Color.BLACK);
        final int indicatorX = (int) boundary.getCenter().getX();
        final int indicatorY = (int) boundary.getCenter().getY() + boundary.bottom() / 2;
        final int fontSize = 16;
        canvas.drawText(indicatorX, indicatorY, score.toString(), fontSize);
    }

    @Override
    public void timePassed() {
        // do nothing
    }

    @Override
    public void addToGame(final ElementsCollection elementsCollection) {
        elementsCollection.addSprite(this);
    }

    @Override
    public void removeFromGame(final ElementsCollection elementsCollection) {
        elementsCollection.removeSprite(this);
        elementsCollection.removeElement(this);
    }
}
