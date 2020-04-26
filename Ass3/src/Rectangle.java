// ID 316044809

import java.util.ArrayList;
import java.util.List;

/**
 * The class Rectangle.
 */
public class Rectangle {

    /**
     * The Width.
     */
    private final int width;
    /**
     * The Height.
     */
    private final int height;
    /**
     * The Center.
     */
    private Point center;

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, int width, int height) {
        this.width = width;
        this.height = height;
        this.center = new Point(upperLeft.getX() + (width / 2), upperLeft.getY() + height / 2);
    }


    /**
     * Bottom int.
     *
     * @return the int
     */
    public int bottom() {
        return (int) this.center.getY() + height / 2;
    }

    /**
     * Top int.
     *
     * @return the int
     */
    public int top() {
        return (int) this.center.getY() - height / 2;
    }

    /**
     * Right double.
     *
     * @return the double
     */
    public int right() {
        return (int) this.center.getX() + width / 2;

    }

    /**
     * Left double.
     *
     * @return the double
     */
    public int left() {
        return (int) this.center.getX() - width / 2;
    }

    /**
     * Intersection points list.
     *
     * @param line the line
     * @return the list
     */
    public List<Point> intersectionPoints(Line line) {
        Line[] edges = {
                // top line
                new Line(right(), top(), left(), top()),
                //right line
                new Line(right(), top(), right(), bottom()),
                //left line
                new Line(left(), top(), left(), bottom()),
                //bottom line
                new Line(right(), bottom(), left(), bottom())
        };
        List<Point> points = new ArrayList<>();
        for (Line edge : edges) {
            if (edge.isIntersecting(line)) {
                points.add(edge.intersectionWith(line));
            }
        }
        return points;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
    public Point getUpperLeft() {
        return this.center.translate(-width / 2, -height / 2);
    }

    /**
     * todo
     * Gets frame width.
     *
     * @return the frame width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * todo
     * Gets frame height.
     *
     * @return the frame height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * todo
     * Gets down right.
     *
     * @return the down right
     */
    public Point getDownRight() {
        return this.center.translate(width / 2, height / 2);
    }

    /**
     * Gets center.
     *
     * @return the center
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Gets center.
     *
     * @param newCenter the new center
     */
    public void setCenter(Point newCenter) {
        this.center = newCenter;
    }
}
