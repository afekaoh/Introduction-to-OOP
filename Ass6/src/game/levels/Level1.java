// ID 316044809
package game.levels;

import biuoop.DrawSurface;
import game.collections.ElementsCollection;
import game.collections.Sprite;
import game.elements.objects.Block;
import game.elements.physics.Velocity;
import game.elements.shapes.Point;

import java.awt.Color;
import java.util.List;

/**
 * The class Level 1.
 */
public class Level1 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return List.of(Velocity.fromAngleAndSpeed(0, 5));
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "level1";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(final DrawSurface d) {
                d.setColor(Color.RED);
                d.fillRectangle(0, 0, 800, 600);
            }

            @Override
            public void timePassed() {
                // do nothing
            }

            @Override
            public void addToGame(final ElementsCollection elementsCollection) {
                elementsCollection.addSprite(this);
            }

            @Override
            public void removeFromGame(final ElementsCollection elementsCollection) {
                // not removing
            }
        };
    }

    @Override
    public List<Block> blocks() {
        return List.of(
                new Block(
                        new Point(400, 200),
                        100,
                        20,
                        1
                ));
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
