// ID 316044809

import biuoop.KeyboardSensor;

/**
 * The class Game settings.
 */
public final class GameSettings {
    /**
     * The Game boundary.
     */
    private final Rectangle gameEdge;
    /**
     * The Keyboard.
     */
    private final KeyboardSensor keyboard;


    /**
     * Instantiates a new Game settings.
     *
     * @param gameEdge the game edge
     * @param keyboard the keyboard Sensor
     */
    public GameSettings(Rectangle gameEdge, KeyboardSensor keyboard) {
        this.gameEdge = gameEdge;
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
    public Rectangle getGameEdge() {
        return gameEdge;
    }
}
