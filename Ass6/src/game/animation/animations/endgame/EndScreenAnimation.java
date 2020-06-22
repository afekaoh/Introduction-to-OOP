// ID 316044809
package game.animation.animations.endgame;

import biuoop.DrawSurface;
import game.animation.Animation;

import java.awt.Color;

/**
 * The class End screen animation.
 */
public abstract class EndScreenAnimation implements Animation {
    /**
     * The Text.
     */
    private String text;

    /**
     * Instantiates a new End screen animation.
     *
     * @param score the score
     */
    public EndScreenAnimation(int score) {
        text = getEndText();
        text += " Your score is " + score;
    }

    /**
     * Get end text.
     *
     * @return the end text
     */
    protected abstract String getEndText();

    @Override
    public boolean shouldStop() {
        return true;
    }

    @Override
    public void doOneFrame(final DrawSurface canvas) {
        canvas.setColor(Color.BLACK);
        canvas.drawText(0, canvas.getHeight() / 2, text, 32);
    }
}
