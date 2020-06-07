// ID 316044809
package game.animation;

import biuoop.DrawSurface;

public interface Animation {
    boolean shouldStop();

    void doOneFrame(DrawSurface canvas);

    void drawBackground(DrawSurface canvas);

    int getFramePerSeconds();
}
