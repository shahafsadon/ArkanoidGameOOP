package game;

import biuoop.GUI;
import java.util.ArrayList;
import java.util.List;

public class Ass5Game {
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        GameFlow gameFlow = new GameFlow(gui);

        List<LevelInformation> levels = new ArrayList<>();
        levels.add(new Level1());
        levels.add(new Level2());
        levels.add(new Level3());

        gameFlow.runLevels(levels);
    }
}
