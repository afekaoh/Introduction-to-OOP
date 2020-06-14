// ID 316044809
package game.elements.objects;

import biuoop.DrawSurface;
import game.collections.Collidable;
import game.collections.ElementsCollection;
import game.collections.Sprite;
import game.elements.physics.Velocity;
import game.elements.shapes.Point;
import game.elements.shapes.Rectangle;
import game.listeners.HitListener;
import game.listeners.HitNotifier;
import game.tools.Counter;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The class game.geometry.objects.Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    /**
     * The Boundary of the block.
     */
    private final Rectangle boundary;
    /**
     * The Hit listeners.
     */
    private final List<HitListener> hitListeners;
    /**
     * The Difficulty of the block.
     */
    private final Counter life;
    private final Color[] colorSpace;
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
     * @param life       the number of life of the block
     * @param colorSpace the color space
     */
    public Block(Point topLeft, int width, int height, int life, Color[] colorSpace) {
        this.colorSpace = colorSpace;
        this.boundary = new Rectangle(topLeft, width, height);
        this.life = new Counter(life);
        this.hitListeners = new ArrayList<>();
        if (colorSpace != null) {
            setColor();
        }
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
        return colorSpace[this.life.getValue() % colorSpace.length];
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

    /**
     * Decrease life.
     */
    public void decreaseLife() {
        this.life.decrease(1);
    }

    /**
     * Is dead boolean.
     *
     * @return the boolean
     */
    public boolean isDead() {
        return this.life.getValue() == -1;
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
    public Velocity hit(final Ball hitter, final Point collisionPoint,
                        final Velocity currentVelocity) {
        Velocity newV = new Velocity(currentVelocity);
        if (collisionPoint.getX() == boundary.left() || collisionPoint.getX() == boundary.right()) {
            newV.reverseX();
        }
        if (collisionPoint.getY() == boundary.top() || collisionPoint.getY() == boundary.bottom()) {
            newV.reverseY();
        }
        this.notifyHit(hitter);
        return newV;
    }


    // hitNotifier methods
    @Override
    public void addHitListener(final HitListener hitListener) {
        this.hitListeners.add(hitListener);
    }

    @Override
    public void removeHitListener(final HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Notify all the listeners about the hit.
     *
     * @param hitter the hitter ball
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> copy = new ArrayList<>(this.hitListeners);
        copy.forEach(hl -> hl.hitEvent(this, hitter));
    }
}
