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
    /**
     * The constant FONT_SIZE.
     */
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
    /**
     * The Text color.
     */
    private final Color textColor;
    /**
     * The Lives.
     */
    private final Counter lives;
    /**
     * The Name.
     */
    private final String name;

    /**
     * Instantiates a new Score indicator.
     *
     * @param boundary the boundary
     * @param name     the name
     * @param counters the counters
     */
    public ScoreIndicator(final Rectangle boundary, final String name, final Counter... counters) {
        this.boundary = boundary;
        this.score = counters[0];
        this.lives = counters[1];
        this.name = name;
        this.color = Color.decode("#EEE1AC");
        this.textColor = Color.decode("#1D0140");
    }

    @Override
    public void drawOn(final DrawSurface canvas) {
        int scoreX = (int) this.boundary.getCenter().getX();
        int livesX = (int) this.boundary.getCenter().getX() / 4;
        int nameX = (3 * this.boundary.right()) / 4;
        int textY = (int) this.boundary.getCenter().getY() + this.boundary.bottom() / 2 - 2;
        canvas.setColor(color);
        canvas.fillRectangle(boundary.left(), boundary.top(), boundary.getWidth(), boundary.getHeight());
        canvas.setColor(textColor);
        canvas.drawText(scoreX, textY, "Score: " + score.toString(), FONT_SIZE);
        canvas.drawText(livesX, textY, "Lives: " + lives.toString(), FONT_SIZE);
        canvas.drawText(nameX, textY, "Name: " + name, FONT_SIZE);
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
