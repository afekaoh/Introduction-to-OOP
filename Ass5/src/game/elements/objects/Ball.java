// ID 316044809
package game.elements.objects;

import biuoop.DrawSurface;
import game.collections.ElementsCollection;
import game.collections.GameEnvironment;
import game.collections.Sprite;
import game.elements.physics.Velocity;
import game.elements.shapes.Line;
import game.elements.shapes.Point;
import game.tools.CollisionInfo;

import java.awt.Color;
import java.util.Random;

/**
 * The class Ball representing a 2D ball.
 */
public class Ball implements Sprite {

    private final GameEnvironment environment;
    /**
     * The Radius of the ball.
     */
    private final int radius;
    /**
     * The Color of the ball.
     */
    private final Color color;
    /**
     * The Center point.
     */
    private Point center;
    /**
     * The game.geometry.physics.Velocity of the ball.
     */
    private Velocity velocity;

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
        setVelocityFromRadius();
        this.environment = environment;
    }

    /**
     * Sets a new velocity based on the ball radius.
     */
    private void setVelocityFromRadius() {
        Random rand = new Random();
        final double angle = 30 * (rand.nextBoolean() ? 1 : -1);

        // generating the speed
        final int maxRadius = 20;
        final int maxRadiusSq = maxRadius * maxRadius;
        final int minRadius = 1;
        final int maxSpeed = 5;
        final int minSpeed = 1;
        final int radiusToMap = Math.min(this.radius * this.radius, maxRadiusSq);
        final double speed = Velocity.map(radiusToMap, minRadius, maxRadiusSq, maxSpeed, minSpeed);
        this.velocity = Velocity.fromAngleAndSpeed(angle, speed);
    }


    // GameElement methods
    @Override
    public void addToGame(ElementsCollection e) {
        e.addSprite(this);
    }

    @Override
    public void removeFromGame(final ElementsCollection elementsCollection) {
        elementsCollection.removeSprite(this);
        elementsCollection.removeElement(this);
    }


    // Sprite methods
    @Override
    public void drawOn(final DrawSurface canvas) {
        canvas.setColor(this.color);
        canvas.fillCircle(getX(), getY(), this.radius);
        canvas.setColor(Color.black);
        canvas.drawCircle(getX(), getY(), this.radius);
    }

    /**
     * Get x.
     *
     * @return the X
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Get y.
     *
     * @return the Y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Moving the ball one step.
     */
    public void moveOneStep() {
        // checking if there is an obstacle
        final Line trajectory = new Line(center, this.velocity.applyToPoint(this.center));
        CollisionInfo collision = environment.getClosestCollision(trajectory);
        if (collision == null) {
            // there is no collision
            this.center = trajectory.end();
        } else {
            // getting the new velocity
            this.velocity = collision.collisionObject()
                                     .hit(this, collision.collisionPoint(), velocity);

            // checking where to move the ball
            Point p = null;
            final int maxTries = 100;
            int counter = 0;
            // moving the ball to a place where there isn't any obstacles
            while (collision != null) {
                p = trajectory.getPointByPercentage(0.8);
                trajectory.setEnd(p);
                if (counter > maxTries) {
                    // to make sure we don't get into infinite loop
                    final int obstacleTop = collision.collisionObject()
                                                     .getCollisionRectangle()
                                                     .top();
                    p.setY(obstacleTop - radius);
                    break;
                }
                collision = environment.getClosestCollision(trajectory);
                counter++;
            }
            this.center = p;
        }
    }
}
