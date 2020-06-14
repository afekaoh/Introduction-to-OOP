// ID 316044809
package game.animation.levels.factory;

/**
 * The class Level factory.
 */
public class AbstractLevelFactory {
    /**
     * The Width.
     */
    private final int width;
    /**
     * The Height.
     */
    private final int height;

    /**
     * Instantiates a new Abstract level factory.
     *
     * @param width  the width
     * @param height the height
     */
    public AbstractLevelFactory(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Create level level information.
     *
     * @param numOfLevel the num of level
     * @return the level information
     */
    public LevelFactory createFactory(int numOfLevel) {
        switch (numOfLevel) {
            case 1:
                return new Level1Factory(width, height);
            case 2:
                return new Level2Factory(width, height);
            case 3:
                return new Level3Factory(width, height);
            case 4:
                return new Level4Factory(width, height);
            default:
                throw new IllegalArgumentException("send number between 1-4");
        }
    }
}
