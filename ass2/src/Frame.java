import java.awt.Color;

/**
 * The class Frame.
 */
// ID 316044809
public class Frame {

    /**
     * The Frame.
     */
    private Line frameLine;
    /**
     * The Color.
     */
    private Color color = new Color(240, 240, 240);

    /**
     * Instantiates a new Frame.
     *
     * @param topLeftX the top left x
     * @param topLeftY the top left y
     * @param downRightX the down right x
     * @param downRightY the down right y
     */
    public Frame(final int topLeftX, final int topLeftY, final int downRightX, final int downRightY) {
        this.frameLine = new Line(topLeftX, topLeftY, downRightX, downRightY);
    }

    /**
     * Instantiates a new Frame.
     *
     * @param topLeftX the top left x
     * @param topLeftY the top left y
     * @param downRightX the down right x
     * @param downRightY the down right y
     * @param color the color
     */
    public Frame(final int topLeftX, final int topLeftY, final int downRightX, final int downRightY,
                 final Color color) {
        this.frameLine = new Line(topLeftX, topLeftY, downRightX, downRightY);
        this.color = color;
    }


    /**
     * Bottom point.
     *
     * @return the point
     */
    public Point bottom() {
        return frameLine.end();
    }

    /**
     * Top point.
     *
     * @return the point
     */
    public Point top() {
        return frameLine.start();
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
    public int getFrameWidth() {
        return (int) (frameLine.end().getX() - frameLine.start().getX());
    }

    /**
     * Gets frame height.
     *
     * @return the frame height
     */
    public int getFrameHeight() {
        return (int) (frameLine.end().getY() - frameLine.start().getY());
    }
}
