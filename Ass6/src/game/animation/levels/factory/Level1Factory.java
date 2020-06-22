// ID 316044809
package game.animation.levels.factory;

import game.animation.levels.Level;
import game.animation.levels.Level1;

/**
 * The class Level 1 factory.
 */
public class Level1Factory implements LevelFactory {

    /**
     * The Width.
     */
    private final int width;
    /**
     * The Height.
     */
    private final int height;

    /**
     * Instantiates a new Level 1 factory.
     *
     * @param width  the width
     * @param height the height
     */
    public Level1Factory(int width, int height) {

        this.width = width;
        this.height = height;
    }

    @Override
    public Level createLevel() {
        return new Level1(width, height);
    }
}
