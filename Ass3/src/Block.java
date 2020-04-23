import biuoop.DrawSurface;

/**
 * The class Block.
 */
// ID 316044809
public class Block implements Collidable, Sprite {

    /**
     * The Boundary.
     */
    private final Rectangle boundary;

    /**
     * Instantiates a new Block.
     *
     * @param topLeft the top left
     * @param width   the width
     * @param height  the height
     */
    public Block(Point topLeft, int width, int height) {
        this.boundary = new Rectangle(topLeft, width, height);
    }

    /**
     * Show.
     *
     * @param canvas the canvas
     */
    public void drawOn(DrawSurface canvas) {
        this.boundary.show(canvas);
    }

    @Override
    public void timePassed() {
        // do nothing
    }

    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return boundary;
    }

    /**
     * todo
     * Hit velocity.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    @Override
    public Velocity hit(final Point collisionPoint, final Velocity currentVelocity) {
        Velocity newV = new Velocity(currentVelocity);
        if (collisionPoint.getX() == boundary.left() || collisionPoint.getX() == boundary.right()) {
            newV.reverseX();
        }
        if (collisionPoint.getY() == boundary.top() || collisionPoint.getY() == boundary.bottom()) {
            newV.reverseY();
        }
        return newV;
    }
}