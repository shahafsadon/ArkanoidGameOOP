package sprites;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Background for Level 2 - Night with moon and stars.
 */
public class BackgroundLevel2 implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {

        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);


        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(650, 100, 50);


        d.setColor(Color.WHITE);
        for (int i = 0; i < 50; i++) {
            int x = 50 + (i * 15) % 750;
            int y = 50 + (i * 35) % 500;
            d.fillCircle(x, y, 2);
        }
    }

    @Override
    public void timePassed() {

    }
}
