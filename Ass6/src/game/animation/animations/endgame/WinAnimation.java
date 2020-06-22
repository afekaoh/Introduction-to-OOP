// ID 316044809
package game.animation.animations.endgame;

/**
 * The class Win animation.
 */
public class WinAnimation extends EndScreenAnimation {
    /**
     * Instantiates a new Win animation.
     *
     * @param score the score
     */
    public WinAnimation(final int score) {
        super(score);
    }

    @Override
    protected String getEndText() {
        return "You Win!";
    }
}
