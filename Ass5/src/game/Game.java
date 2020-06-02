// ID 316044809
package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import game.collections.Collidable;
import game.collections.ElementsCollection;
import game.collections.GameEnvironment;
import game.collections.Sprite;
import game.collections.SpriteCollection;
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
 * The class game.Game.
 * the main class of the game itself creating and running all the elements of the game
 */
public class Game {

    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECONDS_PER_FRAME = 1000 / FRAMES_PER_SECOND;
    public static final Color SCREEN_COLOR = Color.decode("#F6F3E3");
    public static final int START_HEIGHT = 20;
    /**
     * The Width of the game.
     */
    private final int width;
    /**
     * The Height of the game.
     */
    private final int height;
    /**
     * The Gui for the game.
     */
    private final GUI gui;
    /**
     * The Sleeper of the game.
     */
    private final Sleeper sleeper;
    private final KeyboardSensor keyboardSensor;
    /**
     * the collection of all the Sprites in the game.
     */
    private final SpriteCollection sprites = null;
    /**
     * The Environment - includes all the collidables of the game.
     */
    private final GameEnvironment environment = null;
    private final ElementsCollection elements;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    /**
     * The Draw Surface of the game.
     */
    private DrawSurface canvas;

    /**
     * Instantiates a new game.Game.
     *
     * @param width  the width of the game
     * @param height the height of the game
     * @param title  the title of the game
     */
    public Game(final int width, final int height, final String title) {
        this.width = width;
        this.height = height;
        this.gui = new GUI(title, this.width, this.height);
        this.sleeper = new Sleeper();
        this.elements = new ElementsCollection();
        keyboardSensor = gui.getKeyboardSensor();
        setNewCanvas();
    }

    /**
     * Sets a new DrawSurface.
     */
    public void setNewCanvas() {
        this.canvas = gui.getDrawSurface();
        canvas.setColor(SCREEN_COLOR);
        canvas.fillRectangle(0, 0, width, height);
    }

    /**
     * Add collidable to the game.
     *
     * @param c the game.collections.Collidable to add to the game
     */
    public void addCollidable(Collidable c) {
        //noinspection ConstantConditions
        environment.addCollidable(c);
    }

    /**
     * Add sprite to the game.
     *
     * @param s the game.collections.Sprite to add to the game
     */
    public void addSprite(Sprite s) {
        //noinspection ConstantConditions
        sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create all the component of the game
     * i.e Blocks, Balls and game.geometry.objects.Paddle add them to the game, and sta.
     */
    public void initialize() {

        createScoreIndicator();

        // creating the game blocks
        createBlocks();

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
                        Game.START_HEIGHT
                ),
                score
        );
        elements.addElement(indicator);
    }

    /**
     * Create blocks for the game.
     */
    private void createBlocks() {
        final int numOfRows = 5;
        final int blocksPerRow = 10;
        final int startX = width / 4;
        final int startY = width / 9;
        final int blockWidth = (width - startX) / blocksPerRow;
        final int blockHeight = (int) (height * 0.05);
        final int numOfBlocks = numOfRows * blocksPerRow;
        remainingBlocks = new Counter(numOfBlocks);
        HitListener blockRemover = new BlockRemover(this, remainingBlocks);
        HitListener scoreTracking = new ScoreTrackingListener(score);
        for (int row = 0; row < numOfRows; row++) {
            for (int column = blocksPerRow - 1; column >= row; column--) {
                final Block block = new Block(
                        new Point(column * blockWidth + startX, row * blockHeight + startY + Game.START_HEIGHT),
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
        HitListener ballRemover = new BallRemover(this, remainingBalls);
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
                new EdgeBlock(new Point(0, -edgeSize), width, edgeSize + Game.START_HEIGHT, 0)
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
                keyboardSensor
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
     * run the game.
     */
    public void run() {
        while (true) {
            long startTime = System.currentTimeMillis();
            // getting ready for the next frame of the game
            setNewCanvas();
            elements.runSprites(canvas);
            if (remainingBlocks.getValue() == 0) {
                score.increase(100);
                return;
            } else if (remainingBalls.getValue() == 0) {
                return;
            }
            drawFrame(startTime);
        }
    }


    /**
     * draws the canvas to the gui and suspends the animation.
     *
     * @param startTime the time the frame has begun
     */
    public void drawFrame(long startTime) {
        gui.show(canvas);
        long usedTime = System.currentTimeMillis() - startTime;
        long milliSecondLeftToSleep = MILLISECONDS_PER_FRAME - usedTime;
        if (milliSecondLeftToSleep > 0) {
            sleeper.sleepFor(milliSecondLeftToSleep);
        }
    }

    /**
     * Get elements collection.
     *
     * @return the elements collection
     */
    public ElementsCollection getElementsCollection() {
        return this.elements;
    }

    /**
     * Close the game.
     */
    public void closeGame() {
        gui.close();
    }
}
