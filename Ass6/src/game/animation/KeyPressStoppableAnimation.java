// ID 316044809
package game.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor sensor;
    private final String key;
    private final Animation animation;
    private boolean stop;

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
        if (sensor.isPressed(key)) {
            stop = true;
        }
    }

    @Override
    public void drawBackground(final DrawSurface canvas) {
        animation.doOneFrame(canvas);
    }
}
