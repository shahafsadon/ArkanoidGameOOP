package sprites;

import biuoop.DrawSurface;
import game.LevelInformation;

import java.awt.Color;

/**
 * רקע לשלב הראשון – מצייר שמש, עננים וציפורים,
 * וגם מציג את שם השלב בצד שמאל למעלה.
 */
public class BackgroundLevel1 implements Sprite {
    private LevelInformation levelInfo;

    /**
     * בונה רקע עם מידע על השלב.
     *
     * @param levelInfo מידע השלב (שם, בלוקים וכו')
     */
    public BackgroundLevel1(LevelInformation levelInfo) {
        this.levelInfo = levelInfo;
    }

    @Override
    public void drawOn(DrawSurface d) {
        // רקע תכלת
        d.setColor(new Color(173, 216, 230));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        // שמש
        d.setColor(Color.YELLOW);
        d.fillCircle(80, 80, 60);

        // קרני שמש עדינות
        d.setColor(new Color(255, 255, 153));
        for (int i = 0; i < 20; i++) {
            d.drawLine(80, 80, 200 + i * 20, 200);
        }

        // עננים
        d.setColor(Color.WHITE);
        d.fillCircle(200, 100, 30);
        d.fillCircle(230, 100, 40);
        d.fillCircle(260, 100, 30);

        d.fillCircle(400, 120, 35);
        d.fillCircle(430, 110, 45);
        d.fillCircle(470, 120, 35);

        d.fillCircle(650, 90, 25);
        d.fillCircle(675, 85, 35);
        d.fillCircle(705, 90, 25);

        // ציפורים
        d.setColor(Color.BLACK);
        d.drawLine(600, 200, 610, 190);
        d.drawLine(610, 190, 620, 200);

        d.drawLine(640, 220, 650, 210);
        d.drawLine(650, 210, 660, 220);

        // שם השלב בצד שמאל למעלה
        d.setColor(Color.BLACK);
        d.drawText(20, 20, levelInfo.levelName(), 20);
    }

    @Override
    public void timePassed() {
        // לא נדרש
    }
}
