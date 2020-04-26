// ID 316044809
//todo doc

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
    public static final int PADDLE_SPEED = 5;
    public static final int NUM_OF_REGIONS = 5;
    /**
     * The Boundary.
     */
    private final Rectangle boundary;
    /**
     * The Velocity.
     */
    private final Velocity velocity;
    /**
     * The Color.
     */
    private final Color color;
    /**
     * The Keyboard.
     */
    private final KeyboardSensor keyboard;

    /**
     * The Environment.
     */
    private final GameEnvironment environment;


    /**
     * Instantiates a new Paddle.
     *
     * @param x           the x
     * @param y           the y
     * @param width       the width
     * @param height      the height
     * @param keyboard    the keyboard
     * @param environment the environment
     */
    public Paddle(int x, int y, int width, int height, KeyboardSensor keyboard, GameEnvironment environment) {
        this.environment = environment;
        this.color = Color.WHITE;
        // todo fix magic numbers
        this.boundary = new Rectangle(new Point(x, y), width, height);
        this.velocity = new Velocity(0, 0);
        this.keyboard = keyboard;
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        this.velocity.setXSpeed(-PADDLE_SPEED);
        final Point upperLeft = this.boundary.getUpperLeft();
        Line trajectory = new Line(upperLeft, velocity.applyToPoint(upperLeft));
        CollisionInfo info = environment.getClosestCollision(trajectory);
        if (info != null) {
            final int edgeRight = info.collisionObject().getCollisionRectangle().right();
            if (edgeRight <= this.boundary.left()) {
                velocity.setXSpeed(0);
            }
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        this.velocity.setXSpeed(PADDLE_SPEED);
        final Point downRight = this.boundary.getDownRight().translate(1, 0);
        Line trajectory = new Line(velocity.applyToPoint(downRight), downRight);
        CollisionInfo info = environment.getClosestCollision(trajectory);
        if (info != null) {
            final int edgeLeft = info.collisionObject().getCollisionRectangle().left();
            if (edgeLeft > this.boundary.left()) {
                velocity.setXSpeed(0);
            }
        }
    }

    /**
     * Time passed.
     */
// Sprite
    public void timePassed() {
        move();
    }

    /**
     * Move.
     */
    public void move() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
        this.boundary.move(velocity);
        this.velocity.setSpeed(0, 0);
    }

    /**
     * Draw on.
     *
     * @param canvas the canvas
     */
    public void drawOn(DrawSurface canvas) {
        canvas.setColor(this.color);
        //drawing the rectangle
        canvas.fillRectangle(boundary.left(), boundary.top(), boundary.getWidth(), boundary.getHeight());

        // drawing the stroke
        canvas.setColor(Color.BLACK);
        canvas.drawRectangle(boundary.left(), boundary.top(), boundary.getWidth(), boundary.getHeight());
    }

    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
// Collidable
    public Rectangle getCollisionRectangle() {
        return this.boundary;
    }

    /**
     * Hit velocity.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        int region = getRegion(collisionPoint, NUM_OF_REGIONS);
        double angle = Velocity.map(region, 1, NUM_OF_REGIONS, -60, 60);
        return angle == 0 ? new Velocity(currentVelocity.getXSpeed(), -currentVelocity.getYSpeed())
                : Velocity.fromAngleAndSpeed(angle, currentVelocity.getMag());
    }

    /**
     * todo
     * Gets region.
     *
     * @param collisionPoint the collision point
     * @param numOfRegions   the num of regions
     * @return the region
     */
    private int getRegion(Point collisionPoint, int numOfRegions) {
        double percentage = (collisionPoint.getX() - boundary.left()) / boundary.getWidth();
        for (double i = 1; i < numOfRegions; i++) {
            if (percentage <= i / numOfRegions) {
                return (int) i;
            }
        }
        return numOfRegions;
    }

    /**
     * todo
     * Add to game.
     *
     * @param g the g
     */
// Add this paddle to the game.
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}

