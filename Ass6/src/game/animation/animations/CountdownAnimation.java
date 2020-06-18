// ID 316044809
package game.animation.animations;

import biuoop.DrawSurface;
import game.animation.Animation;
import game.collections.SpriteCollection;

import java.awt.Color;

/**
 * The class Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private final SpriteCollection backgroundScreen;
    private int countFrom;
    private boolean stop;


    /**
     * Instantiates a new Countdown animation.
     *
     * @param countFrom    the count from
     * @param numOfSeconds the num of seconds
     * @param background   the background screen
     */
    public CountdownAnimation(final int countFrom, final double numOfSeconds, final SpriteCollection background) {
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.backgroundScreen = background;
        this.stop = false;
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }

    @Override
    public void doOneFrame(final DrawSurface canvas) {
        backgroundScreen.drawAllOn(canvas);
        canvas.setColor(Color.BLACK);
        canvas.drawText(canvas.getWidth() / 2, canvas.getHeight() / 2, "" + countFrom, 64);
        countFrom--;
        if (countFrom < 0) {
            stop = true;
        }
    }

    @Override
    public double getFramePerSeconds() {
        final double framesPerSeconds = numOfSeconds / countFrom;
        return framesPerSeconds > 1 ? framesPerSeconds : 1 / framesPerSeconds;
    }
}

