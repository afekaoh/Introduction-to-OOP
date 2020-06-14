// ID 316044809
package game.animation.animations.endgame;

public class GameOverAnimation extends EndScreenAnimation {
    public GameOverAnimation(final int score) {
        super(score);
    }

    @Override
    protected String getEndText() {
        return "Game Over.";
    }
}
