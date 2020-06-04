// ID 316044809
package game.elements.objects;

import biuoop.DrawSurface;
import game.collections.ElementsCollection;
import game.collections.Sprite;
import game.elements.shapes.Rectangle;
import game.tools.Counter;

import java.awt.Color;

/**
 * The class Score indicator.
 */
public class ScoreIndicator implements Sprite {
    public static final int FONT_SIZE = 16;
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
    private final int indicatorX;
    private final int indicatorY;
    private final Color textColor;

    /**
     * Instantiates a new Score indicator.
     *
     * @param boundary the boundary
     * @param score    the score
     */
    public ScoreIndicator(final Rectangle boundary, final Counter score) {
        this.boundary = boundary;
        this.score = score;
        this.color = Color.decode("#EEE1AC");
        this.textColor = Color.decode("#1D0140");
        indicatorX = (int) this.boundary.getCenter().getX();
        indicatorY = (int) this.boundary.getCenter().getY() + this.boundary.bottom() / 2 - 2;
    }

    @Override
    public void drawOn(final DrawSurface canvas) {
        canvas.setColor(color);
        canvas.fillRectangle(boundary.left(), boundary.top(), boundary.getWidth(), boundary.getHeight());
        canvas.setColor(textColor);
        canvas.drawText(indicatorX, indicatorY, "Score: " + score.toString(), FONT_SIZE);
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
