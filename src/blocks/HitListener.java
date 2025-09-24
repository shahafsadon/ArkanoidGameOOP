package blocks;

import sprites.Ball;

/**
 * HitListener is notified whenever a hit() occurs on a Block.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the block that was hit.
     * @param hitter   the ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
