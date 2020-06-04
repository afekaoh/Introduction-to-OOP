// ID 316044809
package game.listeners;

import game.collections.ElementsCollection;
import game.elements.objects.Ball;
import game.elements.objects.Block;
import game.tools.Counter;


/**
 * The class Ball remover.
 */
public class BallRemover implements HitListener {

    /**
     * The Remaining balls.
     */
    private final Counter remainingBalls;
    /**
     * The Elements.
     */
    private final ElementsCollection elements;

    /**
     * Instantiates a new Ball remover.
     *
     * @param elements      the elements
     * @param removedBlocks the removed blocks
     */
    public BallRemover(ElementsCollection elements, Counter removedBlocks) {
        this.remainingBalls = removedBlocks;
        this.elements = elements;
    }

    @Override
    public void hitEvent(final Block beingHit, final Ball hitter) {
        hitter.removeFromGame(elements);
        remainingBalls.decrease();
    }
}
