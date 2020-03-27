// ID 316044809

import biuoop.DrawSurface;

import java.awt.Color;

import static java.util.concurrent.ThreadLocalRandom.current;

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
    private int radius;
    /**
     * The Color of the ball.
     */
    private Color color;

    /**
     * The Velocity of the ball.
     */
    private Velocity velocity;


    /**
     * Instantiates a new Ball with center radius and color.
     *
     * @param center the center of the call
     * @param radius the radius of the ball
     * @param color the color of the ball
     */
    public Ball(final Point center, final int radius, final Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
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
     * Instantiates a new Ball in random spot between (0,0) to (xRange, yRange).
     *
     * @param radius the radius
     * @param xRange the xRange
     * @param yRange the yRange
     */
    public Ball(final int radius, final double xRange, final double yRange) {
        this(radius, new Point(0, 0), new Point(xRange, yRange));
    }


    /**
     * Instantiates a new Ball in random spot between start to end with a random speed.
     *
     * @param radius the radius
     * @param start the start
     * @param end the end
     */
    public Ball(final int radius, final Point start, final Point end) {
        start.setX(start.getX() + radius);
        this.center = Point.getRandomPoint(start, end);
        this.radius = radius;
        this.color = Color.getHSBColor(current().nextFloat(), current().nextFloat(), current().nextFloat());
        final double angle = current().nextDouble() * 360;
        final double speed = Velocity.map(radius, 1, 50, 15, 5);
        this.velocity = Velocity.fromAngleAndSpeed(angle, speed);
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
    public int getRadius() {
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
    }

    /**
     * Moving the ball.
     *
     * @param frame the boundaries of the ball movement
     */
    public void move(final Line frame) {
        // moving the ball
        this.center = this.velocity.applyToPoint(this.center);
        bounce(frame);
    }

    /**
     * Bounce the ball if it got to a boundary.
     *
     * @param frame the boundaries of the ball movement
     */
    private void bounce(final Line frame) {
        boolean bounced = false;

        // extracting the XY data.
        final int xStart = (int) frame.start().getX();
        final int xEnd = (int) frame.end().getX();
        final int yStart = (int) frame.start().getY();
        final int yEnd = (int) frame.end().getY();

        // checking the horizontal boundaries
        if (this.center.getX() + this.radius >= xEnd) {
            // checking if the ball touching the right well
            this.center.setX(xEnd - radius);
            this.velocity.setXSpeed(-this.velocity.getXSpeed());
            bounced = true;
        } else if (this.center.getX() - this.radius <= xStart) {
            // checking if the ball touching the left well
            this.center.setX(xStart + radius);
            this.velocity.setXSpeed(-this.velocity.getXSpeed());
            bounced = true;
        }
        // no else as the ball could intersect with both X boundary and Y boundary at the same frame

        // checking the vertical boundaries
        if (this.center.getY() + this.radius >= yEnd) {
            // checking if the ball touching the floor
            this.center.setY(yEnd - radius);
            this.velocity.setYSpeed(-this.velocity.getYSpeed());
            bounced = true;
        } else if (this.center.getY() - this.radius <= yStart) {
            // checking if the ball touching the celling
            this.center.setY(yStart + radius);
            this.velocity.setYSpeed(-this.velocity.getYSpeed());
            bounced = true;
        }

        if (bounced) {
            // changing the color every bounce
            this.color = Color.getHSBColor(current().nextFloat(), current().nextFloat(), current().nextFloat());
        }
    }
}
