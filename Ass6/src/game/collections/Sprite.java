// ID 316044809
package game.collections;

import biuoop.DrawSurface;


/**
 * The interface Sprite.
 */
public interface Sprite extends GameElement {
    /**
     * Draw on.
     * draw the sprite to the screen
     *
     * @param canvas the DrawSurface which we draw to
     */
    void drawOn(DrawSurface canvas);

    /**
     * Time passed.
     * notify the sprite that time has passed
     */
    void timePassed();
}

