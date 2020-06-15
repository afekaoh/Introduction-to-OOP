// ID 316044809
package game.animation.levels;

import game.animation.background.SimpleBackground;
import game.collections.Sprite;
import game.elements.objects.Block;
import game.elements.physics.Velocity;
import game.elements.shapes.DrawShapes;
import game.elements.shapes.Point;

import java.awt.Color;
import java.util.List;

/**
 * The class Level 1.
 */
public class Level1 extends Level {

    /**
     * Instantiates a new Level 1.
     *
     * @param width  the width
     * @param height the height
     */
    public Level1(final int width, final int height) {
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
        return "DirectHit";
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
                        super.paddleHeight(),
                        0,
                        DrawShapes.getColorSpace("Green")
                ));
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return List.of(Velocity.fromAngleAndSpeed(0, super.paddleSpeed()));
    }
}
