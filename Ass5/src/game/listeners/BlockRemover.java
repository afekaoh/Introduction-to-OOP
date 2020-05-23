package game.listeners;


import game.Game;
import game.collections.ElementsCollection;
import game.geometry.objects.Ball;
import game.geometry.objects.Block;
import game.tools.Counter;

// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.

public class BlockRemover implements HitListener {
    private final Counter remainingBlocks;
    private final ElementsCollection elements;

    public BlockRemover(Game game, Counter removedBlocks) {
        this.remainingBlocks = removedBlocks;
        this.elements = game.getElementsCollection();
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.decreaseLife();
        if (beingHit.isDead()) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(elements);
            remainingBlocks.decrease(1);
        }
    }
}