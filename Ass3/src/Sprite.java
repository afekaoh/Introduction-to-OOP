import biuoop.DrawSurface;

//todo doc

/**
 * The interface Sprite.
 */
// ID 316044809
public interface Sprite {
    /**
     * Draw on.
     *
     * @param d the d
     */
// draw the sprite to the screen
    void drawOn(DrawSurface d);

    /**
     * Time passed.
     */
// notify the sprite that time has passed
    void timePassed();
}

