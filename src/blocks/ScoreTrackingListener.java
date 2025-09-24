package blocks;

import sprites.Ball;
import utils.Counter;

/**
 * The ScoreTrackingListener is responsible for updating the score
 * when a block is hit by a ball of a different color and removed.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a ScoreTrackingListener that tracks the current score.
     *
     * @param scoreCounter the score counter to be updated
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Called whenever a block is hit.
     * If the ball's color does not match the block's color,
     * the block is considered removed and 5 points are awarded.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (!beingHit.ballColorMatch(hitter)) {
            currentScore.increase(5);
        }
    }
}
