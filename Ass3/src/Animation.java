// ID 316044809

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The class Animation.
 * Implementing all the animation related methods to be used in the various animations classes.
 */
public class Animation {
    // constants for the animation classes
    public static final int SLEEPING_TIME = 40;
    public static final int POINT_RADIUS = 3;
    public static final Color BLACK = Color.BLACK;

    /**
     * The Width of the animation.
     */
    private final int width;
    /**
     * The Height of the animation.
     */
    private final int height;
    /**
     * The Gui for the animation.
     */
    private final GUI gui;
    /**
     * The Sleeper of the animation.
     */
    private final Sleeper sleeper;

    private DrawSurface canvas;

    /**
     * Instantiates a new Animation.
     *
     * @param width  the width of the animation
     * @param height the height of the animation
     * @param title  the title of the animation
     */
    public Animation(final int width, final int height, final String title) {
        this.width = width;
        this.height = height;
        this.gui = new GUI(title, this.width, this.height);
        this.sleeper = new Sleeper();
        setNewCanvas();
    }

    /**
     * Map values from one range to another.
     *
     * @param value  the incoming value to be converted
     * @param start1 lower bound of the value's current range
     * @param stop1  upper bound of the value's current range
     * @param start2 lower bound of the value's target range
     * @param stop2  upper bound of the value's target range
     * @return value mapped to the new range
     */
    public static double map(final double value, final double start1, final double stop1, final double start2,
                             final double stop2) {
        return (value - start1) / (stop1 - start1) * (stop2 - start2) + start2;
    }

    /**
     * Sets a new DrawSurface.
     */
    public void setNewCanvas() {
        this.canvas = gui.getDrawSurface();
    }

    /**
     * Get width.
     *
     * @return the width of animation.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get height.
     *
     * @return the height of the animation.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Draw animation.
     *
     * @param args the arguments for the animation
     */
    public void drawAnimation(final String[] args) {
        // showing the GUI
        setNewCanvas();
        show();
    }

    /**
     * draws the canvas to the gui and suspends the animation.
     */
    public void show() {
        gui.show(canvas);
        sleeper.sleepFor(SLEEPING_TIME);
    }

    /**
     * Draw ball to the given canvas.
     *
     * @param ball the ball to draw
     */
    public void drawBall(final Ball ball) {
        ball.moveOneStep();
        ball.drawOn(canvas);
    }

    public void drawSprite(final Sprite sprite) {
        sprite.timePassed();
        sprite.drawOn(canvas);
    }


    /**
     * Draw point.
     *
     * @param point  the point to draw
     * @param color  the color of the point.
     * @param radius the radius of the point
     */
    public void drawPoint(final Point point, final Color color, final int radius) {
        final int x = (int) point.getX();
        final int y = (int) point.getY();
        canvas.setColor(color);
        canvas.fillCircle(x, y, radius);
        canvas.setColor(BLACK);
        canvas.drawCircle(x, y, radius);
    }

    /**
     * Draw line.
     *
     * @param line  the line to draw
     * @param color the color of the line
     */
    public void drawLine(final Line line, final Color color) {
        final int x1 = (int) line.start().getX();
        final int y1 = (int) line.start().getY();
        final int x2 = (int) line.end().getX();
        final int y2 = (int) line.end().getY();
        canvas.setColor(color);
        canvas.drawLine(x1, y1, x2, y2);
    }

    /**
     * todo
     * Draw game.
     *
     * @param list the list
     */
    public void drawGame(ArrayList<Sprite> list) {
        while (true) {
            setNewCanvas();
            for (Sprite sprite : list) {
                sprite.timePassed();
                sprite.drawOn(canvas);
            }
            show();
        }
    }
}
