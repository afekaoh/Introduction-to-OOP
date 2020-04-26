// ID 316044809

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The class Game.
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
    private final SpriteCollection sprites;
    /**
     * The Environment - includes all the collidables of the game.
     */
    private final GameEnvironment environment;
    /**
     * The Draw Surface of the game.
     */
    private DrawSurface canvas;

    /**
     * Instantiates a new Game.
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
        canvas.setColor(SCREEN_COLOR);
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
        // getting ready for the next frame of the game
        setNewCanvas();
    }

    /**
     * Add collidable to the game.
     *
     * @param c the Collidable to add to the game
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite to the game.
     *
     * @param s the Sprite to add to the game
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create all the component of the game
     * i.e Blocks, Balls and Paddle add them to the game, and sta.
     */
    public void initialize() {
        // the boundary of the game
        final Rectangle gameEdge = new Rectangle(new Point(0, 0), width, height);

        // creating blocks that blocks the edges of the screen
        final Block[] edges = {
                //left edge
                new Block(new Point(-100, 0), 100, height, 0),
                //right edge
                new Block(new Point(width, 0), 100, height, 0),
                //top edge
                new Block(new Point(0, -100), width, 100, 0),
                //bottom edge
                new Block(new Point(0, height), width, 100, 0),
        };
        // adding the blocs to the environment
        for (Block edge : edges) {
            addCollidable(edge);
        }

        // creating the game blocks which the ball interact with
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

        //creating the paddle which the player is playing with
        final int paddleWidth = 100;
        final int paddleHeight = 20;
        Paddle player = new Paddle(width / 2, height - paddleHeight - 2, paddleWidth, paddleHeight,
                                   new GameSettings(gameEdge, keyboardSensor));
        player.addToGame(this);

        // creating the balls
        Ball[] balls = new Ball[2];
        for (int i = 0; i < balls.length; i++) {
            balls[i] = new Ball(new Point(width / (i + 2), 3 * height / 4), 3, Color.YELLOW, environment);
            balls[i].addToGame(this);
        }
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
