// ID 316044809
package game.animation.background;

import game.collections.Sprite;

import java.awt.Polygon;

public class Tree extends Background {
    private Polygon tree;

    public Tree(final Sprite backgroundElement) {
        super(backgroundElement);
        this.tree = new Polygon();
    }

    public Tree(final Sprite backgroundElement, final Polygon newShape) {
        super(backgroundElement);
        this.tree = newShape;
    }

    @Override
    protected Background createNew(final Sprite backgroundElement, final Polygon newShape) {
        return new Tree(backgroundElement, newShape);
    }

    @Override
    protected Polygon getPolygon() {
        return tree;
    }
}
