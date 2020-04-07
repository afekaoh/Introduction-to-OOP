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
    // constants
    /**
     * The constant SLEEPING_TIME.
     */
    public static final int SLEEPING_TIME = 40;
    /**
     * A default radius for a point.
     */
    public static final int POINT_RADIUS = 3;
    /**
     * the color black which used a lot.
     */
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
     * @param width the width of the animation
     * @param height the height of the animation
     * @param title the title of the animation
     */
    public Animation(final int width, final int height, final String title) {
        this.width = width;
        this.height = height;
        this.gui = new GUI(title, this.width, this.height);
        this.sleeper = new Sleeper();
        setNewCanvas();
    }

    /**
     * Sets a new DrawSurface.
     */
    public void setNewCanvas() {
        this.canvas = gui.getDrawSurface();
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets height.
     *
     * @return the height
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
        // draw loop
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
     * @param frame a line representing the boundaries of the frame
     * @param ball the ball to draw
     */
    public void drawBall(final Frame frame, final Ball ball) {
        ball.moveOneStep(frame);
        drawPoint(ball.getCenter(), ball.getColor(), ball.getRadius());
    }

    /**
     * Draw point.
     *
     * @param point the point to draw
     * @param color the color of the point.
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
     * @param line the line to draw
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
     * @param radii the balls radii
     * @return an array of Balls
     */
    public Ball[] createBallsFromCMD(final String[] radii) {
        final int[] ballsRadii = getRadii(radii);
        Ball[] balls = new Ball[ballsRadii.length];
        for (int i = 0; i < balls.length; i++) {
            balls[i] = new Ball(ballsRadii[i], width, height);
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
            for (int i = 0; i < radii.length; i++) {
                ballsRadii[i] = Integer.parseInt(radii[i]);
            }
        } catch (Exception NumberFormatException) {
            throw new RuntimeException("Error! enter radii in integers numbers only");
        }
        return ballsRadii;
    }

    /**
     * Draws the frame.
     *
     * @param frame the frame
     */
    public void drawFrame(Frame frame) {
        // drawing the fill
        canvas.setColor(frame.getColor());

        final int leftX = (int) frame.top().getX();
        final int topY = (int) frame.top().getY();
        final int frameWidth = frame.getFrameWidth();
        final int frameHeight = frame.getFrameHeight();

        //drawing the frame
        canvas.fillRectangle(leftX, topY, frameWidth, frameHeight);

        // drawing the stroke
        canvas.setColor(Animation.BLACK);
        canvas.drawRectangle(leftX, topY, frameWidth, frameHeight);
    }

    /**
     * Create random lines on the screen.
     *
     * @param numOfLines the num of lines
     * @return an array of lines
     */
    public Line[] createRandomLines(final int numOfLines) {
        final Line[] lines = new Line[numOfLines];
        for (int i = 0; i < 10; i++) {
            lines[i] = new Line(width, height);
        }
        return lines;
    }
}
