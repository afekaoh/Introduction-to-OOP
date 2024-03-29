// ID 316044809
package game.listeners;

import game.elements.objects.Ball;
import game.elements.objects.Block;

/**
 * The interface Hit listener.
 */
public interface HitListener {

    /**
     * Hit event.
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}
