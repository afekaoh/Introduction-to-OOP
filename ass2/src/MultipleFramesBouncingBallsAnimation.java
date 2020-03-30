// ID 316044809

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;


/**
 * The class Multiple bouncing balls animation.
 */
public class MultipleFramesBouncingBallsAnimation {


    /**
     * The Width.
     */
    private final int width;
    /**
     * The Height.
     */
    private final int height;
    /**
     * The Sleeper.
     */
    private final Sleeper sleeper;
    /**
     * The Gui.
     */
    private final GUI gui;

    /**
     * Instantiates a new Multiple frames bouncing balls animation.
     *
     * @param width the width
     * @param height the height
     */
    public MultipleFramesBouncingBallsAnimation(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.sleeper = new Sleeper();
        this.gui = new GUI("Multi Frame Balls", width, height);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(final String[] args) {
        // validity check
        if (args.length < 1) {
            throw new RuntimeException("Error! enter radii for the balls");
        }
        // parsing the user data
        final int[] ballsRadii = new int[args.length];
        try {
            for (int i = 0; i < args.length; i++) {
                ballsRadii[i] = Integer.parseInt(args[i]);
            }
        } catch (Exception NumberFormatException) {
            throw new RuntimeException("Error! enter radii in integers numbers only");
        }
        // creating the animation
        MultipleFramesBouncingBallsAnimation animation = new MultipleFramesBouncingBallsAnimation(800, 600);

        //creating the balls
        Ball[] balls = new Ball[ballsRadii.length];
        for (int i = 0; i < balls.length; i++) {
            balls[i] = new Ball(ballsRadii[i], animation.width, animation.height);
        }
        animation.drawAnimation(balls);
    }

    /**
     * Draw animation.
     *
     * @param balls the balls to draw
     */
    private void drawAnimation(final Ball[] balls) {
        // setting up the frame of movements
        final Line frame1 = new Line(50, 50, 500, 500);
        final Line frame2 = new Line(450, 450, 600, 600);

        // draw loop
        while (true) {
            final DrawSurface canvas = gui.getDrawSurface();
            // drawing the frames
            drawFrame(canvas, frame1, Color.GRAY);
            drawFrame(canvas, frame2, Color.YELLOW);

            // drawing the balls
            for (int i = 0; i < balls.length / 2; i++) {
                drawBall(frame1, canvas, balls[i]);
            }
            for (int i = balls.length / 2; i < balls.length; i++) {
                drawBall(frame2, canvas, balls[i]);
            }
            gui.show(canvas);
            sleeper.sleepFor(BouncingBallAnimation.SLEEPING_TIME);
        }
    }

    /**
     * Draws the frame.
     *
     * @param canvas the canvas to draw onto
     * @param frame a line representing the edges of the frame to draw
     * @param color the color of the frame
     */
    private void drawFrame(final DrawSurface canvas, final Line frame, final Color color) {
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
     * Draw ball to the given canvas.
     *
     * @param frame a line representing the boundaries of the frame
     * @param canvas the DrawSurface to draw to
     * @param ball the ball to draw
     */
    private void drawBall(final Line frame, final DrawSurface canvas, final Ball ball) {
        ball.moveOneStep(frame);
        ball.drawOn(canvas);
    }
}
