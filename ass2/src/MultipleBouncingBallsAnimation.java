// ID 316044809


/**
 * The class Multiple bouncing balls animation.
 * creates an animation of multiple bouncing balls according to user specific radii.
 */
public class MultipleBouncingBallsAnimation extends Animation {
    private final Boundary frameOfMovement;

    /**
     * Instantiates a new Multiple bouncing balls animation.
     *
     * @param width  the width
     * @param height the height
     * @param title  the title
     */
    public MultipleBouncingBallsAnimation(final int width, final int height, final String title) {
        super(width, height, title);
        // setting the frame of movement bounds
        this.frameOfMovement = new Boundary(0, 0, width, height);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        // creating a new animation
        final MultipleBouncingBallsAnimation animation;
        animation = new MultipleBouncingBallsAnimation(400, 300, "Multiple Bouncing Balls");
        animation.drawAnimation(args);
    }


    /**
     * create random balls and draws them to the screen.
     *
     * @param args the radii of the balls
     */
    public void drawAnimation(final String[] args) {
        // validity check
        if (args == null || args.length < 1) {
            throw new RuntimeException("Error! enter radii for the balls");
        }

        //creating the balls
        final Ball[] balls = createBallsFromCMD(args, frameOfMovement);

        // draw loop
        while (true) {
            setNewCanvas();
            for (final Ball ball : balls) {
                drawBall(frameOfMovement, ball);
            }
            show();
        }
    }
}
