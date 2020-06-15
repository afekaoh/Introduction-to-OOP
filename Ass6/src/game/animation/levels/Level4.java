// ID 316044809
package game.animation.levels;

import game.animation.background.SimpleBackground;
import game.collections.Sprite;
import game.elements.objects.Block;
import game.elements.shapes.DrawShapes;
import game.elements.shapes.Point;

import java.awt.Color;
import java.util.List;

/**
 * The class Level 1.
 */
public class Level4 extends Level {

    public Level4(final int width, final int height) {
        super(width, height);
    }

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "level4";
    }

    @Override
    public Sprite getBackground() {
        return new SimpleBackground(Color.CYAN);
    }

    @Override
    public List<Block> blocks() {
        return List.of(
                new Block(
                        new Point(400, 200),
                        100,
                        20,
                        0,
                        DrawShapes.getColorSpace("Rainbow")
                ));
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
