package game;

import blocks.Block;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Sprite;
import sprites.BackgroundLevel3;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Level3 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(4, -4));
        velocities.add(new Velocity(-4, -4));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 70;
    }

    @Override
    public String levelName() {
        return "Final Boss";
    }

    @Override
    public Sprite getBackground() {
        return new BackgroundLevel3();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();

        int blockWidth = 50;
        int blockHeight = 20;
        int startX = 100;
        int startY = 150;


        Color[] rowColors = {Color.RED, new Color(128, 0, 128), Color.DARK_GRAY};

        for (int row = 0; row < 3; row++) {
            Color color = rowColors[row];
            for (int i = 0; i < 12; i++) {
                Block block = new Block(
                        new Rectangle(new Point(startX + i * blockWidth, startY + row * (blockHeight + 5)),
                                blockWidth, blockHeight),
                        color
                );
                blocks.add(block);
            }
        }

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 36;
    }
}
