package game;

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

import sprites.Ball;
import sprites.BallRemover;
import sprites.Paddle;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

import collisions.Collidable;
import collisions.GameEnvironment;

import blocks.Block;
import blocks.BlockRemover;
import blocks.ScoreTrackingListener;

import utils.Counter;

public class GameLevel {
    private LevelInformation levelInfo;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private KeyboardSensor keyboard;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter lives;
    private Counter score;

    public GameLevel(LevelInformation levelInfo, GUI gui, Counter score, Counter lives) {
        this.levelInfo = levelInfo;
        this.gui = gui;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.score = score;
        this.lives = lives;
        this.keyboard = gui.getKeyboardSensor();
    }

    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    public void initialize() {
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(this.score);
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);


        this.addSprite(levelInfo.getBackground());


        List<Velocity> ballVelocities = levelInfo.initialBallVelocities();
        for (int i = 0; i < levelInfo.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(400, 400), 5, Color.WHITE);
            ball.setVelocity(ballVelocities.get(i));
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
            this.remainingBalls.increase(1);
        }


        Rectangle paddleRect = new Rectangle(new Point(350, 560), levelInfo.paddleWidth(), 20);
        Paddle paddle = new Paddle(paddleRect, Color.ORANGE, keyboard, levelInfo.paddleSpeed(), 800);
        paddle.addToGame(this);


        Block top = new Block(new Rectangle(new Point(0, 0), 800, 25), Color.GRAY);
        Block left = new Block(new Rectangle(new Point(0, 25), 20, 575), Color.GRAY);
        Block right = new Block(new Rectangle(new Point(780, 25), 20, 575), Color.GRAY);
        for (Block wall : new Block[]{top, left, right}) {
            wall.addToGame(this);
        }


        Block deathBlock = new Block(new Rectangle(new Point(0, 580), 800, 20), Color.GRAY, true);
        deathBlock.addHitListener(ballRemover);
        deathBlock.addToGame(this);


        for (Block block : levelInfo.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTracker);
            this.remainingBlocks.increase(1);
        }


        ScoreIndicator scoreDisplay = new ScoreIndicator(this.score, levelInfo.levelName());
        this.addSprite(scoreDisplay);


        this.lives.increase(3);
    }

    public boolean run() {
        Sleeper sleeper = new Sleeper();
        int fps = 60;
        int msPerFrame = 1000 / fps;

        while (true) {
            long start = System.currentTimeMillis();

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();


            if (remainingBlocks.getValue() == 0) {
                score.increase(100);
                System.out.println("שלב הסתיים! ניקוד: " + score.getValue());
                return true;
            }


            if (remainingBalls.getValue() == 0) {
                lives.decrease(1);
                if (lives.getValue() == 0) {
                    System.out.println("Game Over. ניקוד: " + score.getValue());
                    return false;
                } else {
                    return false;
                }
            }

            long used = System.currentTimeMillis() - start;
            long leftToSleep = msPerFrame - used;
            if (leftToSleep > 0) {
                sleeper.sleepFor(leftToSleep);
            }
        }
    }

    public boolean isPlayerDead() {
        return this.lives.getValue() <= 0;
    }

    public Counter getScore() {
        return this.score;
    }

    public boolean isGameOver() {
        return lives.getValue() <= 0;
    }
}
