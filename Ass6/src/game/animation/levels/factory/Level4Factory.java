// ID 316044809
package game.animation.levels.factory;

import game.animation.levels.Level;
import game.animation.levels.Level4;

/**
 * The class Level 4 factory.
 */
public class Level4Factory implements LevelFactory {
    /**
     * The Width.
     */
    private final int width;
    /**
     * The Height.
     */
    private final int height;

    /**
     * Instantiates a new Level 4 factory.
     *
     * @param width  the width
     * @param height the height
     */
    public Level4Factory(final int width, final int height) {

        this.width = width;
        this.height = height;
    }

    @Override
    public Level createLevel() {
        return new Level4(width, height);
    }
}
