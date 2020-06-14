// ID 316044809
package game.animation.levels.factory;

import game.animation.levels.Level;
import game.animation.levels.Level3;

public class Level3Factory implements LevelFactory {
    private final int width;
    private final int height;

    public Level3Factory(final int width, final int height) {

        this.width = width;
        this.height = height;
    }

    @Override
    public Level createLevel() {
        return new Level3(width, height);
    }
}
