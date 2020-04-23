// ID 316044809
//todo add doc

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The class Game.
 * Implementing all the animation related methods to be used in the various animations classes.
 */
public class Game {
    /**
     * The constant SLEEPING_TIME.
     */
// constants for the animation classes
    public static final int SLEEPING_TIME = 40;

    /**
     * The Width of the animation.
     */
    private final int width;
    /**
     * The Height of the animation.
     */
    private final int height;
    /**
     * The Gui for the animation.
     */
    private final GUI gui;
    /**
     * The Sleeper of the animation.
     */
    private final Sleeper sleeper;

    private final KeyboardSensor keyboardSensor;
    /**
     * The Sprites.
     */
    private final SpriteCollection sprites;
    /**
     * The Environment.
     */
    private final GameEnvironment environment;
    /**
     * The Canvas.
     */
    private DrawSurface canvas;

    /**
     * Instantiates a new Game.
     *
     * @param width  the width of the animation
     * @param height the height of the animation
     * @param title  the title of the animation
     */
    public Game(final int width, final int height, final String title) {
        this.width = width;
        this.height = height;
        this.gui = new GUI(title, this.width, this.height);
        this.sleeper = new Sleeper();
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        keyboardSensor = gui.getKeyboardSensor();
        setNewCanvas();
    }

    /**
     * Map values from one range to another.
     *
     * @param value  the incoming value to be converted
     * @param start1 lower bound of the value's current range
     * @param stop1  upper bound of the value's current range
     * @param start2 lower bound of the value's target range
     * @param stop2  upper bound of the value's target range
     * @return value mapped to the new range
     */
    public static double map(final double value, final double start1, final double stop1, final double start2,
                             final double stop2) {
        return (value - start1) / (stop1 - start1) * (stop2 - start2) + start2;
    }

    /**
     * Sets a new DrawSurface.
     */
    public void setNewCanvas() {
        this.canvas = gui.getDrawSurface();
        canvas.setColor(Color.WHITE);
        canvas.fillRectangle(0, 0, width, height);
    }

    /**
     * todo
     * draws the canvas to the gui and suspends the animation.
     *
     * @param startTime the start time
     */
    public void showFrame(long startTime) {
        final int framesPerSecond = 60;
        final int millisecondsPerFrame = 1000 / framesPerSecond;
        gui.show(canvas);
        long usedTime = System.currentTimeMillis() - startTime;
        long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
        if (milliSecondLeftToSleep > 0) {
            sleeper.sleepFor(milliSecondLeftToSleep);
            sleeper.sleepFor(SLEEPING_TIME);
        }
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * todo
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        Block[] edges = {
                //left edge
                new Block(new Point(-100, 0), 100, height),
                //right edge
                new Block(new Point(width, 0), 100, height),
                //top edge
                new Block(new Point(0, -100), width, 100),
        };
        for (Block edge : edges) {
            addCollidable(edge);
        }

        int blocksPerRow = 15;
        int blockWidth = 30;
        int numOfRows = 5;
        int blockHeight = 10;
        int startX = 100;
        int startY = 100;

        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < blocksPerRow; j++) {
                Block block = new Block(new Point(j * blockWidth + startX, i * blockHeight + startY), blockWidth,
                                        blockHeight);
                block.addToGame(this);
            }

        }
        //todo fix magic numbers
        Paddle player = new Paddle(width / 2, height - 23, 100, 20, keyboardSensor);
        player.addToGame(this);
        Ball ball = new Ball(new Point(player.getCollisionRectangle().middle(),
                                       player.getCollisionRectangle().top() - 3),
                             3, Color.YELLOW, environment);
        this.addSprite(ball);
        run();
    }

    /**
     * todo
     * Draw game.
     */
    public void run() {
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            setNewCanvas();
            sprites.drawAllOn(canvas);
            sprites.notifyAllTimePassed();
            showFrame(startTime);
        }
    }
}
