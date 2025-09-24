package sprites;

import biuoop.DrawSurface;
import utils.Counter;

/**
 * The LivesIndicator used to display the number of lives, but now intentionally left empty.
 */
public class LivesIndicator implements Sprite {
    private Counter lives;

    /**
     * Constructs a LivesIndicator (inactive version).
     *
     * @param lives the counter tracking player lives
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
    }

    /**
     * No longer draws anything on the screen.
     *
     * @param d the drawing surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        // Intentionally does nothing — hidden indicator
    }

    /**
     * Does nothing each frame.
     */
    @Override
    public void timePassed() {
        // No changes per frame needed
    }
}
