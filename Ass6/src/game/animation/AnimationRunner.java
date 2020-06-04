// ID 316044809
package game.animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The class Animation runner.
 */
public class AnimationRunner {
    private final int millisecondsPerFrame;
    private final Color screenColor;
    /**
     * The Width of the game.
     */
    private final int width;
    /**
     * The Height of the game.
     */
    private final int height;
    /**
     * The Gui for the game.
     */
    private final GUI gui;
    /**
     * The Sleeper of the game.
     */
    private final Sleeper sleeper;
    private final KeyboardSensor keyboardSensor;
    /**
     * The Draw Surface of the game.
     */
    private DrawSurface canvas;

    public AnimationRunner(final int width, final int height, final String title, final int framesPerSecond,
                           final Color screenColor) {
        this.width = width;
        this.height = height;
        this.gui = new GUI(title, this.width, this.height);
        this.screenColor = screenColor;
        this.sleeper = new Sleeper();
        millisecondsPerFrame = 1000 / framesPerSecond;
        keyboardSensor = gui.getKeyboardSensor();
    }

    /**
     * Sets a new DrawSurface.
     */
    public void setNewCanvas() {
        this.canvas = gui.getDrawSurface();
        canvas.setColor(screenColor);
        canvas.fillRectangle(0, 0, width, height);
    }

    /**
     * run the game.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {
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

    public KeyboardSensor getKeyboardSensor() {
        return keyboardSensor;
    }

    public void close() {
        gui.close();
    }

    public Sleeper getSleeper() {
        return this.sleeper;
    }
}
