// ID 316044809
package game.animation.levels;

import game.animation.background.Cloud;
import game.animation.background.SimpleBackground;
import game.animation.background.tree.Tree;
import game.collections.Sprite;
import game.elements.objects.Block;
import game.elements.shapes.DrawShapes;
import game.elements.shapes.Point;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The class Level 1.
 */
public class Level3 extends Level {
    public Level3(final int width, final int height) {
        super(width, height);
    }


    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public int paddleWidth() {
        return getWidth() / 4;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Cloud(
                new Cloud(
                        new Tree(
                                new Tree(
                                        new SimpleBackground(Color.BLUE)
                                ).translate(200, 0)
                        )
                ).scale(0.6, 0.6)
                 .translate(400, 100));
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new LinkedList<>();
        final int numOfRows = 5;
        final int blocksPerRow = numberOfBlocksToRemove() / numOfRows;
        final int startX = getWidth() / 4;
        final int startY = getHeight() / 4;
        final int blockWidth = (getWidth() - startX) / blocksPerRow;
        final int blockHeight = getHeight() / 20;
        for (int i = 0; i < numOfRows; i++) {
            for (int j = i; j < blocksPerRow; j++) {
                blocks.add(
                        new Block(
                                new Point(
                                        j * blockWidth + startX,
                                        i * blockHeight + startY
                                ),
                                blockWidth,
                                blockHeight,
                                (numOfRows - 1) - i,
                                DrawShapes.getColorSpace("Green")
                        )
                          );
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 50;
    }
}
