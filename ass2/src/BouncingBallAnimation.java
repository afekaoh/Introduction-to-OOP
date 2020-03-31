// ID 316044809

import biuoop.DrawSurface;


/**
 * The class Bouncing ball animation.
 */
public class BouncingBallAnimation extends Animation {

    /**
     * Instantiates a new Bouncing ball animation.
     *
     * @param width the width
     * @param height the height
     * @param title the title
     */
    public BouncingBallAnimation(final int width, final int height, final String title) {
        super(width, height, title);
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
        if (args.length != 4) {
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
            throw new RuntimeException("Error! enter numbers only!");
        }

        // creating the new Ball
        final Point center = new Point(x, y);
        final Ball ball = new Ball(center, 30, BLACK);
        final Velocity v = new Velocity(xSpeed, ySpeed);
        ball.setVelocity(v);

        // setting the frame of movement bounds
        final Line frame = new Line(0, 0, getWidth(), getHeight());

        // draw loop
        while (true) {
            final DrawSurface canvas = getGui().getDrawSurface();
            drawBall(frame, canvas, ball);
            getGui().show(canvas);
            getSleeper().sleepFor(SLEEPING_TIME);
        }
    }
}
