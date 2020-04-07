// ID 316044809

import java.awt.Color;


/**
 * The class Multiple bouncing balls animation.
 */
public class MultipleFramesBouncingBallsAnimation extends Animation {


    /**
     * The first Frame of movement.
     */
    private final Frame frameOfMovement0;

    /**
     * The second Frame of movement.
     */
    private final Frame frameOfMovement1;

    /**
     * Instantiates a new Multiple frames bouncing balls animation.
     *
     * @param width the width
     * @param height the height
     * @param title the title
     */
    public MultipleFramesBouncingBallsAnimation(final int width, final int height, final String title) {
        super(width, height, title);
        // setting up the frames of movements
        this.frameOfMovement0 = new Frame(50, 50, 500, 500, Color.GRAY);
        this.frameOfMovement1 = new Frame(450, 450, 600, 600, Color.YELLOW);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(final String[] args) {
        // creating the animation
        final var animation = new MultipleFramesBouncingBallsAnimation(800, 600, "Multi Frames Balls");
        animation.drawAnimation(args);
    }


    /**
     * creates 2 frames and random balls and draw the balls on those frames.
     *
     * @param args the radii of the balls to draw
     */
    public void drawAnimation(String[] args) {
        // validity check
        if (args.length < 1) {
            throw new RuntimeException("Error! enter radii for the balls");
        }

        //creating the balls
        final Ball[] balls = createBallsFromCMD(args);

        // draw loop
        while (true) {
            setNewCanvas();
            // drawing the frames
            drawFrame(frameOfMovement0);
            drawFrame(frameOfMovement1);
            // drawing the balls
            for (int i = 0; i < balls.length; i++) {
                drawBall(i % 2 == 0 ? frameOfMovement0 : frameOfMovement1, balls[i]);
            }
            show();
        }
    }
}
