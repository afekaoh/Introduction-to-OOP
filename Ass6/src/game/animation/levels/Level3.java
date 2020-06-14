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
        Polygon cloud = DrawShapes.getCloud();
        Polygon cloud2 = DrawShapes.getCloud(400, 100);

        return new Sprite() {
            @Override
            public void drawOn(final DrawSurface d) {
//                d.setColor(Color.decode("#F6F3E3"));
                d.setColor(Color.GREEN);
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
