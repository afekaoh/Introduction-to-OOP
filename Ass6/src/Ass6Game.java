// ID 316044809

import game.animation.states.levels.GameLevel;
import game.animation.states.levels.Level1;
import game.animation.states.levels.LevelInformation;

/**
 * The class Ass 5 game.
 * creating, initialize and run a new game
 */
public class Ass6Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        LevelInformation level1 = new Level1();
        GameLevel gameLevel = new GameLevel(level1);
        gameLevel.initialize();
        gameLevel.run();
        gameLevel.closeGame();
    }
}
