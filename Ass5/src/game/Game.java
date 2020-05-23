// ID 316044809
package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import game.collections.Collidable;
import game.collections.ElementsCollection;
import game.collections.GameEnvironment;
import game.collections.GameSettings;
import game.collections.Sprite;
import game.collections.SpriteCollection;
import game.geometry.objects.Ball;
import game.geometry.objects.Block;
import game.geometry.objects.Paddle;
import game.geometry.shapes.Point;
import game.listeners.BallRemover;
import game.listeners.BlockRemover;
import game.tools.Counter;

import java.awt.Color;
import java.util.stream.Stream;

/**
 * The class game.Game.
 * the main class of the game itself creating and running all the elements of the game
 */
public class Game {

    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECONDS_PER_FRAME = 1000 / FRAMES_PER_SECOND;
    public static final Color SCREEN_COLOR = Color.black;
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
    private final Counter remainingBlocks;
    private final Counter remainingBalls;
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
        remainingBlocks = new Counter(0);
        remainingBalls = new Counter(0);
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
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        BallRemover ballRemover = new BallRemover(this, remainingBalls);

        //creating the edges blocks
        // creating blocks that blocks the edges of the screen
        final Block downBloack = new Block(new Point(0, height), width, 100, 0);
        downBloack.addHitListener(ballRemover);
        final Stream<Block> edges = Stream.of(
                //left edge
                new Block(new Point(-100, 0), 100, height, 0),
                //right edge
                new Block(new Point(width, 0), 100, height, 0),
                //bottom edge
                downBloack,
                //top edge
                new Block(new Point(0, -100), width, 100, 0)
                                             );
        // adding the edges to the environment
        edges.forEach(elements::addElement);

        // creating the game blocks
        final int numOfRows = 5;
        final int blocksPerRow = 10;
        final int blockWidth = width / blocksPerRow;
        final int blockHeight = (int) (height * 0.05);
        final int numOfBlocks = numOfRows * blocksPerRow;
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < blocksPerRow; j++) {
                final Block block = new Block(
                        new Point(j * blockWidth, i * blockHeight),
                        blockWidth,
                        blockHeight,
                        i
                );
                block.addHitListener(blockRemover);
                elements.addElement(block);
            }
        }
        remainingBlocks.increase(numOfBlocks);

        // creating the paddle that the player will play with
        final int paddleHeight = (int) (height * 0.03);
        // the paddle is extra length to better see region interaction
        final int paddleWidth = width / 8;
        final GameSettings settings = new GameSettings(
                elements.getEnvironment(),
                keyboardSensor
        );
        elements.addElement(new Paddle(
                width / 2,
                height - (paddleHeight + 2),
                paddleWidth,
                paddleHeight,
                settings
        ));

        // creating the balls
        final int numOfBalls = 3;
        for (int i = 0; i < numOfBalls; i++) {
            elements.addElement(new Ball(
                    new Point(width / (i + 2), 3 * height / 4),
                    3,
                    Color.YELLOW,
                    elements.getEnvironment()
            ));
        }
        remainingBalls.increase(numOfBalls);

        // adding all the elements to the game
        elements.addAll();
    }

    /**
     * run the game.
     */
    public void run() {
        while (true) {
            long startTime = System.currentTimeMillis();
            elements.runSprites(canvas);
            drawFrame(startTime);
            if (remainingBlocks.getValue() == 0 || remainingBalls.getValue() == 0) {
                return;
            }
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
        // getting ready for the next frame of the game
        setNewCanvas();
    }

    public ElementsCollection getElementsCollection() {
        return this.elements;
    }

    public void closeGame() {
        gui.close();
    }
}
