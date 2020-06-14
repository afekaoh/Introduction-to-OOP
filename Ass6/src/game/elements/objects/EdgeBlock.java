// ID 316044809
package game.elements.objects;

import game.collections.ElementsCollection;
import game.elements.shapes.Point;
import game.listeners.HitListener;

import java.util.Arrays;

/**
 * The class Edge block a block that blocks the edges of the screen.
 */
public class EdgeBlock extends Block {
    /**
     * Instantiates a new Edge block.
     *
     * @param point      the point
     * @param width      the width
     * @param height     the height
     * @param difficulty the difficulty
     * @param listeners  the listeners
     */
    public EdgeBlock(final Point point, final int width, final int height, final int difficulty,
                     final HitListener... listeners) {
        super(point, width, height, difficulty, null);
        Arrays.stream(listeners).forEach(this::addHitListener);
    }

    /**
     * Instantiates a new Edge block.
     *
     * @param topLeft    the top left
     * @param width      the width
     * @param height     the height
     * @param difficulty the difficulty
     */
    public EdgeBlock(final Point topLeft, final int width, final int height, final int difficulty) {
        super(topLeft, width, height, difficulty, null);
    }

    /**
     * Add the edge block to the game.
     *
     * @param elementsCollection the elements Collection
     */
    @Override
    public void addToGame(final ElementsCollection elementsCollection) {
        elementsCollection.addCollidable(this);
    }

    /**
     * Remove the edge block from the game.
     *
     * @param elementsCollection the elements collection
     */
    @Override
    public void removeFromGame(final ElementsCollection elementsCollection) {
        elementsCollection.removeCollidable(this);
        elementsCollection.removeElement(this);
    }
}
