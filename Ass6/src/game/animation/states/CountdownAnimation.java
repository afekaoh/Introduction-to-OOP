// ID 316044809
package game.animation.states;

import biuoop.DrawSurface;
import game.animation.Animation;
import game.collections.Sprite;
import game.collections.SpriteCollection;

import java.awt.Color;

/**
 * The class Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private final Sprite background;
    private final SpriteCollection backgroundScreen;
    private int countFrom;
    private boolean stop;


    /**
     * Instantiates a new Countdown animation.
     *
     * @param countFrom        the count from
     * @param numOfSeconds     the num of seconds
     * @param background       the background
     * @param backgroundScreen the background screen
     */
    public CountdownAnimation(final int countFrom, final double numOfSeconds, final Sprite background,
                              final SpriteCollection backgroundScreen) {
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.background = background;
        this.backgroundScreen = backgroundScreen;
        this.stop = false;
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }

    @Override
    public void doOneFrame(final DrawSurface canvas) {
        canvas.setColor(Color.BLACK);
        canvas.drawText(canvas.getWidth() / 2, canvas.getHeight() / 2, "" + countFrom, 64);
        countFrom--;
        if (countFrom < 0) {
            stop = true;
        }
    }

    @Override
    public void drawBackground(final DrawSurface canvas) {
        background.drawOn(canvas);
        backgroundScreen.drawAllOn(canvas);
    }

    @Override
    public int getFramePerSeconds() {
        return (int) numOfSeconds / countFrom;
    }
}

