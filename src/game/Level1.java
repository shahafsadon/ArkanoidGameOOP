package game;

import sprites.Sprite;
import sprites.BackgroundLevel1;
import geometry.Velocity;
import blocks.Block;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Level1 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(-30, 5));
        velocities.add(Velocity.fromAngleAndSpeed(30, 5));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 120;
    }

    @Override
    public String levelName() {
        return "Sunny Day";
    }

    @Override
    public Sprite getBackground() {
        return new BackgroundLevel1(this);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int blockWidth = 50;
        int blockHeight = 20;
        int startX = 50;
        int y = 300;

        Color[] colors = {
                Color.PINK, Color.ORANGE, Color.YELLOW,
                new Color(255, 182, 193), // LightPink
                new Color(255, 228, 181)  // Moccasin
        };

        for (int i = 0; i < 12; i++) {
            Color c = colors[i % colors.length];
            Block block = new Block(new Rectangle(new Point(startX + i * blockWidth, y), blockWidth, blockHeight), c);
            blocks.add(block);
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 12;
    }
}
