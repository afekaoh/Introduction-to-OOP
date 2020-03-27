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
     * The Guis.
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
        if (args.length < 1) {
            return;
        }

        MultipleFramesBouncingBallsAnimation animation = new MultipleFramesBouncingBallsAnimation(800, 600);
        int[] ballsR = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            ballsR[i] = Integer.parseInt(args[i]);
        }
        animation.drawAnimation(ballsR);
    }

    /**
     * Draw ball to the given canvas.
     *
     * @param frame a line representing the boundaries of the frame
     * @param canvas the DrawSurface to draw to
     * @param ball the ball to draw
     */
    private void drawBall(final Line frame, final DrawSurface canvas, final Ball ball) {
        ball.move(frame);
        ball.drawOn(canvas);
    }

    /**
     * Draw animation.
     *
     * @param radii the radii of all the balls to draw
     */
    private void drawAnimation(final int[] radii) {
        final Line frame1 = new Line(50, 50, 500, 500);
        final Line frame2 = new Line(450, 450, 600, 600);

        Ball[] balls1 = new Ball[radii.length / 2];
        Ball[] balls2 = new Ball[radii.length / 2 + radii.length % 2];
        int balls1Index = 0;
        int balls2Index = 0;
        for (int i = 0; i < radii.length; i++) {
            if (i % 2 == 1) {
                balls1[balls1Index] = new Ball(radii[i], frame1.start(), frame1.end());
                balls1Index++;
            } else {
                balls2[balls2Index] = new Ball(radii[i], frame2.start(), frame2.end());
                balls2Index++;
            }
        }
        while (true) {
            final DrawSurface canvas = gui.getDrawSurface();
            drawRectangle(canvas, frame1, Color.GRAY);
            drawRectangle(canvas, frame2, Color.YELLOW);
            for (final Ball ball : balls1) {
                drawBall(frame1, canvas, ball);
            }
            for (final Ball ball : balls2) {
                drawBall(frame2, canvas, ball);
            }
            gui.show(canvas);
            sleeper.sleepFor(40);  // wait for 50 milliseconds.
        }
    }

    /**
     * Draw rectangle with.
     *
     * @param canvas the canvas
     * @param frame the frame
     * @param color the color
     */
    private void drawRectangle(final DrawSurface canvas, final Line frame, final Color color) {
        canvas.setColor(color);
        final int x = (int) frame.start().getX();
        final int y = (int) frame.start().getY();
        final int frameWidth = (int) (frame.end().getX() - x);
        final int frameHeight = (int) (frame.end().getY() - y);
        canvas.fillRectangle(x, y, frameWidth, frameHeight);
    }
}
