// ID 316044809
package game.animation;

import biuoop.DrawSurface;

public interface Animation {
    int FRAMES_PER_SECONDS = 60;

    boolean shouldStop();

    void doOneFrame(DrawSurface canvas);

    void drawBackground(DrawSurface canvas);

    default int getFramePerSeconds() {
        return FRAMES_PER_SECONDS;
    }
}
