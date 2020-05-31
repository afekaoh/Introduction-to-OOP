// ID 316044809
package game.collections;

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
     * Instantiates a new game.collections.Sprite collection.
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
     * Remove sprite.
     *
     * @param sprite the sprite to remove
     */
    public void removeSprite(Sprite sprite) {
        sprites.remove(sprite);
    }


    /**
     * Notify all time passed.
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> s = new ArrayList<>(sprites);
        s.forEach(Sprite::timePassed);
    }

    /**
     * Draw all on.
     * call drawOn(canvas) on all sprites.
     *
     * @param canvas the canvas
     */
    public void drawAllOn(DrawSurface canvas) {
        sprites.forEach(s -> s.drawOn(canvas));
    }
}
