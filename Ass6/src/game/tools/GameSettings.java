// ID 316044809
package game.tools;


import biuoop.KeyboardSensor;
import game.collections.GameEnvironment;

/**
 * The class GameLevel settings.
 */
public class GameSettings {
    /**
     * The game.levels.GameLevel boundary.
     */
    private final GameEnvironment environment;
    /**
     * The Keyboard.
     */
    private final KeyboardSensor keyboard;

    /**
     * Instantiates a new GameLevel settings.
     *
     * @param environment the game edges
     * @param keyboard    the keyboard Sensor
     */
    public GameSettings(GameEnvironment environment, KeyboardSensor keyboard) {
        this.environment = environment;
        this.keyboard = keyboard;
    }

    /**
     * Gets keyboard.
     *
     * @return the keyboard Sensor
     */
    public KeyboardSensor getKeyboard() {
        return keyboard;
    }

    /**
     * Get game edge.
     *
     * @return the game boundary
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }
}
