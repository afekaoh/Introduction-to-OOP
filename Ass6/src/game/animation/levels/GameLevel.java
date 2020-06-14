// ID 316044809
package game.animation.levels;


import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.animation.Animation;
import game.animation.AnimationRunner;
import game.animation.KeyPressStoppableAnimation;
import game.animation.animations.CountdownAnimation;
import game.animation.animations.PauseScreen;
import game.collections.ElementsCollection;
import game.collections.Sprite;
import game.elements.objects.Ball;
import game.elements.objects.EdgeBlock;
import game.elements.objects.Paddle;
import game.elements.objects.ScoreIndicator;
import game.elements.shapes.Point;
import game.elements.shapes.Rectangle;
import game.listeners.BallRemover;
import game.listeners.BlockRemover;
import game.listeners.HitListener;
import game.listeners.ScoreTrackingListener;
import game.tools.Counter;
import game.tools.GameSettings;

import java.awt.Color;
import java.util.stream.Stream;

/**
 * The class GameLevel.
 * the main class of the game itself creating and running all the elements of the game
 */
public class GameLevel implements Animation {

    /**
     * The constant START_HEIGHT.
     */
    public static final int START_HEIGHT = 20;
    /**
     * The Elements.
     */
    private final ElementsCollection elements;
    /**
     * The Animation runner.
     */
    private final AnimationRunner animationRunner;
    /**
     * The Width.
     */
    private final int width;
    /**
     * The Height.
     */
    private final int height;
    private final KeyboardSensor keyboard;
    private final LevelInformation levelInformation;
    private final Sprite background;
    /**
     * The Score.
     */
    private final Counter score;
    /**
     * The Remaining balls.
     */
    private Counter remainingBalls;
    /**
     * The Running.
     */
    private boolean running;
    /**
     * The Remaining blocks.
     */
    private Counter remainingBlocks;

    /**
     * Instantiates a new GameLevel.
     *
     * @param level           the level
     * @param keyboardSensor  the keyboard sensor
     * @param animationRunner the animation runner
     * @param score           the score
     */
    public GameLevel(LevelInformation level, final KeyboardSensor keyboardSensor,
                     final AnimationRunner animationRunner, final Counter score) {
        this.levelInformation = level;
        this.width = level.getWidth();
        this.height = level.getHeight();
        this.animationRunner = animationRunner;
        this.score = score;
        this.elements = new ElementsCollection();
        this.keyboard = keyboardSensor;
        this.background = level.getBackground();
    }

    /**
     * Initialize a new game: create all the component of the game
     * i.e Blocks, Balls and game.geometry.objects.Paddle add them to the game, and sta.
     */
    public void initialize() {
        remainingBlocks = new Counter(levelInformation.blocks().size());
        remainingBalls = new Counter(levelInformation.numberOfBalls());

        // creating the game blocks
        createBlocks();

        // creating the balls
        createBalls();

        //creating the edges blocks
        createEdges();

        // creating the paddle that the player will play with
        createPlayer();

        createScoreIndicator();

        // adding all the elements to the game
        elements.addAll();
    }

    /**
     * Create blocks for the game.
     */
    private void createBlocks() {
        HitListener blockRemover = new BlockRemover(elements, remainingBlocks);
        HitListener scoreListener = new ScoreTrackingListener(score);
        levelInformation.blocks().forEach(b -> {
            b.addHitListener(blockRemover);
            b.addHitListener(scoreListener);
            elements.addElement(b);
        });
    }

    /**
     * Create the  balls.
     */
    private void createBalls() {
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            elements.addElement(
                    new Ball(
                            levelInformation.initialBallLocation().get(i),
                            3,
                            Color.decode("#e9c46a"),
                            elements.getEnvironment(),
                            levelInformation.initialBallVelocities().get(i)
                    ));
        }
    }

    /**
     * Create the edges of the screen.
     */
    private void createEdges() {
        HitListener ballRemover = new BallRemover(elements, remainingBalls);
        final int edgeSize = 20;
        final int deathRegion = height - ((int) (height * 0.03) - 2);
        Stream.of(
                //left edge
                new EdgeBlock(new Point(-edgeSize, 0), edgeSize, height, 0),
                //right edge
                new EdgeBlock(new Point(width, 0), edgeSize, height, 0),
                //bottom edge
                new EdgeBlock(new Point(0, deathRegion), width, edgeSize, 0, ballRemover),
                //top edge
                new EdgeBlock(new Point(0, -edgeSize), width, edgeSize + GameLevel.START_HEIGHT, 0)
                 )
              // adding the blocks to the element collections
              .forEach(elements::addElement);
    }

    /**
     * Create the paddle which the player will play with.
     */
    private void createPlayer() {
        final GameSettings settings = new GameSettings(
                elements.getEnvironment(),
                keyboard
        );
        elements.addElement(new Paddle(
                width / 2,
                height - levelInformation.paddleHeight() - 2,
                levelInformation.paddleWidth(),
                levelInformation.paddleHeight(),
                levelInformation.paddleSpeed(),
                settings
        ));
    }


    /**
     * Create the score indicator.
     */
    private void createScoreIndicator() {
        ScoreIndicator indicator = new ScoreIndicator(
                new Rectangle(
                        new Point(0, 0),
                        width,
                        GameLevel.START_HEIGHT
                ),
                levelInformation.levelName(),
                score,
                remainingBalls
        );
        elements.addElement(indicator);
    }

    /**
     * Run.
     */
    public void run() {
        // countdown before turn starts.
        this.animationRunner.run(new CountdownAnimation(3, 2, background, elements.getSprites()));
        // running the level
        this.running = true;

        animationRunner.run(this);
    }

    @Override
    public boolean shouldStop() {
        return !running;
    }

    @Override
    public void doOneFrame(DrawSurface canvas) {
        if (keyboard.isPressed("p")) {
            animationRunner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
        elements.runSprites(canvas);
        if (remainingBlocks.getValue() == 0) {
            score.increase(100);
            running = false;
        } else if (remainingBalls.getValue() == 0) {
            running = false;
        }
    }

    @Override
    public void drawBackground(final DrawSurface canvas) {
        this.background.drawOn(canvas);
    }

    public boolean isDead() {
        return remainingBalls.getValue() == 0;
    }
}

