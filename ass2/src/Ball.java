// ID 316044809

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * The class Ball representing a 2D ball.
 */
public class Ball {

    /**
     * The Center point.
     */
    private Point center;
    /**
     * The Radius of the ball.
     */
    @SuppressWarnings("FieldMayBeFinal")
    private int radius;
    /**
     * The Color of the ball.
     */
    private Color color;

    /**
     * The Velocity of the ball.
     * defaults to (0,0) if not set otherwise
     */
    private Velocity velocity = new Velocity(0, 0);


    /**
     * Instantiates a new Ball with center radius and color.
     *
     * @param center the center of the call
     * @param radius the radius of the ball
     * @param color the color of the ball
     * @param velocity the velocity of the ball
     */
    public Ball(final Point center, final int radius, final Color color, Velocity velocity) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.velocity = velocity;
    }

    /**
     * Instantiates a new Ball with the XY coordinates.
     *
     * @param x the x coordinate of the ball
     * @param y the y coordinate of the ball
     * @param radius the radius of the ball
     * @param color the color of the ball
     */
    public Ball(final double x, final double y, final int radius, final Color color) {
        this.center = new Point(x, y);
        this.radius = radius;
        this.color = color;
    }

    /**
     * Instantiates a new Ball with velocity.
     *
     * @param center the center
     * @param radius the radius
     * @param angle the angle
     * @param speed the speed
     * @param color the color
     */
    public Ball(final Point center, final int radius, final double angle, final double speed, final Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.velocity = Velocity.fromAngleAndSpeed(angle, speed);
    }

    /**
     * Instantiates a new Ball in random spot between start to end with a random speed.
     *
     * @param radius the radius
     * @param boundary the boundary to draw the balls into
     */
    public Ball(final int radius, final Boundary boundary) {
        Random rand = new Random();
        Point start = new Point(boundary.left() + radius, boundary.top() + radius);
        Point end = new Point(boundary.right() - radius, boundary.bottom() - radius);
        this.center = Point.getRandomPoint(start, end);
        this.radius = radius;
        // generating a new random speed
        final double angle = (rand.nextInt(4) + 1) * (rand.nextDouble() * (70 - 12.5) + 12.5);
        final double speed = Velocity.map(radius * radius, 1, 2500, 15, 7);
        this.velocity = Velocity.fromAngleAndSpeed(angle, speed);
        setRandomColor();
    }

    /**
     * give the Ball a new random color.
     */
    private void setRandomColor() {
        Random rand = new Random();
        // changing the color every bounce
        this.color = Color.getHSBColor(rand.nextFloat(), 0.9f, 1f);
    }

    /**
     * Sets velocity.
     *
     * @param xSpeed the x speed
     * @param ySpeed the y speed
     */
    public void setVelocity(final double xSpeed, final double ySpeed) {
        this.velocity = new Velocity(xSpeed, ySpeed);
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * Sets velocity.
     *
     * @param v the new velocity
     */
    public void setVelocity(final Velocity v) {
        this.velocity = v;
    }

    /**
     * Gets center.
     *
     * @return the center
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Gets radius.
     *
     * @return the radius
     */
    public int getSize() {
        return radius;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param canvas the DrawSurface to draw the ball on
     */
    public void drawOn(final DrawSurface canvas) {
        canvas.setColor(this.color);
        canvas.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        canvas.setColor(Color.black);
        canvas.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /**
     * Move one step.
     */
    public void moveOneStep() {
        this.center = this.velocity.applyToPoint(this.center);
    }

    /**
     * Moving the ball.
     *
     * @param boundary the boundaries of the ball movement
     */
    public void moveOneStep(final Boundary boundary) {
        this.center = this.velocity.applyToPoint(this.center);
        bounce(boundary);
    }

    /**
     * Bounce the ball if it got to a boundary.
     *
     * @param boundary the boundaries of the ball movement
     */
    private void bounce(final Boundary boundary) {
        boolean bounced = false;

        // getting the ball edges.
        final double ballRight = this.center.getX() + this.radius;
        final double ballLeft = this.center.getX() - this.radius;
        final double ballBottom = this.center.getY() + this.radius;
        final double ballTop = this.center.getY() - this.radius;

        // checking the horizontal boundaries
        if (ballRight >= boundary.right()) {
            // checking if the ball touching the right
            this.center.setX(boundary.right() - radius);
            this.velocity.reverseX();
            bounced = true;
        } else if (ballLeft <= boundary.left()) {
            // checking if the ball touching the left
            this.center.setX(boundary.left() + radius);
            this.velocity.reverseX();
            bounced = true;
        }
        // no else as the ball could intersect with both X boundary and Y boundary at the same boundary

        // checking the vertical boundaries
        if (ballBottom >= boundary.bottom()) {
            // checking if the ball touching the floor
            this.center.setY(boundary.bottom() - radius);
            this.velocity.reverseY();
            bounced = true;
        } else if (ballTop <= boundary.top()) {
            // checking if the ball touching the celling
            this.center.setY(boundary.top() + radius);
            this.velocity.reverseY();
            bounced = true;
        }

        if (bounced) {
            setRandomColor();
        }
    }

    /**
     * Get x int.
     *
     * @return the X
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Get y int.
     *
     * @return the Y
     */
    public int getY() {
        return (int) this.center.getX();
    }
}
