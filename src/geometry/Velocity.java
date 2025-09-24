package geometry;
/**
 * Velocity specifies the change in position on the x and y axes.
 * This class supports creating velocities from components (dx, dy)
 * or from angle and speed using trigonometry.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Creates a velocity with given dx and dy.
     *
     * @param dx change in x-axis
     * @param dy change in y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @return the dx component of the velocity
     */
    public double getDx() {
        return dx;
    }

    /**
     * @return the dy component of the velocity
     */
    public double getDy() {
        return dy;
    }

    /**
     * Applies this velocity to a given point.
     *
     * @param p the original point
     * @return a new point after applying velocity
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Creates a velocity instance from angle and speed.
     * Angle is measured in degrees, where 0 is upward and increases clockwise.
     *
     * @param angle the direction of movement (degrees)
     * @param speed the total speed (magnitude)
     * @return a Velocity object representing this angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        double dx = speed * Math.sin(radians);
        double dy = -speed * Math.cos(radians); // Negative because y increases downward
        return new Velocity(dx, dy);
    }

    /**
     * Checks if this velocity is approximately equal to another velocity,
     * using EPSILON to compare double values.
     *
     * @param other the other velocity
     * @return true if both dx and dy are approximately equal
     */
    public boolean equals(Velocity other) {
        if (other == null) {
            return false;
        }
        return Math.abs(this.dx - other.dx) < Point.EPSILON
                && Math.abs(this.dy - other.dy) < Point.EPSILON;
    }
    /**
     * Returns the magnitude (speed) of the velocity vector.
     *
     * @return the speed (sqrt(dx^2 + dy^2))
     */
    public double getSpeed() {
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }
}
