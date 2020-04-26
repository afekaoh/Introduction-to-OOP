// ID 316044809

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
     * @param sprite the sprite
     */
    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }

    /**
     * Notify all time passed.
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    /**
     * Draw all on.
     * call drawOn(canvas) on all sprites.
     *
     * @param canvas the canvas
     */
    public void drawAllOn(DrawSurface canvas) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(canvas);
        }
    }
}
