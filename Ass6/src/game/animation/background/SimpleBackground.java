// ID 316044809
package game.animation.background;

import biuoop.DrawSurface;
import game.collections.ElementsCollection;
import game.collections.Sprite;

import java.awt.Color;

/**
 * The class Simple background.
 */
public class SimpleBackground implements Sprite {

    /**
     * The Color 1.
     */
    private final Color color1;

    /**
     * Instantiates a new Simple background.
     *
     * @param color the color
     */
    public SimpleBackground(Color color) {
        this.color1 = color;
    }

    @Override
    public void drawOn(final DrawSurface canvas) {
        canvas.setColor(color1);
        canvas.fillRectangle(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(final ElementsCollection elementsCollection) {

    }

    @Override
    public void removeFromGame(final ElementsCollection elementsCollection) {

    }
}
