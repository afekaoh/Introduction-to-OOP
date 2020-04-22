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

    //todo add game environment

    /**
     * Instantiates a new Ball with center radius and color.
     *
     * @param center   the center of the call
     * @param radius   the radius of the ball
     * @param color    the color of the ball
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
     * @param x      the x coordinate of the ball
     * @param y      the y coordinate of the ball
     * @param radius the radius of the ball
     * @param color  the color of the ball
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
     * @param angle  the angle
     * @param speed  the speed
     * @param color  the color
     */
    public Ball(final Point center, final int radius, final double angle, final double speed, final Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.velocity = Velocity.fromAngleAndSpeed(angle, speed);
    }

    /**
     * Instantiates a new Ball in random spot inside a rectangle.
     *
     * @param radius    the radius
     * @param rectangle the rectangle to draw the balls into
     */
    public Ball(final int radius, final Rectangle rectangle) {
        Point start = new Point(rectangle.left() + radius, rectangle.top() + radius);
        Point end = new Point(rectangle.right() - radius, rectangle.bottom() - radius);
        this.center = Point.getRandomPoint(start, end);
        this.radius = radius;
        setVelocityFromRadius();
        setRandomColor();
    }

    /**
     * Sets a new velocity based on the ball radius.
     */
    private void setVelocityFromRadius() {
        Random rand = new Random();
        // generating a random angle
        final double maxAngle = 70;
        final double minAngle = 12.5;
        // generating a random quadrant for the speed to point to
        final int quadrant = rand.nextInt(4) * 90;
        final double angle = quadrant + (rand.nextDouble() * (maxAngle - minAngle) + minAngle);

        // generating the speed
        final int maxRadius = 50;
        final int maxRadiusSq = maxRadius * maxRadius;
        final int minRadius = 1;
        final int maxSpeed = 15;
        final int minSpeed = 7;
        final int radiusToMap = Math.min(this.radius * this.radius, maxRadiusSq);
        final double speed = Animation.map(radiusToMap, minRadius, maxRadiusSq, maxSpeed, minSpeed);
        this.velocity = Velocity.fromAngleAndSpeed(angle, speed);
    }

    /**
     * give the Ball a new random color.
     */
    private void setRandomColor() {
        Random rand = new Random();
        this.color = Color.getHSBColor(rand.nextFloat(), 0.9f, 1f);
    }

    /**
     * Sets radius.
     *
     * @param r the radius
     */
    public void setRadius(final int r) {
        this.radius = r;
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
     * @param rectangle the boundaries of the ball movement
     */
    public void moveOneStep(final Rectangle rectangle) {
        this.center = this.velocity.applyToPoint(this.center);
        bounce(rectangle);
    }

    /**
     * Bounce the ball if it got to a rectangle.
     *
     * @param rectangle the boundaries of the ball movement
     */
    private void bounce(final Rectangle rectangle) {
        boolean bounced = false;

        // getting the ball edges.
        final double ballRight = this.center.getX() + this.radius;
        final double ballLeft = this.center.getX() - this.radius;
        final double ballBottom = this.center.getY() + this.radius;
        final double ballTop = this.center.getY() - this.radius;

        // checking the horizontal boundaries
        if (ballRight >= rectangle.right()) {
            // checking if the ball touching the right
            this.center.setX(rectangle.right() - radius);
            this.velocity.reverseX();
            bounced = true;
        } else if (ballLeft <= rectangle.left()) {
            // checking if the ball touching the left
            this.center.setX(rectangle.left() + radius);
            this.velocity.reverseX();
            bounced = true;
        }
        // no else as the ball could intersect with both X rectangle and Y rectangle at the same rectangle

        // checking the vertical boundaries
        if (ballBottom >= rectangle.bottom()) {
            // checking if the ball touching the floor
            this.center.setY(rectangle.bottom() - radius);
            this.velocity.reverseY();
            bounced = true;
        } else if (ballTop <= rectangle.top()) {
            // checking if the ball touching the celling
            this.center.setY(rectangle.top() + radius);
            this.velocity.reverseY();
            bounced = true;
        }

        if (bounced) {
            // changing the color every bounce
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
        return (int) this.center.getY();
    }
}
