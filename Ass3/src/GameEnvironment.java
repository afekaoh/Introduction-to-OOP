// ID 316044809

import java.util.ArrayList;
import java.util.List;

/**
 * The class Game environment.
 */
public final class GameEnvironment {
    private final List<Collidable> collidables;

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        collidables = new ArrayList<>();
    }

    /**
     * add the given collidable to the environment..
     *
     * @param collidable the collidable
     */
    public void addCollidable(Collidable collidable) {
        collidables.add(collidable);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory
     * @return the closest collision info or null if none happened
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        final PointsComparator comp = new PointsComparator(trajectory.start());
        CollisionInfo info = null;
        for (Collidable collidable : collidables) {
            Point intersection = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (intersection == null) {
                continue;
            }
            if (info == null || comp.compare(info.collisionPoint(), intersection) > 0) {
                info = new CollisionInfo(intersection, collidable);
            }
        }
        return info;
    }
}