// ID 316044809
package game.collections;

import game.geometry.objects.Ball;
import game.geometry.physics.Velocity;
import game.geometry.shapes.Point;
import game.geometry.shapes.Rectangle;

/**
 * The interface Collidable.
 */
public interface Collidable extends GameElement {
    /**
     * Gets collision rectangle.
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit
     * (based on the force the object inflicted on us).
     *
     * @param hitter          the hitter ball
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
