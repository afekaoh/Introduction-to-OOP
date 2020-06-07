// ID 316044809

import game.levels.GameLevel;

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
        GameLevel gameLevel = new GameLevel(800, 600, "game");
        gameLevel.initialize();
        gameLevel.run();
        gameLevel.closeGame();
    }
}
