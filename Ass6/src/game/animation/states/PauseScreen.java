// ID 316044809
package game.animation.states;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.animation.Animation;

import java.awt.Color;

public class PauseScreen implements Animation {
    private final KeyboardSensor keyboard;
    private boolean stop;

    public PauseScreen(final KeyboardSensor keyboard) {
        this.keyboard = keyboard;
        this.stop = false;
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }

    @Override
    public void doOneFrame(final DrawSurface canvas) {
        canvas.setColor(Color.BLACK);
        canvas.drawText(10, canvas.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }
}

