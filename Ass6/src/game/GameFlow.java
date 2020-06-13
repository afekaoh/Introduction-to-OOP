// ID 316044809
package game;

import biuoop.KeyboardSensor;
import game.animation.AnimationRunner;
import game.animation.states.EndScreenAnimation;
import game.animation.states.levels.GameLevel;
import game.animation.states.levels.LevelInformation;
import game.tools.Counter;

import java.util.List;

public class GameFlow {

    private final KeyboardSensor keyboardSensor;
    private final AnimationRunner animationRunner;
    private boolean win;

    public GameFlow() {
        this.animationRunner = new AnimationRunner(800, 600, "Arknoid");
        this.keyboardSensor = animationRunner.getKeyboardSensor();
        this.win = true;
    }

    public void runLevels(List<LevelInformation> levels) {
        final Counter score = new Counter();
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(
                    levelInfo,
                    this.keyboardSensor,
                    this.animationRunner,
                    score
            );

            level.initialize();

            do {
                level.run();
            } while (!level.shouldStop());

            if (level.isDead()) {
                win = false;
                break;
            }
        }
        animationRunner.run(new EndScreenAnimation(keyboardSensor, win, score.getValue()));
    }


    public void close() {
        animationRunner.close();
    }
}