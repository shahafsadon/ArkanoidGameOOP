package sprites;

import biuoop.DrawSurface;

/**
 * A Sprite is a game object that can be drawn and can change over time.
 */
public interface Sprite {

    /**
     * Draws the sprite on the given surface.
     *
     * @param d the surface to draw the sprite on.
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that a unit of time has passed.
     */
    void timePassed();
}
