// ID 316044809

import biuoop.KeyboardSensor;

/**
 * The class Game settings.
 */
public class GameSettings {
    /**
     * The Game boundary.
     */
    private final GameEnvironment environment;
    /**
     * The Keyboard.
     */
    private final KeyboardSensor keyboard;

    /**
     * Instantiates a new Game settings.
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
