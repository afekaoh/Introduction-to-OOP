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

    /**
     * The constant SLEEPING_TIME.
     */
    public static final int SLEEPING_TIME = 40;
    /**
     * The radius of a point to draw.
     */
    public static final int POINT_RADIUS = 3;
    /**
     * The color of a line.
     */
    public static final Color LINE_COLOR = Color.BLACK;

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
        gui = new GUI(title, this.width, this.height);
        sleeper = new Sleeper();
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
     * Gets gui.
     *
     * @return the gui
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Gets sleeper.
     *
     * @return the sleeper
     */
    public Sleeper getSleeper() {
        return sleeper;
    }

    /**
     * Draw animation.
     *
     * @param args the arguments for the animation
     */
    public void drawAnimation(final String[] args) {
        // setting the frame of movement bounds
        final Line frame = new Line(0, 0, width, height);

        // draw loop
        while (true) {
            final DrawSurface canvas = getGui().getDrawSurface();
            getGui().show(canvas);
            getSleeper().sleepFor(SLEEPING_TIME);
        }
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
     * Draw ball to the given canvas.
     *
     * @param frame a line representing the boundaries of the frame
     * @param canvas the DrawSurface to draw to
     * @param ball the ball to draw
     */
    public void drawBall(final Line frame, final DrawSurface canvas, final Ball ball) {
        ball.moveOneStep(frame);
        ball.drawOn(canvas);
    }

    /**
     * Draws the frame.
     *
     * @param canvas the canvas to draw onto
     * @param frame a line representing the edges of the frame to draw
     * @param color the color of the frame
     */
    public void drawFrame(final DrawSurface canvas, final Line frame, final Color color) {
        // drawing the fill
        canvas.setColor(color);
        final int x = (int) frame.start().getX();
        final int y = (int) frame.start().getY();
        final int frameWidth = (int) (frame.end().getX() - x);
        final int frameHeight = (int) (frame.end().getY() - y);
        canvas.fillRectangle(x, y, frameWidth, frameHeight);

        // drawing the stroke
        canvas.setColor(Color.black);
        canvas.drawRectangle(x, y, frameWidth, frameHeight);
    }


    /**
     * Draw point.
     *
     * @param canvas the DrawSurface to draw the point to
     * @param point the point to draw
     * @param color the color of the point.
     */
    public void drawPoint(final DrawSurface canvas, final Point point, final Color color) {
        final int x = (int) point.getX();
        final int y = (int) point.getY();
        canvas.setColor(color);
        canvas.fillCircle(x, y, POINT_RADIUS);
        canvas.setColor(Color.black);
        canvas.drawCircle(x, y, POINT_RADIUS);
    }

    /**
     * Draw line.
     *
     * @param canvas the DrawSurface to draw the line to
     * @param line the line to draw
     */
    public void drawLine(final DrawSurface canvas, final Line line) {
        final int x1 = (int) line.start().getX();
        final int y1 = (int) line.start().getY();
        final int x2 = (int) line.end().getX();
        final int y2 = (int) line.end().getY();
        canvas.setColor(LINE_COLOR);
        canvas.drawLine(x1, y1, x2, y2);
    }

    /**
     * Create balls.
     * gets an string array of radii and creates an array of balls with the corresponding radii
     *
     * @param radii the balls radii
     * @return an array of Balls
     */
    public Ball[] createBalls(final String[] radii) {
        final int[] ballsRadii = getRadii(radii);
        Ball[] balls = new Ball[ballsRadii.length];
        for (int i = 0; i < balls.length; i++) {
            balls[i] = new Ball(ballsRadii[i], width, height);
        }
        return balls;
    }
}
