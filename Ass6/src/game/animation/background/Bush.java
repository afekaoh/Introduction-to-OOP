// ID 316044809
package game.animation.background;

import biuoop.DrawSurface;
import game.collections.Sprite;

import java.awt.Color;
import java.awt.Polygon;

public class Bush extends Background {

    private final Polygon bush;

    public Bush(final Sprite backgroundElement) {
        super(backgroundElement);
        bush = new Cloud(null).translate(200, 400).scale(0.8, 1.2).getPolygon();
    }

    public Bush(final Sprite backgroundElement, final Polygon newShape) {
        super(backgroundElement);
        this.bush = newShape;
    }

    @Override
    public void drawOn(final DrawSurface canvas) {
        super.drawOn(canvas);
        canvas.setColor(Color.GREEN);
        canvas.fillPolygon(bush);
        canvas.setColor(Color.BLACK);
        canvas.drawPolygon(bush);
    }

    @Override
    public Background createNew(final Sprite backgroundElement, final Polygon newShape) {
        return new Bush(backgroundElement, newShape);
    }

    @Override
    public Background createNew(final Sprite backgroundElement) {
        return new Bush(backgroundElement);
    }

    @Override
    public Polygon getPolygon() {
        return bush;
    }
}
