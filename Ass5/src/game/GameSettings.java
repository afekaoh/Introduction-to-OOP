package game;// ID 316044809

import biuoop.KeyboardSensor;
import game.collections.GameEnvironment;

/**
 * The class game.Game settings.
 */
public class GameSettings {
    /**
     * The game.Game boundary.
     */
    private final GameEnvironment environment;
    /**
     * The Keyboard.
     */
    private final KeyboardSensor keyboard;

    /**
     * Instantiates a new game.Game settings.
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
