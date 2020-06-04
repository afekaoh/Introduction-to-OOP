// ID 316044809
package game.animation.states;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.animation.Animation;
import game.collections.SpriteCollection;

public class CountdownAnimation implements Animation {
    private final int countFrom;
    private final double numOfSeconds;
    private final SpriteCollection backgroungScreen;
    private final Sleeper sleeper;
    private boolean stop;


    public CountdownAnimation(final int countFrom, final double numOfSeconds,
                              final SpriteCollection backgroundScreen, final Sleeper sleeper) {
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.backgroungScreen = backgroundScreen;
        this.sleeper = sleeper;
        this.stop = false;
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }

    @Override
    public void doOneFrame(final DrawSurface canvas) {
        for (int i = countFrom; i > 0; i--) {
            backgroungScreen.drawAllOn(canvas);
            sleeper.sleepFor((long) (numOfSeconds / countFrom));
        }
        stop = true;
    }
}
