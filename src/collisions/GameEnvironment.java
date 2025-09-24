package collisions;

import geometry.Line;
import geometry.Point;

import java.util.List;
import java.util.ArrayList;

/**
 * The GameEnvironment class holds all the collidable objects in the game.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Constructs a GameEnvironment with an empty list of collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Adds a collidable object to the environment.
     *
     * @param c the collidable to add.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Removes a collidable object from the environment.
     *
     * @param c the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Determines the closest collision that will occur based on a trajectory.
     *
     * @param trajectory the line representing the object's path.
     * @return information about the closest collision, or null if no collision is detected.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestPoint = null;
        Collidable closestObject = null;
        double minDistance = Double.POSITIVE_INFINITY;

        for (Collidable c : collidables) {
            Point p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (p != null) {
                double distance = trajectory.start().distance(p);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestPoint = p;
                    closestObject = c;
                }
            }
        }

        if (closestPoint == null) {
            return null;
        }

        return new CollisionInfo(closestPoint, closestObject);
    }
}
