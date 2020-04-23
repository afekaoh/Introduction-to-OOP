// ID 316044809

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * The class Ball representing a 2D ball.
 */
public class Ball implements Sprite {

    private final GameEnvironment environment;
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
    private Velocity velocity;
    //todo add game environment

    /**
     * Instantiates a new Ball with center radius and color.
     *
     * @param center      the center of the call
     * @param radius      the radius of the ball
     * @param color       the color of the ball
     * @param environment the environment
     */
    public Ball(final Point center, final int radius, final Color color, GameEnvironment environment) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.velocity = new Velocity((current().nextBoolean() ? 1 : -1) * 10, -10);
        this.environment = environment;
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
        final double speed = Velocity.map(radiusToMap, minRadius, maxRadiusSq, maxSpeed, minSpeed);
        this.velocity = Velocity.fromAngleAndSpeed(angle, speed);
    }

    public void addToGame(Game game) {
        game.addSprite(this);
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
     * Time passed.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Move one step.
     */
    public void moveOneStep() {
        final Line trajectory = new Line(center, this.velocity.applyToPoint(this.center));
        CollisionInfo collision = environment.getClosestCollision(trajectory);
        if (collision == null) {
            this.center = trajectory.end();
        } else {
            Point p = null;
            this.velocity = collision.collisionObject().hit(collision.collisionPoint(), velocity);
            while (collision != null) {
                p = trajectory.middle();
                trajectory.setEnd(p);
                collision = environment.getClosestCollision(trajectory);
            }
            this.center = p;
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
