// ID 316044809
package game.listeners;


import game.elements.objects.Ball;
import game.elements.objects.Block;
import game.tools.Counter;

/**
 * The class Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    /**
     * The constant HIT_SCORE.
     */
    public static final int HIT_SCORE = 5;
    /**
     * The constant BRAKE_SCORE.
     */
    public static final int KILL_SCORE = 10;
    /**
     * The Current score.
     */
    private final Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(final Block beingHit, final Ball hitter) {
        currentScore.increase(beingHit.isDead() ? KILL_SCORE : HIT_SCORE);
    }
}
