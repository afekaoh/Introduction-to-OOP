// ID 316044809

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
     * The Boundary of the block.
     */
    private final Rectangle boundary;
    /**
     * The Difficulty of the block.
     */
    private final int difficulty;
    /**
     * The Color of the block.
     */
    private Color color;

    /**
     * Instantiates a new Block.
     *
     * @param topLeft    the top left point of the block
     * @param width      the width of the block
     * @param height     the height of the block
     * @param difficulty the difficulty of the block
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
     * draw the block on the given DrawSurface.
     *
     * @param canvas the DrawSurface to draw the block on
     */
    public void drawOn(DrawSurface canvas) {
        //drawing the rectangle
        canvas.setColor(this.color);
        canvas.fillRectangle(boundary.left(), boundary.top(), boundary.getWidth(), boundary.getHeight());

        // drawing the stroke
        canvas.setColor(Color.BLACK);
        canvas.drawRectangle(boundary.left(), boundary.top(), boundary.getWidth(), boundary.getHeight());
    }

    @Override
    public void timePassed() {
        setColor();
    }

    @Override
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return boundary;
    }


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
