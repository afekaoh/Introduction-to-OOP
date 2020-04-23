import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The class Block.
 */
// ID 316044809
public class Block implements Collidable, Sprite {

    /**
     * The Boundary.
     */
    private final Rectangle boundary;
    private Color color;

    /**
     * Instantiates a new Block.
     *
     * @param topLeft the top left
     * @param width   the width
     * @param height  the height
     */
    public Block(Point topLeft, int width, int height) {
        this.boundary = new Rectangle(topLeft, width, height);
        this.color = Color.BLACK;
    }

    /**
     * Show.
     *
     * @param canvas the canvas
     */
    public void drawOn(DrawSurface canvas) {
        canvas.setColor(this.color);
        //drawing the rectangle
        canvas.fillRectangle((int) boundary.left(), (int) boundary.top(), boundary.getWidth(), boundary.getHeight());

        // drawing the stroke
        canvas.setColor(Color.WHITE);
        canvas.drawRectangle((int) boundary.left(), (int) boundary.top(), boundary.getWidth(), boundary.getHeight());
    }

    @Override
    public void timePassed() {
        // do nothing
    }

    /**
     * todo
     * Add to game.
     *
     * @param game the game
     */
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
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
