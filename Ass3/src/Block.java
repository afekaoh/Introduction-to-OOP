// ID 316044809
//todo doc

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The class Block.
 */
public class Block implements Collidable, Sprite {

    /**
     * The constant DIFFICULTY_COLORS.
     */
    private static final Color[] DIFFICULTY_COLORS = {Color.MAGENTA, Color.YELLOW, Color.RED, Color.CYAN, Color.GREEN};
    /**
     * The Boundary.
     */
    private final Rectangle boundary;
    /**
     * The Difficulty.
     */
    private final int difficulty;
    /**
     * The Color.
     */
    private Color color;

    /**
     * Instantiates a new Block.
     *
     * @param topLeft    the top left
     * @param width      the width
     * @param height     the height
     * @param difficulty the color
     */
    public Block(Point topLeft, int width, int height, int difficulty) {
        this.boundary = new Rectangle(topLeft, width, height);
        this.difficulty = difficulty;
        setColor();
    }

    /**
     * Sets color.
     */
    public void setColor() {
        this.color = getColor();
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    private Color getColor() {
        return DIFFICULTY_COLORS[difficulty % 5];
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
        canvas.setColor(Color.BLACK);
        canvas.drawRectangle((int) boundary.left(), (int) boundary.top(), boundary.getWidth(), boundary.getHeight());
    }

    /**
     * Time passed.
     */
    @Override
    public void timePassed() {
        setColor();
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
