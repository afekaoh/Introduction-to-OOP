// ID 316044809
//todo doc


import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The class Sprite collection.
 */
public class SpriteCollection {
    /**
     * The Sprites.
     */
    private final List<Sprite> sprites;

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        sprites = new ArrayList<>();
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Notify all time passed.
     */
// call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        for (Sprite s : sprites) {
            s.timePassed();
        }
    }

    /**
     * Draw all on.
     *
     * @param canvas the canvas
     */
// call drawOn(canvas) on all sprites.
    public void drawAllOn(DrawSurface canvas) {
        for (Sprite s : sprites) {
            s.drawOn(canvas);
        }
    }
}
