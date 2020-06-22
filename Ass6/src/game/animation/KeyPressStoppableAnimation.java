// ID 316044809
package game.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The class Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    /**
     * The Sensor.
     */
    private final KeyboardSensor sensor;
    /**
     * The Key.
     */
    private final String key;
    /**
     * The Animation.
     */
    private final Animation animation;
    /**
     * The Stop.
     */
    private boolean stop;
    /**
     * The Is already pressed.
     */
    private boolean isAlreadyPressed = true;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }

    @Override
    public void doOneFrame(final DrawSurface canvas) {
        animation.doOneFrame(canvas);
        if (isAlreadyPressed && !sensor.isPressed(key)) {
            isAlreadyPressed = false;
        }
        if (!isAlreadyPressed && sensor.isPressed(key)) {
            stop = true;
        }
    }
}
