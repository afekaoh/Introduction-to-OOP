// ID 316044809

import game.Game;

/**
 * The class Ass 5 game.
 * creating, initialize and run a new game
 */
public class Ass5Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Game game = new Game(800, 600, "");
        game.initialize();
        game.run();
        game.closeGame();
    }
}
