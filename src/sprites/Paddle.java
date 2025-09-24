package sprites;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

import collisions.Collidable;

/**
 * The Paddle class represents the player-controlled paddle.
 * It moves left and right according to key presses and reflects balls upon collision.
 */
public class Paddle implements Sprite, Collidable {
    private Rectangle rect;
    private Color color;
    private KeyboardSensor keyboard;
    private int speed;
    private int screenWidth;

    /**
     * Constructs a Paddle object.
     *
     * @param rect         the rectangle representing the paddle's shape.
     * @param color        the paddle's color.
     * @param keyboard     the KeyboardSensor for detecting user input.
     * @param speed        the speed (in pixels per frame) the paddle moves.
     * @param screenWidth  the width of the screen for circular movement logic.
     */
    public Paddle(Rectangle rect, Color color, KeyboardSensor keyboard, int speed, int screenWidth) {
        this.rect = rect;
        this.color = color;
        this.keyboard = keyboard;
        this.speed = speed;
        this.screenWidth = screenWidth;
    }

    /**
     * Moves the paddle to the left. Wraps to the right if it goes off-screen.
     */
    public void moveLeft() {
        double newX = rect.getUpperLeft().getX() - speed;
        if (newX + rect.getWidth() < 0) {
            newX = screenWidth;
        }
        this.rect = new Rectangle(new Point(newX, rect.getUpperLeft().getY()), rect.getWidth(), rect.getHeight());
    }

    /**
     * Moves the paddle to the right. Wraps to the left if it goes off-screen.
     */
    public void moveRight() {
        double newX = rect.getUpperLeft().getX() + speed;
        if (newX > screenWidth) {
            newX = -rect.getWidth();
        }
        this.rect = new Rectangle(new Point(newX, rect.getUpperLeft().getY()), rect.getWidth(), rect.getHeight());
    }

    /**
     * Called once per frame to update paddle movement based on key presses.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the screen.
     *
     * @param d the DrawSurface to draw on.
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
     * Returns the rectangle that defines the paddle's collision area.
     *
     * @return the collision rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Calculates the new velocity of the ball based on where it hits the paddle.
     *
     * @param hitter          the ball that hit the paddle (not used here).
     * @param collisionPoint  the point where the ball hit.
     * @param currentVelocity the ball's velocity before impact.
     * @return the new velocity after the hit.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double x = collisionPoint.getX();
        double paddleX = rect.getUpperLeft().getX();
        double regionWidth = rect.getWidth() / 5;

        // Determine region 1–5 based on where the hit occurred
        int region = (int) ((x - paddleX) / regionWidth) + 1;
        region = Math.min(5, Math.max(1, region)); // Clamp to 1–5

        double angle;
        switch (region) {
            case 1: angle = 300; break;
            case 2: angle = 330; break;
            case 3: return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            case 4: angle = 30; break;
            case 5: angle = 60; break;
            default: angle = 0; break;
        }
        return Velocity.fromAngleAndSpeed(angle, currentVelocity.getSpeed());
    }

    /**
     * Adds the paddle to the game (as both a sprite and a collidable).
     *
     * @param g the game instance to add the paddle to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
