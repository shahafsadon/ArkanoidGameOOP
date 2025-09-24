package sprites;

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;

/**
 * Background for Level 3 - Final Boss stage with scary theme.
 */
public class BackgroundLevel3 implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {

        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);


        d.setColor(new Color(139, 0, 0));
        d.fillCircle(650, 80, 45);

        d.setColor(new Color(255, 69, 0));
        d.fillCircle(650, 80, 30);


        d.setColor(Color.WHITE);

        d.drawLine(50, 0, 90, 100);
        d.drawLine(90, 100, 60, 200);
        d.drawLine(60, 200, 80, 300);


        d.drawLine(730, 0, 750, 100);
        d.drawLine(750, 100, 720, 200);
        d.drawLine(720, 200, 740, 300);


        Random rand = new Random();
        d.setColor(Color.DARK_GRAY);
        for (int i = 0; i < 10; i++) {
            int x = rand.nextInt(800);
            int y = 350 + rand.nextInt(200);
            d.fillCircle(x, y, 40);
        }


        d.setColor(Color.RED);
        d.drawText(20, 20, "Final Boss", 20);
    }

    @Override
    public void timePassed() {

    }
}
