package blocks;

import sprites.Ball;
import game.GameLevel;
import utils.Counter;

/**
 * A BlockRemover is a HitListener that removes blocks from the game,
 * and keeps track of the remaining number of blocks using a Counter.
 * This class ensures all listeners are notified before removing the block.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructs a BlockRemover with references to the game and the block counter.
     *
     * @param game           the game where blocks are removed from
     * @param remainingBlocks the counter tracking remaining blocks
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Called whenever a block is hit. If the hit was with a mismatched ball color,
     * the block is removed from the game after all listeners are notified.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (!beingHit.ballColorMatch(hitter)) {
            // Let all other listeners (like ScoreTrackingListener) process the event first
            remainingBlocks.decrease(1);
            beingHit.removeFromGame(this.game);
        }
    }
}
