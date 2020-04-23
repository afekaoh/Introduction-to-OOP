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
    public void drawFrame(long startTime) {
        final int framesPerSecond = 60;
        final int millisecondsPerFrame = 1000 / framesPerSecond;
        gui.show(canvas);
        long usedTime = System.currentTimeMillis() - startTime;
        long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
        if (milliSecondLeftToSleep > 0) {
            sleeper.sleepFor(milliSecondLeftToSleep);
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
                new Block(new Point(-97, 0), 100, height, 0),
                //right edge
                new Block(new Point(width - 3, 0), 100, height, 0),
                //top edge
                new Block(new Point(0, -97), width, 100, 0),
                //bottom edge
                new Block(new Point(0, height - 3), width, 100, 0),
        };
        for (Block edge : edges) {
            addCollidable(edge);
        }
        // todo fix numbers
        int startX = 190;
        int startY = 100;
        int blocksPerRow = 15;
        int blockWidth = (width - startX) / blocksPerRow;
        int numOfRows = 5;
        int blockHeight = (int) (height * 0.03);

        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < blocksPerRow; j++) {
                Block block = new Block(new Point(j * blockWidth + startX, i * blockHeight + startY), blockWidth,
                                        blockHeight, i);
                block.addToGame(this);
            }
            blocksPerRow--;
            startX += blockWidth;
        }
        //todo fix magic numbers
        Paddle player = new Paddle(width / 2, height - 23, 100, 20, keyboardSensor);
        player.addToGame(this);
        Ball ball = new Ball(new Point(width / 2, height / 2), 3, Color.YELLOW, environment);
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
            drawFrame(startTime);
        }
    }
}
