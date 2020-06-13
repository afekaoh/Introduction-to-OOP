// ID 316044809

import game.GameFlow;
import game.animation.states.levels.Level1;
import game.animation.states.levels.Level2;
import game.animation.states.levels.Level3;
import game.animation.states.levels.Level4;
import game.animation.states.levels.LevelInformation;

import java.util.List;

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
        List<LevelInformation> levels = List.of(new Level1(), new Level2(), new Level3(), new Level4());
        GameFlow game = new GameFlow();
        game.runLevels(levels);
        game.close();
    }
}
