// ID 316044809
package game.listeners;


import game.geometry.objects.Ball;
import game.geometry.objects.Block;
import game.tools.Counter;

public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(final Block beingHit, final Ball hitter) {
        currentScore.increase(beingHit.isDead() ? 10 : 5);
    }
}
