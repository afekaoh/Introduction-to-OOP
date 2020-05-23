// ID 316044809
package game.collections;

/**
 * The interface game.Game element.
 */
public interface GameElement {

    /**
     * Add element to the game.
     *
     * @param elementsCollection the elements collection
     */
    void addToGame(ElementsCollection elementsCollection);

    /**
     * Remove from game.
     *
     * @param elementsCollection the elements collection
     */
    void removeFromGame(ElementsCollection elementsCollection);

    void decreaseLife();

    boolean isDead();
}
