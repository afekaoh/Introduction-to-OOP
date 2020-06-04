// ID 316044809
package game.elements.objects;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.collections.Collidable;
import game.collections.ElementsCollection;
import game.collections.GameEnvironment;
import game.collections.Sprite;
import game.elements.physics.Velocity;
import game.elements.shapes.Line;
import game.elements.shapes.Point;
import game.elements.shapes.Rectangle;
import game.tools.CollisionInfo;
import game.tools.GameSettings;

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

    /**
     * The Environment of the game.
     */
    private final GameEnvironment environment;

    /**
     * The game.geometry.physics.Velocity of the paddle.
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
    public Paddle(int x, int y, int width, int height, final GameSettings settings) {
        this.color = Color.decode("#7BC0E2");
        this.boundary = new Rectangle(new Point(x, y), width, height);
        this.velocity = new Velocity(0, 0);
        this.keyboard = settings.getKeyboard();
        this.environment = settings.getEnvironment();
    }

    // GameElement methods
    @Override
    public void addToGame(ElementsCollection e) {
        e.addCollidable(this);
        e.addSprite(this);
    }

    @Override
    public void removeFromGame(final ElementsCollection elementsCollection) {
        elementsCollection.removeSprite(this);
        elementsCollection.removeCollidable(this);
        elementsCollection.removeElement(this);
    }

    // Sprite methods
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
     * <p>
     * moving the paddle left or right depending on the player input
     */
    public void move() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
        this.boundary.setCenter(velocity.applyToPoint(boundary.getCenter()));
        // resetting the velocity for the next frame
        this.velocity.setXSpeed(0);
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        // giving the paddle a velocity in the left direction
        this.velocity.setXSpeed(-PADDLE_SPEED);
        // checking it it got to the edge of the screen
        final Point upperLeft = boundary.getUpperLeft().translate(0, -1);
        // take a line in the X as the paddle but higher on the Y to avoid collision with himself
        Line trajectory = new Line(upperLeft, velocity.applyToPoint(upperLeft));
        CollisionInfo info = environment.getClosestCollision(trajectory);

        // there is only one block in this side of the paddle
        if (info != null) {
            // moving the paddle to the appropriate position
            boundary.setCenter(info.collisionPoint().translate(boundary.getWidth() / 2, boundary.getHeight() / 2 + 1));
            // stopping the paddle
            this.velocity.setXSpeed(0);
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        // giving the paddle a velocity in the right direction
        this.velocity.setXSpeed(PADDLE_SPEED);

        // checking it it got to the edge of the screen
        final Point upperRight = boundary.getUpperLeft().translate(boundary.getWidth(), -1);
        // take a line in the X as the paddle but higher on the Y to avoid collision with himself
        Line trajectory = new Line(upperRight, velocity.applyToPoint(upperRight));
        CollisionInfo info = environment.getClosestCollision(trajectory);
        // there is only one block in this side of the paddle
        if (info != null) {
            // moving the paddle to the appropriate position
            boundary.setCenter(info.collisionPoint()
                                   .translate(-(boundary.getWidth() / 2), (boundary.getHeight() / 2) + 1));
            // stopping the paddle
            this.velocity.setXSpeed(0);
        }
    }

    // collidable methods
    @Override
    public Rectangle getCollisionRectangle() {
        return this.boundary;
    }

    @Override
    public Velocity hit(final Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        int region = getRegion(collisionPoint);
        double angle = Velocity.map(region, 1, NUM_OF_REGIONS, -60, 60);
        return angle == 0
                ? new Velocity(currentVelocity.getXSpeed(), -currentVelocity.getYSpeed())
                : Velocity.fromAngleAndSpeed(angle, currentVelocity.getMag());
    }

    /**
     * Get region.
     * <p>
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

