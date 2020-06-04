// ID 316044809
package game.tools;

import game.collections.Collidable;
import game.elements.shapes.Point;

/**
 * The class Collision info - an object that holding the information needed about the collision.
 */
public class CollisionInfo {
    /**
     * The Collision point - the of intersection point.
     */
    private final Point collisionPoint;
    /**
     * The object which the Collision happened with.
     */
    private final Collidable collisionObject;

    /**
     * Instantiates a new Collision info.
     *
     * @param collisionPoint  the collision point
     * @param collisionObject the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Collision point point.
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * Collision object collidable.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}

