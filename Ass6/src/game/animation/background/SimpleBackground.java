// ID 316044809
package game.animation.background;

import biuoop.DrawSurface;
import game.collections.ElementsCollection;
import game.collections.Sprite;

import java.awt.Color;

public class SimpleBackground implements Sprite {

    private final Color color;

    public SimpleBackground(Color color) {
        this.color = color;
    }

    @Override
    public void drawOn(final DrawSurface canvas) {
        canvas.setColor(color);
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
