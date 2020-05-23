package game.listeners;

import game.Game;
import game.collections.ElementsCollection;
import game.geometry.objects.Ball;
import game.geometry.objects.Block;
import game.tools.Counter;

// ID 316044809
public class BallRemover implements HitListener {
    private final Counter remainingBalls;
    private final ElementsCollection elements;

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
