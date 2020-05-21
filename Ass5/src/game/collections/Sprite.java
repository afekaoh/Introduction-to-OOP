// ID 316044809
package game.collections;

import biuoop.DrawSurface;


/**
 * The interface game.collections.Sprite.
 */
public interface Sprite extends GameElement {
    /**
     * Draw on.
     * draw the sprite to the screen
     *
     * @param d the DrawSurface which we draw to
     */
    void drawOn(DrawSurface d);

    /**
     * Time passed.
     * notify the sprite that time has passed
     */
    void timePassed();
}

