package geometry;
/**
 * A class representing a point in 2D space.
 */
public class Point {
    /** Comparison threshold for equality of doubles. */
    public static final double EPSILON = 0.00001;

    private double x;
    private double y;

    /**
     * Constructor for a Point object.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x value of this point.
     *
     * @return the x-coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y value of this point.
     *
     * @return the y-coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other the other point
     * @return the distance between the points
     */
    public double distance(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Checks if this point is equal to another point, considering a small margin of error (epsilon).
     *
     * @param other the other point
     * @return true if both points are (almost) equal
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return Math.abs(this.x - other.x) < EPSILON
                && Math.abs(this.y - other.y) < EPSILON;
    }
}
