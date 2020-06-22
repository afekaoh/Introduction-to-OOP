// ID 316044809
package game.animation;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {
    /**
     * The constant FRAMES_PER_SECONDS.
     */
    int FRAMES_PER_SECONDS = 60;

    /**
     * Should stop boolean.
     *
     * @return if the animation should stop
     */
    boolean shouldStop();

    /**
     * Do one frame.
     *
     * @param canvas the canvas
     */
    void doOneFrame(DrawSurface canvas);

    /**
     * Get frame per seconds.
     *
     * @return the frame per seconds
     */
    default double getFramePerSeconds() {
        return FRAMES_PER_SECONDS;
    }
}
