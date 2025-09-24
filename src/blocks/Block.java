package blocks;

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import collisions.Collidable;
import sprites.Sprite;
import sprites.Ball;

/**
 * The Block class represents a game object that can be collided with (Collidable),
 * drawn (Sprite), and notifies registered listeners about hit events (HitNotifier).
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private Color color;
    private boolean isDeathRegion;
    private List<HitListener> hitListeners;

    /**
     * Constructs a Block with a rectangle and color (not a death region).
     *
     * @param rect  the rectangle shape of the block.
     * @param color the color to fill the block.
     */
    public Block(Rectangle rect, Color color) {
        this(rect, color, false);
    }

    /**
     * Constructs a Block with a rectangle and default gray color (not a death region).
     *
     * @param rect the rectangle shape of the block.
     */
    public Block(Rectangle rect) {
        this(rect, Color.GRAY, false);
    }

    /**
     * Constructs a Block with full configuration.
     *
     * @param rect          the rectangle shape of the block.
     * @param color         the fill color.
     * @param isDeathRegion whether the block represents the "death region".
     */
    public Block(Rectangle rect, Color color, boolean isDeathRegion) {
        this.rect = rect;
        this.color = color;
        this.isDeathRegion = isDeathRegion;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Returns the collision rectangle of the block.
     *
     * @return the block's rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Handles the result of a collision with the block:
     * flips velocity direction and notifies listeners if appropriate.
     *
     * @param hitter          the ball that hit the block.
     * @param collisionPoint  the collision location.
     * @param currentVelocity the velocity before collision.
     * @return the updated velocity.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        boolean hitVertical = false;
        boolean hitHorizontal = false;

        double x = collisionPoint.getX();
        double y = collisionPoint.getY();
        double left = rect.getUpperLeft().getX();
        double right = left + rect.getWidth();
        double top = rect.getUpperLeft().getY();
        double bottom = top + rect.getHeight();

        if (Math.abs(x - left) < 0.01 || Math.abs(x - right) < 0.01) {
            hitVertical = true;
        }
        if (Math.abs(y - top) < 0.01 || Math.abs(y - bottom) < 0.01) {
            hitHorizontal = true;
        }

        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        if (hitVertical) {
            dx = -dx;
        }
        if (hitHorizontal) {
            dy = -dy;
        }

        // Notify only if it's a game block or death region
        if (shouldNotifyHit(hitter)) {
            notifyHit(hitter);
        }

        // Update ball color if it's a real block (not death region or wall)
        if (!isDeathRegion && !isVisualBorderBlock()) {
            hitter.setColor(this.color);
        }

        return new Velocity(dx, dy);
    }

    /**
     * Determines if this block should notify listeners on hit.
     * Only blocks that are not visual borders, or are death region blocks, will notify.
     *
     * @param hitter the ball hitting this block.
     * @return true if should notify, false otherwise.
     */
    private boolean shouldNotifyHit(Ball hitter) {
        return this.isDeathRegion || (!this.isVisualBorderBlock() && !this.ballColorMatch(hitter));
    }

    /**
     * Identifies whether the block is a non-notifying border (left/right/top wall).
     *
     * @return true if the block is a visual border, false otherwise.
     */
    private boolean isVisualBorderBlock() {
        double x = this.rect.getUpperLeft().getX();
        double y = this.rect.getUpperLeft().getY();
        return !this.isDeathRegion && (y == 25 || x == 0 || x >= 780);
    }

    /**
     * Checks if the color of the ball matches the block.
     *
     * @param ball the ball to compare.
     * @return true if colors match, false otherwise.
     */
    public boolean ballColorMatch(Ball ball) {
        return this.color.equals(ball.getColor());
    }

    /**
     * Draws the block on the provided surface.
     *
     * @param d the drawing surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        int x = (int) rect.getUpperLeft().getX();
        int y = (int) rect.getUpperLeft().getY();
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();

        d.setColor(this.color);
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, width, height);
    }

    /**
     * This block does not change over time.
     */
    @Override
    public void timePassed() {
        // No action needed
    }

    /**
     * Adds this block to the given game as both a Sprite and Collidable.
     *
     * @param g the game to add to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Removes this block from the given game.
     *
     * @param g the game to remove from.
     */
    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }

    /**
     * Notifies all listeners that a hit occurred.
     *
     * @param hitter the ball that hit this block.
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Registers a new listener to hit events.
     *
     * @param hl the listener to add.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Removes a listener from hit notifications.
     *
     * @param hl the listener to remove.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Gets the block's color.
     *
     * @return the color.
     */
    public Color getColor() {
        return this.color;
    }
}
