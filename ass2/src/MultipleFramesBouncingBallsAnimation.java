// ID 316044809

import biuoop.DrawSurface;

import java.awt.Color;


/**
 * The class Multiple bouncing balls animation.
 */
public class MultipleFramesBouncingBallsAnimation extends Animation {


    /**
     * Instantiates a new Multiple frames bouncing balls animation.
     *
     * @param width the width
     * @param height the height
     * @param title the title
     */
    public MultipleFramesBouncingBallsAnimation(final int width, final int height, final String title) {
        super(width, height, title);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(final String[] args) {
        // creating the animation
        final MultipleFramesBouncingBallsAnimation animation;
        animation = new MultipleFramesBouncingBallsAnimation(800, 600, "Multi Frames Balls");
        animation.drawAnimation(args);
    }


    /**
     * Draw animation.
     *
     * @param args the args
     */
    public void drawAnimation(String[] args) {
        // validity check
        if (args.length < 1) {
            throw new RuntimeException("Error! enter radii for the balls");
        }

        //creating the balls
        final Ball[] balls = createBalls(args);
        // setting up the frame of movements
        final Line frame1 = new Line(50, 50, 500, 500);
        final Line frame2 = new Line(450, 450, 600, 600);

        // draw loop
        while (true) {
            final DrawSurface canvas = getGui().getDrawSurface();
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
            getGui().show(canvas);
            getSleeper().sleepFor(SLEEPING_TIME);
        }
    }
}
