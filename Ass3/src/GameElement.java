// ID 316044809

/**
 * The interface Game element.
 */
public interface GameElement {

    /**
     * Add element to the game.
     *
     * @param elementsCollection the elements collection
     */
    void addToGame(ElementsCollection elementsCollection);

    /**
     * Remove element from the game.
     *
     * @param elementsCollection the elements collection
     */
    void removeFromGame(ElementsCollection elementsCollection);

    /**
     * Is dead.
     * a method to check if a specific element has died
     *
     * @return true if the element has died amd false otherwise
     */
    boolean isDead();

}
