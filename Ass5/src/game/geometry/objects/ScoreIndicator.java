package game.geometry.objects;

import biuoop.DrawSurface;
import game.collections.ElementsCollection;
import game.collections.Sprite;
import game.geometry.shapes.Rectangle;
import game.tools.Counter;

import java.awt.Color;

// ID 316044809
public class ScoreIndicator implements Sprite {
    private final Rectangle boundary;
    private final Color color;
    private final Counter score;

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
        canvas.drawText(
                (int) boundary.getCenter().getX(), (int) boundary.getCenter().getY() + 10, score.toString(), 16);
    }

    @Override
    public void timePassed() {
        //
    }

    @Override
    public void addToGame(final ElementsCollection elementsCollection) {
        elementsCollection.addSprite(this);
    }

    @Override
    public void removeFromGame(final ElementsCollection elementsCollection) {
        //
    }

    public int getHeight() {
        return boundary.getHeight();
    }
}
