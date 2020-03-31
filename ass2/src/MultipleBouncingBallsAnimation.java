// ID 316044809

import biuoop.DrawSurface;


/**
 * The class Multiple bouncing balls animation.
 */
public class MultipleBouncingBallsAnimation extends Animation {

    /**
     * Instantiates a new Multiple bouncing balls animation.
     *
     * @param width the width
     * @param height the height
     * @param title the title
     */
    public MultipleBouncingBallsAnimation(final int width, final int height, final String title) {
        super(width, height, title);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        // creating a new animation
        final MultipleBouncingBallsAnimation animation;
        animation = new MultipleBouncingBallsAnimation(800, 600, "Multiple Bouncing Balls");

        animation.drawAnimation(args);
    }


    /**
     * Draws the animation.
     *
     * @param args the args
     */
    public void drawAnimation(final String[] args) {
        // validity check
        if (args.length < 1) {
            throw new RuntimeException("Error! enter radii for the balls");
        }

        //creating the balls
        final Ball[] balls = createBalls(args);
        // setting the frame of movement bounds
        final Line frame = new Line(0, 0, getWidth(), getHeight());

        // draw loop
        while (true) {
            final DrawSurface canvas = getGui().getDrawSurface();
            for (final Ball ball : balls) {
                drawBall(frame, canvas, ball);
            }

            getGui().show(canvas);
            getSleeper().sleepFor(SLEEPING_TIME);
        }
    }
}
