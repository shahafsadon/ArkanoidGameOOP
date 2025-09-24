package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an axis-aligned rectangle, defined by upper-left point, width, and height.
 * Supports collision detection via intersection points with a line.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructs a rectangle given the top-left point, width, and height.
     *
     * @param upperLeft the upper-left corner.
     * @param width     the width.
     * @param height    the height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns a list of intersection points with a given line.
     * May return 0–2 points depending on how the line intersects the rectangle.
     *
     * @param line the line to check.
     * @return list of intersection points.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<>();

        // Define the four corners
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point lowerRight = new Point(upperRight.getX(), lowerLeft.getY());

        // Define the edges
        Line top = new Line(upperLeft, upperRight);
        Line bottom = new Line(lowerLeft, lowerRight);
        Line left = new Line(upperLeft, lowerLeft);
        Line right = new Line(upperRight, lowerRight);

        // Check intersections
        for (Line edge : new Line[]{top, bottom, left, right}) {
            Point p = line.intersectionWith(edge);
            if (p != null && !intersections.contains(p)) {
                intersections.add(p);
            }
        }

        return intersections;
    }

    /**
     * @return the width.
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return the height.
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return the top-left point.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * @return the bottom-right point (optional utility).
     */
    public Point getBottomRight() {
        return new Point(upperLeft.getX() + width, upperLeft.getY() + height);
    }

    /**
     * @return the center of the rectangle (optional utility).
     */
    public Point getCenter() {
        return new Point(upperLeft.getX() + width / 2, upperLeft.getY() + height / 2);
    }
}
