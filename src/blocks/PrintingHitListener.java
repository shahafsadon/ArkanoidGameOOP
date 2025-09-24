package blocks;

import sprites.Ball;

/**
 * A simple listener that prints a message to the console whenever a block is hit.
 * Used for testing the hit notification system.
 */
public class PrintingHitListener implements HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * It prints a message to the console.
     *
     * @param beingHit the block that was hit.
     * @param hitter   the ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
