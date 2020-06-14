// ID 316044809
package game.animation.levels;

import biuoop.DrawSurface;
import game.collections.ElementsCollection;
import game.collections.Sprite;
import game.elements.objects.Block;
import game.elements.shapes.DrawShapes;
import game.elements.shapes.Point;

import java.awt.Color;
import java.awt.Polygon;
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
        Polygon cloud = DrawShapes.getCloud();
        Polygon cloud2 = DrawShapes.getCloud(400, 100);

        return new Sprite() {
            @Override
            public void drawOn(final DrawSurface d) {
//                d.setColor(Color.decode("#F6F3E3"));
                d.setColor(Color.CYAN);
                d.fillRectangle(0, 0, getWidth(), getHeight());
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
