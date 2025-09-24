package sprites;

import biuoop.DrawSurface;
import utils.Counter;
import java.awt.Color;

/**
 * The ScoreIndicator displays the current score and level name.
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;
    private final String levelName;

    /**
     * Constructs a ScoreIndicator with a given score counter and level name.
     *
     * @param score the score counter to display
     * @param levelName the current level name
     */
    public ScoreIndicator(Counter score, String levelName) {
        this.score = score;
        this.levelName = levelName;
    }

    /**
     * Draws the score and level name at the top of the screen with a light gray background.
     *
     * @param d the drawing surface
     */
    @Override
    public void drawOn(DrawSurface d) {

        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, 800, 25);

        d.setColor(Color.BLACK);


        d.drawText(350, 18, "Score: " + score.getValue(), 16);


        d.drawText(650, 18, "Level: " + levelName, 16);
    }

    @Override
    public void timePassed() {

    }
}
