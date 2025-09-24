package sprites;

import biuoop.DrawSurface;
import java.awt.Color;

import geometry.Point;
import geometry.Velocity;
import geometry.Line;

import collisions.CollisionInfo;
import collisions.GameEnvironment;

import game.GameLevel;

/**
 * The Ball class represents a circle that can be drawn and animated.
 * It moves with velocity, can bounce inside a defined frame,
 * react to a forbidden area as a bouncing wall, and interact with Collidable objects.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;

    private int minX = 0, minY = 0, maxX = 800, maxY = 600;
    private boolean hasForbiddenRect = false;
    private int fx1, fy1, fx2, fy2;

    private GameEnvironment environment;

    /**
     * Sets the game environment that the ball interacts with.
     *
     * @param env the GameEnvironment to use for collision detection.
     */
    public void setGameEnvironment(GameEnvironment env) {
        this.environment = env;
    }

    /**
     * Constructs a Ball with a center, radius, and color.
     *
     * @param center center point
     * @param r      radius of the ball
     * @param color  color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = Math.max(1, r);
        this.color = (color != null) ? color : Color.BLACK;
    }

    /**
     * Constructs a Ball with center coordinates, radius, and color.
     *
     * @param x     x-coordinate
     * @param y     y-coordinate
     * @param r     radius
     * @param color color
     */
    public Ball(int x, int y, int r, Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * Gets the x-coordinate of the center.
     *
     * @return x-coordinate
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets the y-coordinate of the center.
     *
     * @return y-coordinate
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets the radius of the ball.
     *
     * @return radius
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Gets the color of the ball.
     *
     * @return color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets the color of the ball.
     *
     * @param color the new color to assign to the ball.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Draws the ball on the provided surface.
     *
     * @param surface DrawSurface to draw on
     */
    public void drawOn(DrawSurface surface) {
        if (surface != null) {
            surface.setColor(this.color);
            surface.fillCircle(getX(), getY(), this.radius);
        }
    }

    /**
     * Sets the ball's velocity.
     *
     * @param v velocity to assign
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity by dx and dy values.
     *
     * @param dx delta x
     * @param dy delta y
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets the velocity.
     *
     * @return current velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Defines the frame boundaries where the ball is allowed to move.
     *
     * @param minX left boundary
     * @param minY top boundary
     * @param maxX right boundary
     * @param maxY bottom boundary
     */
    public void setFrame(int minX, int minY, int maxX, int maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    /**
     * Defines a forbidden area that the ball should bounce off.
     *
     * @param x1 top-left x
     * @param y1 top-left y
     * @param x2 bottom-right x
     * @param y2 bottom-right y
     */
    public void setForbiddenArea(int x1, int y1, int x2, int y2) {
        this.fx1 = x1;
        this.fy1 = y1;
        this.fx2 = x2;
        this.fy2 = y2;
        this.hasForbiddenRect = true;
    }

    /**
     * Moves the ball one step forward.
     * Considers collisions with walls, forbidden area, and GameEnvironment.
     */
    public void moveOneStep() {
        if (this.velocity == null) {
            return;
        }

        if (this.environment != null) {
            Line trajectory = new Line(this.center, this.velocity.applyToPoint(this.center));
            CollisionInfo info = this.environment.getClosestCollision(trajectory);

            if (info != null) {
                Point collisionPoint = info.collisionPoint();
                double dx = velocity.getDx();
                double dy = velocity.getDy();
                double epsilon = 0.5;

                this.center = new Point(
                        collisionPoint.getX() - dx * epsilon,
                        collisionPoint.getY() - dy * epsilon
                );

                this.velocity = info.collisionObject().hit(this, collisionPoint, this.velocity);
                return;
            }
        }

        Point next = this.velocity.applyToPoint(this.center);
        double nx = next.getX();
        double ny = next.getY();

        if (nx - radius <= minX || nx + radius >= maxX) {
            this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy());
        }
        if (ny - radius <= minY || ny + radius >= maxY) {
            this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy());
        }

        next = this.velocity.applyToPoint(this.center);
        nx = next.getX();
        ny = next.getY();

        if (hasForbiddenRect) {
            boolean nextInside = nx + radius > fx1 && nx - radius < fx2
                    && ny + radius > fy1 && ny - radius < fy2;
            boolean currentlyOutside = center.getX() + radius <= fx1
                    || center.getX() - radius >= fx2
                    || center.getY() + radius <= fy1
                    || center.getY() - radius >= fy2;

            if (nextInside && currentlyOutside) {
                if (nx + radius > fx1 && nx - radius < fx2) {
                    this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy());
                }
                if (ny + radius > fy1 && ny - radius < fy2) {
                    this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy());
                }
            }
        }

        this.center = this.velocity.applyToPoint(this.center);
    }

    /**
     * Notifies the ball that time has passed.
     * This method is part of the Sprite interface and is called once per frame.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Adds the ball to the game (as a sprite).
     *
     * @param g the game to add this ball to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
