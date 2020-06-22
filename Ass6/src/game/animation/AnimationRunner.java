// ID 316044809
package game.animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * The class Animation runner.
 */
public class AnimationRunner {
    /**
     * The Gui for the game.
     */
    private final GUI gui;
    /**
     * The Sleeper of the game.
     */
    private final Sleeper sleeper;
    /**
     * The Keyboard sensor.
     */
    private final KeyboardSensor keyboardSensor;
    /**
     * The Milliseconds per frame.
     */
    private int millisecondsPerFrame;
    /**
     * The Draw Surface of the game.
     */
    private DrawSurface canvas;

    /**
     * Instantiates a new Animation runner.
     *
     * @param width  the width
     * @param height the height
     * @param title  the title
     */
    public AnimationRunner(final int width, final int height, final String title) {
        this.gui = new GUI(title, width, height);
        this.sleeper = new Sleeper();
        keyboardSensor = gui.getKeyboardSensor();
    }

    /**
     * Sets a new DrawSurface.
     */
    public void setNewCanvas() {
        this.canvas = gui.getDrawSurface();
    }

    /**
     * play the game.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {
        this.millisecondsPerFrame = (int) (1000 / animation.getFramePerSeconds());
        while (true) {
            long startTime = System.currentTimeMillis();
            // getting ready for the next frame of the game
            setNewCanvas();
            animation.doOneFrame(canvas);
            if (animation.shouldStop()) {
                return;
            }
            drawFrame(startTime);
        }
    }

    /**
     * draws the canvas to the gui and suspends the animation.
     *
     * @param startTime the time the frame has begun
     */
    public void drawFrame(long startTime) {
        gui.show(canvas);
        long usedTime = System.currentTimeMillis() - startTime;
        long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
        if (milliSecondLeftToSleep > 0) {
            sleeper.sleepFor(milliSecondLeftToSleep);
        }
    }

    /**
     * Get keyboard sensor.
     *
     * @return the keyboard sensor
     */
    public KeyboardSensor getKeyboardSensor() {
        return keyboardSensor;
    }

    /**
     * Close the animation.
     */
    public void close() {
        gui.close();
    }
}
