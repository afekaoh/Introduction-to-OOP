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
    private static final Color[] DIFFICULTY_COLORS = {
            Color.BLACK, Color.MAGENTA, Color.YELLOW, Color.RED, Color.CYAN, Color.GREEN
    };
    /**
     * The Boundary of the block.
     */
    private final Rectangle boundary;
    /**
     * The Difficulty of the block.
     */
    private int difficulty;
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
        return DIFFICULTY_COLORS[(difficulty % (DIFFICULTY_COLORS.length - 1)) + 1];
    }


    // GameElement methods
    @Override
    public void addToGame(ElementsCollection e) {
        e.addCollidable(this);
        e.addSprite(this);
    }

    @Override
    public void removeFromGame(ElementsCollection e) {
        e.removeSprite(this);
        e.removeCollidable(this);
    }

    @Override
    public boolean isDead() {
        return this.difficulty == 0;
    }


    // sprite methods
    @Override
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


    // collidable methods
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
        if (this.difficulty > 0) {
            this.difficulty--;
        }
        return newV;
    }
}
