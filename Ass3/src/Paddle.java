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
     * a keyboard reference.
     */
    private final KeyboardSensor keyboard;

    private final GameEnvironment environment;

    /**
     * The Velocity of the paddle.
     */
    private final Velocity velocity;


    /**
     * Instantiates a new Paddle.
     *
     * @param x           the x coordinate of the paddle
     * @param y           the y coordinate of the paddle
     * @param width       the width of the paddle
     * @param height      the height of the paddle
     * @param keyboard    the keyboard
     * @param environment the environment
     */
    public Paddle(int x, int y, int width, int height, KeyboardSensor keyboard, final GameEnvironment environment) {
        this.color = Color.WHITE;
        this.boundary = new Rectangle(new Point(x, y), width, height);
        this.velocity = new Velocity(0, 0);
        this.keyboard = keyboard;
        this.environment = environment;
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
        final Point upperLeft = boundary.getUpperLeft().translate(0, -1);
        Line trajectory = new Line(upperLeft, upperLeft);
        CollisionInfo info = environment.getClosestCollision(trajectory);
        if (info != null) {
            this.velocity.setXSpeed(0);
        } else {
            this.velocity.setXSpeed(-PADDLE_SPEED);
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        final Point upperRight = boundary.getUpperLeft().translate(boundary.getWidth(), -1);
        Line trajectory = new Line(upperRight, upperRight);
        CollisionInfo info = environment.getClosestCollision(trajectory);
        if (info != null) {
            this.velocity.setXSpeed(0);
        } else {
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

