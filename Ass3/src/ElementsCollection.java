// ID 316044809

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The class Elements collection.
 * keeps reference to all the elements in the game.
 */
public class ElementsCollection {
    /**
     * The Elements.
     */
    private final List<GameElement> elements;
    /**
     * The Environment.
     */
    private final GameEnvironment environment;
    /**
     * The Sprites.
     */
    private final SpriteCollection sprites;

    /**
     * Instantiates a new Elements collection.
     */
    public ElementsCollection() {
        this.elements = new ArrayList<>();
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
    }

    /**
     * Add collidable.
     *
     * @param collidable the collidable
     */
    public void addCollidable(Collidable collidable) {
        this.environment.addCollidable(collidable);
    }

    /**
     * Remove collidable.
     *
     * @param collidable the collidable
     */
    public void removeCollidable(Collidable collidable) {
        this.environment.removeCollidable(collidable);
    }


    /**
     * Add sprite to the game.
     *
     * @param sprite the sprite to add
     */
    public void addSprite(Sprite sprite) {
        this.sprites.addSprite(sprite);
    }

    /**
     * Remove sprite from the game.
     *
     * @param sprite the sprite to remove
     */
    public void removeSprite(Sprite sprite) {
        this.sprites.removeSprite(sprite);
    }

    /**
     * Add element to the game.
     *
     * @param element the element
     */
    public void addElement(GameElement element) {
        this.elements.add(element);
    }

    /**
     * Add all the elements to the game.
     */
    public void addAll() {
        elements.forEach(e -> e.addToGame(this));
    }

    /**
     * apply the sprites methods to all the sprites in the game.
     *
     * @param canvas the canvas which to draw the sprite on
     */
    public void runSprites(DrawSurface canvas) {
        sprites.drawAllOn(canvas);
        sprites.notifyAllTimePassed();
    }

    /**
     * Check if an element has died and removed it if so.
     */
    public void checkDeath() {
        elements.stream().filter(GameElement::isDead).forEach(e -> e.removeFromGame(this));
        elements.removeIf(GameElement::isDead);
    }

    /**
     * Get environment.
     *
     * @return the GameEnvironment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }
}
