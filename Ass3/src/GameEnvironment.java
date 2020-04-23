import java.util.ArrayList;
import java.util.List;
//todo doc
/**
 * The class Game environment.
 */
// ID 316044809
public class GameEnvironment {
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
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        final PointsComparator comp = new PointsComparator(trajectory.start());
        CollisionInfo info = null;
        for (Collidable c : collidables) {
            Point intersection = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (intersection == null) {
                continue;
            }
            if (info == null || comp.compare(info.collisionPoint(), intersection) > 0) {
                info = new CollisionInfo(intersection, c);
            }
        }
        return info;
    }
}
