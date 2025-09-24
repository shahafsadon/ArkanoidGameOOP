package game;

import biuoop.GUI;
import java.util.List;
import utils.Counter;

public class GameFlow {
    private GUI gui;
    private Counter score;
    private Counter lives;

    public GameFlow(GUI gui) {
        this.gui = gui;
        this.score = new Counter();
        this.lives = new Counter();
        this.lives.increase(3);
    }

    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, gui, this.score, this.lives);

            level.initialize();
            boolean finished = level.run();


            if (!finished || level.isPlayerDead()) {
                System.out.println("Game Over! Your score: " + score.getValue());
                gui.close();
                return;
            }
        }

        System.out.println("You Win! Final score: " + score.getValue());
        gui.close();
    }

}
