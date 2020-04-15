// ID 316044809

import java.awt.Color;

/**
 * The class Boundary.
 */
public class Boundary {

    /**
     * The Boundary.
     */
    private final Line boundaryLine;
    /**
     * The Color.
     */
    private Color color = new Color(240, 240, 240);

    /**
     * Instantiates a new Boundary.
     *
     * @param topLeftX the topPoint left x
     * @param topLeftY the topPoint left y
     * @param downRightX the down right x
     * @param downRightY the down right y
     */
    public Boundary(final int topLeftX, final int topLeftY, final int downRightX, final int downRightY) {
        this.boundaryLine = new Line(topLeftX, topLeftY, downRightX, downRightY);
    }

    /**
     * Instantiates a new Boundary.
     *
     * @param topLeftX the topPoint left x
     * @param topLeftY the topPoint left y
     * @param downRightX the down right x
     * @param downRightY the down right y
     * @param color the color
     */
    public Boundary(final int topLeftX, final int topLeftY, final int downRightX, final int downRightY,
                    final Color color) {
        this.boundaryLine = new Line(topLeftX, topLeftY, downRightX, downRightY);
        this.color = color;
    }


    /**
     * Bottom point.
     *
     * @return the point
     */
    public Point bottomPoint() {
        return boundaryLine.end();
    }

    /**
     * Top point.
     *
     * @return the point
     */
    public Point topPoint() {
        return boundaryLine.start();
    }

    /**
     * Bottom point.
     *
     * @return the point
     */
    public double bottom() {
        return boundaryLine.end().getY();
    }

    /**
     * Bottom point.
     *
     * @return the point
     */
    public double top() {
        return boundaryLine.start().getY();
    }

    /**
     * Bottom point.
     *
     * @return the point
     */
    public double right() {
        return boundaryLine.end().getX();
    }

    /**
     * Bottom point.
     *
     * @return the point
     */
    public double left() {
        return boundaryLine.start().getX();
    }


    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets frame width.
     *
     * @return the frame width
     */
    public int getBoundaryWidth() {
        return (int) (boundaryLine.end().getX() - boundaryLine.start().getX());
    }

    /**
     * Gets frame height.
     *
     * @return the frame height
     */
    public int getBoundaryHeight() {
        return (int) (boundaryLine.end().getY() - boundaryLine.start().getY());
    }
}
