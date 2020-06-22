// ID 316044809
package game.animation.animations.endgame;

/**
 * The class Game over animation.
 */
public class GameOverAnimation extends EndScreenAnimation {
    /**
     * Instantiates a new Game over animation.
     *
     * @param score the score
     */
    public GameOverAnimation(final int score) {
        super(score);
    }

    @Override
    protected String getEndText() {
        return "Game Over.";
    }
}
