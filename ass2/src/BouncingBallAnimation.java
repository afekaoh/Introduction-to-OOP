// ID 316044809


/**
 * The class Bouncing ball animation.
 * creates an animation of one bouncing ball according to user specific arguments.
 */
public class BouncingBallAnimation extends Animation {


    /**
     * The Boundary of movement.
     */
    private final Boundary frameOfMovement;

    /**
     * Instantiates a new Bouncing ball animation.
     *
     * @param width  the width
     * @param height the height
     * @param title  the title
     */
    public BouncingBallAnimation(final int width, final int height, final String title) {
        super(width, height, title);
        // setting the frame of movement bounds
        this.frameOfMovement = new Boundary(0, 0, width, height);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(final String[] args) {

        // creating a new animation
        final BouncingBallAnimation animation = new BouncingBallAnimation(400, 300, "Bouncing Ball");

        animation.drawAnimation(args);
    }

    /**
     * Draw animation.
     * the function creates a ball and draws it to the screen.
     *
     * @param args the arguments to create the Ball
     */
    public void drawAnimation(final String[] args) {
        // validity check
        if (args == null || args.length != 4) {
            throw new RuntimeException("Error! enter exactly 4 arguments!");
        }

        // parsing the user data
        final double ySpeed, x, y, xSpeed;
        try {
            x = Double.parseDouble(args[0]);
            y = Double.parseDouble(args[1]);
            xSpeed = Double.parseDouble(args[2]);
            ySpeed = Double.parseDouble(args[3]);
        } catch (Exception NumberFormatException) {
            // validity check
            throw new RuntimeException("Error! enter numbers only!");
        }

        // creating the new Ball
        final Ball ball = new Ball(new Point(x, y), 30, BLACK, new Velocity(xSpeed, ySpeed));

        // draw loop
        while (true) {
            setNewCanvas();
            drawBall(frameOfMovement, ball);
            show();
        }
    }
}
