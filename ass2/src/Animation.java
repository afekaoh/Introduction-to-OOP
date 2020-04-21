// ID 316044809

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

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
     * Instantiates a new Bouncing ball animation.
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
     * @param boundary a line representing the boundaries of the boundary
     * @param ball     the ball to draw
     */
    public void drawBall(final Boundary boundary, final Ball ball) {
        ball.moveOneStep(boundary);
        drawPoint(ball.getCenter(), ball.getColor(), ball.getSize());
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
     * Create balls.
     * gets an string array of radii and creates an array of balls with the corresponding radii
     *
     * @param radii    the balls radii
     * @param boundary the boundary
     * @return an array of Balls
     */
    public Ball[] createBallsFromCMD(final String[] radii, final Boundary boundary) {
        //parsing the radii from the array.
        final int[] ballsRadii = getRadii(radii);

        // creating the balls.
        Ball[] balls = new Ball[ballsRadii.length];
        for (int i = 0; i < balls.length; i++) {
            balls[i] = new Ball(ballsRadii[i], boundary);
        }

        return balls;
    }

    /**
     * Get radii.
     * converting an array of radii in string format to an int
     *
     * @param radii the radii
     * @return the array of balls radii
     */
    private int[] getRadii(final String[] radii) {
        final int[] ballsRadii = new int[radii.length];
        try {
            // parsing the data.
            for (int i = 0; i < radii.length; i++) {
                ballsRadii[i] = Integer.parseInt(radii[i]);
            }
        } catch (Exception NumberFormatException) {
            // validity check.
            throw new RuntimeException("Error! enter radii in integers numbers only");
        }
        return ballsRadii;
    }

    /**
     * Draws a frame representing by a boundary.
     *
     * @param boundary the boundary to draw
     */
    public void drawFrame(Boundary boundary) {
        // drawing the fill
        canvas.setColor(boundary.getColor());

        final int leftX = (int) boundary.topPoint().getX();
        final int topY = (int) boundary.topPoint().getY();
        final int frameWidth = boundary.getBoundaryWidth();
        final int frameHeight = boundary.getBoundaryHeight();

        //drawing the boundary
        canvas.fillRectangle(leftX, topY, frameWidth, frameHeight);

        // drawing the stroke
        canvas.setColor(Animation.BLACK);
        canvas.drawRectangle(leftX, topY, frameWidth, frameHeight);
    }

    /**
     * Create random lines on the screen.
     *
     * @param numOfLines the num of lines
     * @return an array of random lines
     */
    public Line[] createRandomLines(final int numOfLines) {
        final Line[] lines = new Line[numOfLines];
        for (int i = 0; i < 10; i++) {
            lines[i] = new Line(width, height);
        }
        return lines;
    }
}
