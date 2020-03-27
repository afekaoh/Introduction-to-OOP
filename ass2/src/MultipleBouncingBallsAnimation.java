// ID 316044809

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;


/**
 * The class Multiple bouncing balls animation.
 */
public class MultipleBouncingBallsAnimation {


    /**
     * The Sleeper.
     */
    private final Sleeper sleeper;
    /**
     * The Width.
     */
    private final int width;
    /**
     * The Height.
     */
    private final int height;
    /**
     * The Gui.
     */
    private GUI gui;

    /**
     * Instantiates a new Multiple bouncing balls animation.
     *
     * @param width the width
     * @param height the height
     */
    public MultipleBouncingBallsAnimation(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.gui = new GUI("Multi Bounce Balls", width, height);
        this.sleeper = new Sleeper();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // validity check
        if (args.length < 1) {
            return;
        }
        // creating a new animation
        MultipleBouncingBallsAnimation animation = new MultipleBouncingBallsAnimation(800, 600);

        // parsing the user data
        int[] ballsR = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            ballsR[i] = Integer.parseInt(args[i]);
        }
        animation.drawAnimation(ballsR);
    }


    /**
     * Draws the animation.
     *
     * @param radii the radii of the balls to draw
     */
    private void drawAnimation(final int[] radii) {
        final Line frame = new Line(0, 0, width, height);
        final Ball[] balls = new Ball[radii.length];
        for (int i = 0; i < balls.length; i++) {
            balls[i] = new Ball(radii[i], width, height);
        }
        while (true) {
            final DrawSurface canvas = gui.getDrawSurface();
            for (final Ball ball : balls) {
                ball.move(frame);
                ball.drawOn(canvas);
            }
            gui.show(canvas);
            sleeper.sleepFor(40);
        }
    }
}
