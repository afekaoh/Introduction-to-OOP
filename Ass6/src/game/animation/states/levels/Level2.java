// ID 316044809
package game.animation.states.levels;

import biuoop.DrawSurface;
import game.collections.ElementsCollection;
import game.collections.Sprite;
import game.elements.objects.Block;
import game.elements.physics.Velocity;
import game.elements.shapes.DrawShapes;
import game.elements.shapes.Point;

import java.awt.Color;
import java.awt.Polygon;
import java.util.List;

/**
 * The class Level 1.
 */
public class Level2 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return List.of(Velocity.fromAngleAndSpeed(0, 5));
    }

    @Override
    public List<Point> initialBallLocation() {
        return List.of(new Point(getWidth() / 2, (3 * getHeight()) / 4));
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
    public int paddleHeight() {
        return 20;
    }

    @Override
    public String levelName() {
        return "level2";
    }

    @Override
    public Sprite getBackground() {
        Polygon cloud = DrawShapes.getCloud();
        Polygon cloud2 = DrawShapes.getCloud(400, 100);

        return new Sprite() {
            @Override
            public void drawOn(final DrawSurface d) {
//                d.setColor(Color.decode("#F6F3E3"));
                d.setColor(Color.CYAN);
                d.fillRectangle(0, 0, 800, 600);
                d.setColor(Color.black);
                d.drawPolygon(cloud);
                d.drawPolygon(cloud2);
                d.setColor(Color.white);
                d.fillPolygon(cloud);
                d.fillPolygon(cloud2);
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
                        0
                ));
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public int getWidth() {
        return 800;
    }

    @Override
    public int getHeight() {
        return 600;
    }
}
