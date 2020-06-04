package game.listeners;


import game.collections.ElementsCollection;
import game.elements.objects.Ball;
import game.elements.objects.Block;
import game.tools.Counter;


/**
 * The class Block remover.
 * <p>
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    /**
     * The Remaining blocks.
     */
    private final Counter remainingBlocks;
    /**
     * The Elements.
     */
    private final ElementsCollection elements;

    /**
     * Instantiates a new Block remover.
     *
     * @param elements      the elements
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(ElementsCollection elements, Counter removedBlocks) {
        this.remainingBlocks = removedBlocks;
        this.elements = elements;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.decreaseLife();
        if (beingHit.isDead()) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(elements);
            remainingBlocks.decrease();
        }
    }
}