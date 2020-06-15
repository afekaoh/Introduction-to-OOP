// ID 316044809
package game.animation.levels;

import game.animation.background.SimpleBackground;
import game.collections.Sprite;
import game.elements.objects.Block;
import game.elements.shapes.DrawShapes;
import game.elements.shapes.Point;

import java.awt.Color;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The class Level 1.
 */
public class Level2 extends Level {
    /**
     * The constant LEVEL2.
     */
    private final int width;
    private final int height;

    /**
     * Instantiates a new Level 2.
     *
     * @param width  the width
     * @param height the height
     */
    public Level2(final int width, final int height) {
        super(width, height);
        this.width = width;
        this.height = height;
    }

    @Override
    public int numberOfBalls() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return (int) (getWidth() * 0.9);
    }

    @Override
    public String levelName() {
        return "Wide easy";
    }

    @Override
    public Sprite getBackground() {
        return new SimpleBackground(Color.CYAN);
    }

    @Override
    public List<Block> blocks() {
        final int numOfBlocks = numberOfBlocksToRemove();
        final int blockWidth = getWidth() / numOfBlocks;
        final int blockHeight = getHeight() / 30;
        return IntStream.range(0, numOfBlocks)
                        .mapToObj(i -> new Block(
                                          new Point(i * blockWidth, getHeight() / 2),
                                          blockWidth,
                                          blockHeight,
                                          (i / 2) % 5,
                                          DrawShapes.getColorSpace("Rainbow")
                                  )
                                 ).collect(Collectors.toList());
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

}
