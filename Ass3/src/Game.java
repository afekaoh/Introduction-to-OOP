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


    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECONDS_PER_FRAME = 1000 / FRAMES_PER_SECOND;
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
        gui.show(canvas);
        long usedTime = System.currentTimeMillis() - startTime;
        long milliSecondLeftToSleep = MILLISECONDS_PER_FRAME - usedTime;
        if (milliSecondLeftToSleep > 0) {
            sleeper.sleepFor(milliSecondLeftToSleep);
        }
        setNewCanvas();
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
                new Block(new Point(-100, 0), 100, height, 0),
                //right edge
                new Block(new Point(width, 0), 100, height, 0),
                //top edge
                new Block(new Point(0, -100), width, 100, 0),
                //bottom edge
                new Block(new Point(0, height), width, 100, 0),
        };
        for (Block edge : edges) {
            addCollidable(edge);
        }
        final int numOfRows = 5;
        int blocksPerRow = 10;
        int startX = width / 4;
        final int startY = width / 9;
        final int blockWidth = (width - startX) / blocksPerRow;
        final int blockHeight = (int) (height * 0.05);

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
        final int paddleWidth = 100;
        final int paddleHeight = 20;
        Paddle player = new Paddle(width / 2, height - paddleHeight - 2, paddleWidth, paddleHeight, keyboardSensor,
                                   environment);
        player.addToGame(this);
        Ball[] balls = new Ball[2];
        for (int i = 0; i < balls.length; i++) {
            balls[i] = new Ball(new Point(width / (i + 2), 3 * height / 4), 3, Color.YELLOW, environment);
            balls[i].addToGame(this);
        }
        run();
    }

    /**
     * todo
     * Draw game.
     */
    public void run() {
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            sprites.drawAllOn(canvas);
            sprites.notifyAllTimePassed();
            drawFrame(startTime);
        }
    }
}
