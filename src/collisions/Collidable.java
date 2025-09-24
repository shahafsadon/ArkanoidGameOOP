package collisions;
import geometry.Rectangle;
import geometry.Point;
import geometry.Velocity;
import sprites.Ball;

/**
 * The Collidable interface is used for objects that can be collided with.
 */
public interface Collidable {

    /**
     * Returns the shape of the object that can be collided with.
     *
     * @return the rectangle representing the collision area.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred at a certain point with a given velocity.
     *
     * @param hitter the ball that hit the object.
     * @param collisionPoint the point of collision.
     * @param currentVelocity the current velocity before collision.
     * @return the new velocity after the collision.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
