// ID 316044809
package game.animation.levels;

import game.animation.background.Background;
import game.animation.background.Bush;
import game.animation.background.Cloud;
import game.animation.background.Hill;
import game.animation.background.SimpleBackground;
import game.animation.background.tree.Tree;
import game.collections.Sprite;
import game.elements.objects.Block;
import game.elements.shapes.DrawShapes;
import game.elements.shapes.Point;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
        final Random rand = new Random();
        Background background;

        // crete hills
        background = Background
                .createRandomElements(
                        3,
                        new Hill(new SimpleBackground(Color.BLUE)),
                        rand
                                     );
        // crete the first trees
        background = Background.createRandomElements(
                5,
                new Tree(background),
                rand
                                                    );
        // creat the first bushes
        background = Background.createRandomElements(
                3,
                new Bush(background),
                rand
                                                    );
        // crete the seconds trees
        background = Background.createRandomElements(
                5,
                new Tree(background
                ),
                rand
                                                    );
        // crete the seconds bushes
        background = Background.createRandomElements(3, new Bush(background), rand);

        return new Cloud(
                new Cloud(background).scale(0.6, 0.6)
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

    @Override
    public int getFrameOerSeconds() {
        return 45;
    }
}
