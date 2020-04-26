// ID 316044809

import biuoop.DrawSurface;


/**
 * The interface Sprite.
 */
public interface Sprite {
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

    /**
     * Adding the sprite into the game.
     *
     * @param game the game
     */
    void addToGame(Game game);
}

