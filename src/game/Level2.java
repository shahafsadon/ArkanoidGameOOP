package game;

import blocks.Block;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Sprite;
import sprites.BackgroundLevel2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Level2 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(3, -3));
        velocities.add(new Velocity(-3, -3));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 6;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Moonlight";
    }

    @Override
    public Sprite getBackground() {
        return new BackgroundLevel2();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();

        int blockWidth = 50;
        int blockHeight = 20;
        int startX = 150;
        int startY = 200;


        for (int i = 0; i < 10; i++) {
            Block block = new Block(new Rectangle(new Point(startX + i * blockWidth, startY),
                    blockWidth, blockHeight), Color.CYAN);
            blocks.add(block);
        }


        for (int i = 0; i < 10; i++) {
            Block block = new Block(new Rectangle(new Point(startX + i * blockWidth, startY + blockHeight + 10),
                    blockWidth, blockHeight), Color.MAGENTA);
            blocks.add(block);
        }

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 20;
    }
}
