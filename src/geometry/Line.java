package geometry;
import java.util.List;
/**
 * A class representing a line segment between two points.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Constructor using two points.
     *
     * @param start the starting point
     * @param end   the ending point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor using coordinates.
     *
     * @param x1 x of start point
     * @param y1 y of start point
     * @param x2 x of end point
     * @param y2 y of end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point
     */
    public Point end() {
        return this.end;
    }

    /**
     * Returns the length of the line.
     *
     * @return the distance between start and end
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the midpoint between start and end
     */
    public Point middle() {
        double midX = (start.getX() + end.getX()) / 2.0;
        double midY = (start.getY() + end.getY()) / 2.0;
        return new Point(midX, midY);
    }

    /**
     * Checks if two lines are equal (regardless of point order).
     *
     * @param other the other line
     * @return true if the lines are visually equal
     */
    public boolean equals(Line other) {
        if (other == null) {
            return false;
        }
        return (this.start.equals(other.start()) && this.end.equals(other.end()))
                || (this.start.equals(other.end()) && this.end.equals(other.start()));
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other the other line
     * @return true if the two lines intersect
     */
    public boolean isIntersecting(Line other) {
        Point p1 = this.start;
        Point q1 = this.end;
        Point p2 = other.start();
        Point q2 = other.end();

        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4) {
            return true;
        }

        if (o1 == 0 && onSegment(p1, p2, q1)) {
            return true;
        }
        if (o2 == 0 && onSegment(p1, q2, q1)) {
            return true;
        }
        if (o3 == 0 && onSegment(p2, p1, q2)) {
            return true;
        }
        if (o4 == 0 && onSegment(p2, q1, q2)) {
            return true;
        }

        return false;
    }

    /**
     * Returns true if both given lines intersect with this line.
     *
     * @param other1 the first line
     * @param other2 the second line
     * @return true if both lines intersect with this line
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return this.isIntersecting(other1) && this.isIntersecting(other2);
    }

    /**
     * Returns the intersection point if the lines intersect,
     * and null otherwise.
     *
     * @param other the other line
     * @return the intersection point, or null if none
     */
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }

        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start().getX();
        double y3 = other.start().getY();
        double x4 = other.end().getX();
        double y4 = other.end().getY();

        double denom = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        if (Math.abs(denom) < Point.EPSILON) {
            return null;
        }

        double px = ((x1 * y2 - y1 * x2) * (x3 - x4)
                - (x1 - x2) * (x3 * y4 - y3 * x4)) / denom;
        double py = ((x1 * y2 - y1 * x2) * (y3 - y4)
                - (y1 - y2) * (x3 * y4 - y3 * x4)) / denom;

        return new Point(px, py);
    }

    /**
     * Returns the orientation of the triplet (p, q, r).
     *
     * @param p first point
     * @param q second point
     * @param r third point
     * @return 0 if colinear, 1 if clockwise, 2 if counterclockwise
     */
    private int orientation(Point p, Point q, Point r) {
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX())
                - (q.getX() - p.getX()) * (r.getY() - q.getY());

        if (Math.abs(val) < Point.EPSILON) {
            return 0;
        }
        return (val > 0) ? 1 : 2;
    }

    /**
     * Checks if point q lies on segment pr.
     *
     * @param p start of the segment
     * @param q point to check
     * @param r end of the segment
     * @return true if q lies on segment pr
     */
    private boolean onSegment(Point p, Point q, Point r) {
        return q.getX() <= Math.max(p.getX(), r.getX()) + Point.EPSILON
                && q.getX() >= Math.min(p.getX(), r.getX()) - Point.EPSILON
                && q.getY() <= Math.max(p.getY(), r.getY()) + Point.EPSILON
                && q.getY() >= Math.min(p.getY(), r.getY()) - Point.EPSILON;
    }
    /**
     * Returns the closest intersection point to the start of the line with a given rectangle.
     *
     * @param rect the rectangle to check for intersections with.
     * @return the closest intersection point, or null if there is no intersection.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersections = rect.intersectionPoints(this);
        if (intersections.isEmpty()) {
            return null;
        }

        Point closest = intersections.get(0);
        double minDistance = this.start().distance(closest);

        for (Point p : intersections) {
            double distance = this.start().distance(p);
            if (distance < minDistance) {
                minDistance = distance;
                closest = p;
            }
        }

        return closest;
    }

}
