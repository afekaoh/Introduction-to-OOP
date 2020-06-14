// ID 316044809
package game.animation.levels.factory;

import game.animation.levels.Level;

/**
 * The interface Level factory.
 */
@FunctionalInterface
public interface LevelFactory {
    /**
     * Create level level.
     *
     * @return the level
     */
    Level createLevel();
}