package sprites;

import blocks.Block;
import blocks.HitListener;
import game.GameLevel;
import utils.Counter;

/**
 * BallRemover is a HitListener that removes balls when they hit the designated
 * "death region" block and updates the count of remaining balls.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructs a BallRemover with a game reference and a counter for tracking remaining balls.
     *
     * @param game the game instance from which balls should be removed
     * @param remainingBalls a counter that tracks how many balls are still active in the game
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Called when a ball hits a block. If the block is the "death region",
     * the ball is removed from the game and the remaining ball count is decreased.
     *
     * @param beingHit the block that was hit (expected to be the death region)
     * @param hitter the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // Remove the ball from both the sprite list and collidable list (if applicable)
        game.removeSprite(hitter);

        // Update the counter
        remainingBalls.decrease(1);
    }
}
