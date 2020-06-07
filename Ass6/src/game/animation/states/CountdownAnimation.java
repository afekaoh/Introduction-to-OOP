// ID 316044809
package game.animation.states;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.animation.Animation;
import game.collections.SpriteCollection;

import java.awt.Color;

public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private final SpriteCollection backgroundScreen;
    private final Sleeper sleeper;
    private int countFrom;
    private boolean stop;


    public CountdownAnimation(final int countFrom, final double numOfSeconds,
                              final SpriteCollection backgroundScreen, final Sleeper sleeper) {
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.backgroundScreen = backgroundScreen;
        this.sleeper = sleeper;
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
        backgroundScreen.drawAllOn(canvas);
        countFrom--;
        if (countFrom == 0) {
            stop = true;
            return;
        }
        sleeper.sleepFor(((long) (numOfSeconds / countFrom)) * 1000);
    }
}

