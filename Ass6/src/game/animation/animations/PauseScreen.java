// ID 316044809
package game.animation.animations;

import biuoop.DrawSurface;
import game.animation.Animation;

import java.awt.Color;

/**
 * The class Pause screen.
 */
public class PauseScreen implements Animation {

    @Override
    public boolean shouldStop() {
        return false;
    }

    @Override
    public void doOneFrame(final DrawSurface canvas) {
        canvas.setColor(Color.BLACK);
        canvas.drawText(10, canvas.getHeight() / 2, "paused -- press space to continue", 32);
    }
}

