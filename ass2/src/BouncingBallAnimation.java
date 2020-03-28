// ID 316044809

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;


/**
 * The class Bouncing ball animation.
 */
public class BouncingBallAnimation {

    /**
     * The constant SLEEPING_TIME.
     */
    public static final int SLEEPING_TIME = 40;
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
     * @param width the width
     * @param height the height
     */
    public BouncingBallAnimation(final int width, final int height) {
        this.width = width;
        this.height = height;
        gui = new GUI("Bounce Ball", this.width, this.height);
        sleeper = new Sleeper();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(final String[] args) {
        if (args.length != 4) {
            System.out.println("error! not enough arguments");
            return;
        }
        // creating a new animation
        final BouncingBallAnimation animation = new BouncingBallAnimation(800, 600);

        // parsing the user data
        final double x = Double.parseDouble(args[0]);
        final double y = Double.parseDouble(args[1]);
        final double angle = Double.parseDouble(args[2]);
        final double speed = Double.parseDouble(args[3]);

        // creating the new Ball
        final Point center = new Point(x, y);
        final Ball ball = new Ball(center, 30, angle, speed, java.awt.Color.BLACK);
        animation.drawAnimation(ball);
    }

    /**
     * Draw animation.
     *
     * @param start the start
     * @param dx the dx
     * @param dy the dy
     */
    private static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", 200, 200);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     * Draw animation.
     * the function gets a ball and draws it to the screen.
     *
     * @param ball the ball to draw
     */
    public void drawAnimation(final Ball ball) {
        // setting the frame of movement bounds
        final Line frame = new Line(0, 0, width, height);

        // draw loop
        while (true) {
            final DrawSurface canvas = gui.getDrawSurface();
            ball.moveOneStep(frame);
            ball.drawOn(canvas);
            gui.show(canvas);
            sleeper.sleepFor(SLEEPING_TIME);
        }
    }
}
