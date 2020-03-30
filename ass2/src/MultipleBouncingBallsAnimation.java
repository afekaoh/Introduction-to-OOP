// ID 316044809

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;


/**
 * The class Multiple bouncing balls animation.
 */
public class MultipleBouncingBallsAnimation {

    /**
     * The Width of the animation.
     */
    private final int width;
    /**
     * The Height of the animation.
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

        // creating a new animation
        MultipleBouncingBallsAnimation animation = new MultipleBouncingBallsAnimation(800, 600);

        //creating the balls
        final Ball[] balls = new Ball[ballsRadii.length];
        for (int i = 0; i < balls.length; i++) {
            balls[i] = new Ball(ballsRadii[i], animation.width, animation.height);
        }
        animation.drawAnimation(balls);
    }


    /**
     * Draws the animation.
     *
     * @param balls the radii of the balls to draw
     */
    private void drawAnimation(final Ball[] balls) {
        // setting the frame of movement bounds
        final Line frame = new Line(0, 0, width, height);

        // draw loop
        while (true) {
            final DrawSurface canvas = gui.getDrawSurface();
            for (final Ball ball : balls) {
                drawBall(frame, canvas, ball);
//                System.out.println("Ball: " + ball.getRadius() + " " + ball.getVelocity());
            }
            gui.show(canvas);
            sleeper.sleepFor(BouncingBallAnimation.SLEEPING_TIME);
        }
    }

    /**
     * Draw ball.
     *
     * @param frame a line representing a frame of movement
     * @param canvas the DrawSurface to draw to
     * @param ball the ball to draw
     */
    private void drawBall(final Line frame, final DrawSurface canvas, final Ball ball) {
        ball.moveOneStep(frame);
        ball.drawOn(canvas);
    }
}
