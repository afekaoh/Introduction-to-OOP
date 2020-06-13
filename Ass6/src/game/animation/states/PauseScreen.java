// ID 316044809
package game.animation.states;

import biuoop.DrawSurface;
import game.animation.Animation;

import java.awt.Color;

/**
 * The class Pause screen.
 */
public class PauseScreen implements Animation {

    @Override
    public boolean shouldStop() {
        return true;
    }

    @Override
    public void doOneFrame(final DrawSurface canvas) {
        canvas.setColor(Color.BLACK);
        canvas.drawText(10, canvas.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public void drawBackground(final DrawSurface canvas) {
        canvas.setColor(Color.white);
        canvas.fillRectangle(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    @Override
    public int getFramePerSeconds() {
        return 60;
    }
}

