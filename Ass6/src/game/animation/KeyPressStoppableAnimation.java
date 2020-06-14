// ID 316044809
package game.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The class Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor sensor;
    private final String key;
    private final Animation animation;
    private boolean stop;
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
        } else if (sensor.isPressed(key)) {
            stop = true;
        }
    }

    @Override
    public void drawBackground(final DrawSurface canvas) {
        animation.drawBackground(canvas);
    }
}
