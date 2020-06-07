// ID 316044809
package game.levels;


import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.animation.Animation;
import game.animation.AnimationRunner;
import game.animation.states.PauseScreen;
import game.collections.ElementsCollection;
import game.elements.objects.Ball;
import game.elements.objects.Block;
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
    /**
     * The Remaining balls.
     */
    private Counter remainingBalls;
    /**
     * The Score.
     */
    private Counter score;
    /**
     * The Running.
     */
    private boolean running;
    /**
     * The Remaining blocks.
     */
    private Counter remainingBlocks;
    private KeyboardSensor keyboard;

    /**
     * Instantiates a new GameLevel.
     *
     * @param width  the width of the game
     * @param height the height of the game
     * @param title  the title of the game
     */
    public GameLevel(final int width, final int height, final String title) {
        this.width = width;
        this.height = height;
        this.animationRunner = new AnimationRunner(width, height, title, 60, Color.decode("#F6F3E3"));
        this.elements = new ElementsCollection();
        this.keyboard = animationRunner.getKeyboardSensor();

    }

    /**
     * Initialize a new game: create all the component of the game
     * i.e Blocks, Balls and game.geometry.objects.Paddle add them to the game, and sta.
     */
    public void initialize() {

        createScoreIndicator();

        // creating the game blocks
        createBlocks(5, 10, width / 4, width / 9, (width - width / 4) / 10, (int) (height * 0.05));

        // creating the balls
        final int numOfBalls = 3;
        createBalls(numOfBalls);

        //creating the edges blocks
        createEdges();

        // creating the paddle that the player will play with
        createPlayer();

        // adding all the elements to the game
        elements.addAll();
    }

    /**
     * Create the score indicator.
     */
    private void createScoreIndicator() {
        score = new Counter();
        ScoreIndicator indicator = new ScoreIndicator(
                new Rectangle(
                        new Point(0, 0),
                        width,
                        GameLevel.START_HEIGHT
                ),
                score
        );
        elements.addElement(indicator);
    }

    /**
     * Create blocks for the game.
     *
     * @param numOfRows    the num of rows
     * @param blocksPerRow the blocks per row
     * @param startX       the start x
     * @param startY       the start y
     * @param blockWidth   the block width
     * @param blockHeight  the block height
     */
    private void createBlocks(final int numOfRows, final int blocksPerRow, final int startX, final int startY,
                              final int blockWidth, final int blockHeight) {
        final int numOfBlocks = numOfRows * blocksPerRow;
        remainingBlocks = new Counter(numOfBlocks);
        HitListener blockRemover = new BlockRemover(this.elements, remainingBlocks);
        HitListener scoreTracking = new ScoreTrackingListener(score);
        for (int row = 0; row < numOfRows; row++) {
            for (int column = blocksPerRow - 1; column >= row; column--) {
                final Block block = new Block(
                        new Point(column * blockWidth + startX, row * blockHeight + startY + GameLevel.START_HEIGHT),
                        blockWidth,
                        blockHeight,
                        numOfRows - 1 - row,
                        blockRemover,
                        scoreTracking
                );
                elements.addElement(block);
            }
        }
    }

    /**
     * Create the  balls.
     *
     * @param numOfBalls the num of balls
     */
    private void createBalls(final int numOfBalls) {
        // creating the balls
        for (int i = 0; i < numOfBalls; i++) {
            elements.addElement(new Ball(
                    new Point(width / (i + 2), 3 * height / 4),
                    3,
                    Color.decode("#e9c46a"),
                    elements.getEnvironment()
            ));
            remainingBalls = new Counter(numOfBalls);

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
        final int paddleHeight = (int) (height * 0.03);
        // the paddle is extra length to better see region interaction
        final int paddleWidth = width / 8;
        final GameSettings settings = new GameSettings(
                elements.getEnvironment(),
                keyboard
        );

        elements.addElement(new Paddle(
                width / 2,
                height - paddleHeight - 2,
                paddleWidth,
                paddleHeight,
                settings
        ));
    }

    /**
     * Run.
     */
    public void run() {
        // countdown before turn starts.
//        this.animationRunner.run(new CountdownAnimation(3, 3, elements.getSprites(), animationRunner.getSleeper()));
        // running the level
        this.running = true;
        animationRunner.run(this);
    }

    /**
     * Close the game.
     */
    public void closeGame() {
        animationRunner.close();
    }

    @Override
    public boolean shouldStop() {
        return !running;
    }

    @Override
    public void doOneFrame(DrawSurface canvas) {
        if (keyboard.isPressed("p")) {
            this.animationRunner.run(new PauseScreen(this.keyboard));
        }
        elements.runSprites(canvas);
        if (remainingBlocks.getValue() == 0) {
            score.increase(100);
            running = false;
        } else if (remainingBalls.getValue() == 0) {
            running = false;
        }
    }
}

