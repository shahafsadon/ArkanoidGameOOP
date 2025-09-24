package sprites;

import biuoop.DrawSurface;
import java.util.List;
import java.util.ArrayList;

/**
 * A SpriteCollection holds and manages a list of Sprite objects.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Constructs an empty sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Removes a sprite from the collection.
     *
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        // Make a copy to avoid modification issues
        List<Sprite> copy = new ArrayList<>(sprites);
        for (Sprite s : copy) {
            s.timePassed();
        }
    }

    /**
     * Draws all sprites on the given DrawSurface.
     *
     * @param d the surface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}
