// ID 316044809
package game.listeners;

import game.Game;
import game.collections.ElementsCollection;
import game.geometry.objects.Ball;
import game.geometry.objects.Block;
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
     * @param game          the game
     * @param removedBlocks the removed blocks
     */
    public BallRemover(Game game, Counter removedBlocks) {
        this.remainingBalls = removedBlocks;
        this.elements = game.getElementsCollection();
    }

    @Override
    public void hitEvent(final Block beingHit, final Ball hitter) {
        hitter.removeFromGame(elements);
        remainingBalls.decrease(1);
    }
}
