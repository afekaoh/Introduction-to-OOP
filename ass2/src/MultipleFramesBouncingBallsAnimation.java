// ID 316044809


/**
 * The class Multiple bouncing balls animation.
 * creates an animation of multiple bouncing balls in 2 separate frames according to user specific radii.
 */
public class MultipleFramesBouncingBallsAnimation extends Animation {


    /**
     * The first Boundary of movement.
     */
    private final Boundary[] framesOfMovement;

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
        this.framesOfMovement = new Boundary[] {
                new Boundary(50, 50, 500, 500, java.awt.Color.GRAY),
                new Boundary(450, 450, 600, 600, java.awt.Color.YELLOW)};
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
     * creates 2 frames and random balls and draw the balls on those frames.
     *
     * @param args the radii of the balls to draw
     */
    public void drawAnimation(String[] args) {
        // validity check
        assert args != null && args.length >= 1 : "Error! enter radii for the balls";

        //creating the balls
        final Ball[] balls = createBallsFromCMD(args, new Boundary(0, 0, getWidth(), getHeight()));

        // draw loop
        while (true) {
            setNewCanvas();
            // drawing the frames
            for (Boundary b : framesOfMovement) {
                drawFrame(b);
            }
            // drawing the balls
            for (int i = 0; i < balls.length; i++) {
                drawBall(framesOfMovement[i % 2], balls[i]);
            }
            show();
        }
    }
}
