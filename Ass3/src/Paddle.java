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
     * Instantiates a new Paddle.
     *
     * @param x        the x
     * @param y        the y
     * @param width    the width
     * @param height   the height
     * @param keyboard the keyboard
     */
    public Paddle(int x, int y, int width, int height, KeyboardSensor keyboard) {
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
        this.velocity.setXSpeed(-5);
    }

    /**
     * Move right.
     */
    public void moveRight() {
        this.velocity.setXSpeed(5);
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
        canvas.fillRectangle((int) boundary.left(), (int) boundary.top(), boundary.getWidth(), boundary.getHeight());

        // drawing the stroke
        canvas.setColor(Color.BLACK);
        canvas.drawRectangle((int) boundary.left(), (int) boundary.top(), boundary.getWidth(), boundary.getHeight());
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
        if (collisionPoint.getY() == boundary.top()) {
            int region = getRegion(collisionPoint);
            switch (region) {
                case 1:
                    return Velocity.fromAngleAndSpeed(-60, currentVelocity.getMag());
                case 2:
                    return Velocity.fromAngleAndSpeed(-30, currentVelocity.getMag());
                case 3:
                    return new Velocity(currentVelocity.getXSpeed(), -currentVelocity.getYSpeed());
                case 4:
                    return Velocity.fromAngleAndSpeed(30, currentVelocity.getMag());
                case 5:
                    return Velocity.fromAngleAndSpeed(60, currentVelocity.getMag());
                default:
                    return new Velocity(0, 0);
            }
        }
        return currentVelocity;
    }

    /**
     * todo
     * Gets region.
     *
     * @param collisionPoint the collision point
     * @return the region
     */
    private int getRegion(Point collisionPoint) {
        double percentege = (collisionPoint.getX() - boundary.left()) / boundary.getWidth();
        if (percentege <= 0.2) {
            return 1;
        } else if (percentege <= 0.4) {
            return 2;
        } else if (percentege <= 0.6) {
            return 3;
        } else if (percentege <= 0.8) {
            return 4;
        } else {
            return 5;
        }
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

