// ID 316044809
package game.animation.levels.factory;

import game.animation.levels.Level;
import game.animation.levels.Level1;

public class Level1Factory implements LevelFactory {

    private final int width;
    private final int height;

    public Level1Factory(int width, int height) {

        this.width = width;
        this.height = height;
    }

    @Override
    public Level createLevel() {
        return new Level1(width, height);
    }
}
