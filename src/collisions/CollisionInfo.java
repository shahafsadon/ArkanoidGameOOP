package collisions;
import geometry.Point;
/**
 * CollisionInfo holds the details about a collision — the point and the object involved.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructs a CollisionInfo with a collision point and a collidable object.
     *
     * @param collisionPoint the point where the collision occurred.
     * @param collisionObject the object that was hit.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return the collision point.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the object collided with.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}
