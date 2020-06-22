// ID 316044809
package game;

import biuoop.KeyboardSensor;
import game.animation.AnimationRunner;
import game.animation.KeyPressStoppableAnimation;
import game.animation.animations.endgame.EndScreenAnimation;
import game.animation.animations.endgame.GameOverAnimation;
import game.animation.animations.endgame.WinAnimation;
import game.animation.levels.GameLevel;
import game.animation.levels.LevelInformation;
import game.tools.Counter;

import java.util.List;

/**
 * The class Game flow.
 */
public class GameFlow {

    /**
     * The Keyboard sensor.
     */
    private final KeyboardSensor keyboardSensor;
    /**
     * The Animation runner.
     */
    private final AnimationRunner animationRunner;
    /**
     * The Win.
     */
    private boolean win;

    /**
     * Instantiates a new Game flow.
     *
     * @param animationRunner the animation runner
     */
    public GameFlow(final AnimationRunner animationRunner) {
        this.animationRunner = animationRunner;
        this.keyboardSensor = this.animationRunner.getKeyboardSensor();
        this.win = true;
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        final Counter score = new Counter();
        EndScreenAnimation endScreen;
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(
                    levelInfo,
                    this.keyboardSensor,
                    this.animationRunner,
                    score
            );

            level.initialize();
            level.run();

            if (level.isDead()) {
                win = false;
                break;
            }
        }
        if (win) {
            endScreen = new WinAnimation(score.getValue());
        } else {
            endScreen = new GameOverAnimation(score.getValue());
        }
        animationRunner.run(
                new KeyPressStoppableAnimation(
                        keyboardSensor,
                        KeyboardSensor.SPACE_KEY,
                        endScreen
                ));
    }


    /**
     * Close the game.
     */
    public void close() {
        animationRunner.close();
    }
}