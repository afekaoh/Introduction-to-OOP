// ID 316044809

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The class Paddle.
 */
public class Paddle implements Sprite, Collidable {
    /**
     * The constant PADDLE_SPEED.
     */
    public static final int PADDLE_SPEED = 8;
    /**
     * The constant NUM_OF_REGIONS.
     */
    public static final int NUM_OF_REGIONS = 5;
    /**
     * The Boundary of the paddle.
     */
    private final Rectangle boundary;
    /**
     * The Color of the paddle.
     */
    private final Color color;

    /**
     * The game Settings.
     */
    private final GameSettings settings;

    /**
     * a keyboard reference.
     */
    private final KeyboardSensor keyboard;

    /**
     * The Velocity of the paddle.
     */
    private final Velocity velocity;


    /**
     * Instantiates a new Paddle.
     *
     * @param x        the x coordinate of the paddle
     * @param y        the y coordinate of the paddle
     * @param width    the width of the paddle
     * @param height   the height of the paddle
     * @param settings the settings of the game
     */
    public Paddle(int x, int y, int width, int height, GameSettings settings) {
        this.color = Color.WHITE;
        this.boundary = new Rectangle(new Point(x, y), width, height);
        this.velocity = new Velocity(0, 0);
        this.settings = settings;
        this.keyboard = settings.getKeyboard();
    }

    // GameElement methods
    @Override
    public void addToGame(ElementsCollection e) {
        e.addCollidable(this);
        e.addSprite(this);
    }


    // sprite methods
    @Override
    public void drawOn(DrawSurface canvas) {
        canvas.setColor(this.color);
        //drawing the rectangle
        canvas.fillRectangle(boundary.left(), boundary.top(), boundary.getWidth(), boundary.getHeight());

        // drawing the stroke
        canvas.setColor(Color.BLACK);
        canvas.drawRectangle(boundary.left(), boundary.top(), boundary.getWidth(), boundary.getHeight());
    }

    @Override
    public void timePassed() {
        move();
    }

    /**
     * Move.
     * moving the paddle left or right depending on the player
     */
    public void move() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
        this.boundary.setCenter(velocity.applyToPoint(boundary.getCenter()));
        this.velocity.setXSpeed(0);
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        final Rectangle gameEdge = settings.getGameEdge();
        if (boundary.left() > gameEdge.left()) {
            // if it haven't reached the edge of the screen
            this.velocity.setXSpeed(-PADDLE_SPEED);
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        final Rectangle gameEdge = settings.getGameEdge();
        if (boundary.right() < gameEdge.right()) {
            // if it haven't reached the edge of the screen
            this.velocity.setXSpeed(PADDLE_SPEED);
        }
    }

    // collidable methods
    @Override
    public Rectangle getCollisionRectangle() {
        return this.boundary;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        int region = getRegion(collisionPoint);
        double angle = Velocity.map(region, 1, NUM_OF_REGIONS, -60, 60);
        return angle == 0
                ? new Velocity(currentVelocity.getXSpeed(), -currentVelocity.getYSpeed())
                : Velocity.fromAngleAndSpeed(angle, currentVelocity.getMag());
    }

    /**
     * Get region.
     * returning the region on the paddle of which the ball has landed
     *
     * @param collisionPoint the collision point
     * @return the region
     */
    private int getRegion(Point collisionPoint) {
        double percentage = (collisionPoint.getX() - boundary.left()) / boundary.getWidth();
        for (double i = 1; i < Paddle.NUM_OF_REGIONS; i++) {
            if (percentage <= i / Paddle.NUM_OF_REGIONS) {
                return (int) i;
            }
        }
        return Paddle.NUM_OF_REGIONS;
    }

}

