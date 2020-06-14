// ID 316044809
package game.animation.animations.endgame;

public class WinAnimation extends EndScreenAnimation {
    public WinAnimation(final int score) {
        super(score);
    }

    @Override
    protected String getEndText() {
        return "You Win!";
    }
}
