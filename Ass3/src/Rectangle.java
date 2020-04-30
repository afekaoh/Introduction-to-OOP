// ID 316044809

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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
     * Intersection points.
     * find all the intersection points between the line and the rectangle
     *
     * @param line the line to check intersection with
     * @return a list of all the intersection points between the line given and the rectangle
     */
    public List<Point> intersectionPoints(Line line) {
        Stream<Line> edges = Stream.of(
                // top line
                new Line(right(), top(), left(), top()),
                //right line
                new Line(right(), top(), right(), bottom()),
                //left line
                new Line(left(), top(), left(), bottom()),
                //bottom line
                new Line(right(), bottom(), left(), bottom())
                                      );
        return edges.filter(line::isIntersecting)
                    .map(line::intersectionWith)
                    .collect(toList());
    }

    /**
     * the Right of the rectangle.
     *
     * @return X of the rightmost point in the rectangle
     */
    public int right() {
        return (int) this.center.getX() + width / 2;

    }

    /**
     * the Top of the rectangle.
     *
     * @return Y of the topmost point in the rectangle
     */
    public int top() {
        return (int) this.center.getY() - height / 2;
    }

    /**
     * the Left of the rectangle.
     *
     * @return X of the leftmost point in the rectangle
     */
    public int left() {
        return (int) this.center.getX() - width / 2;
    }

    /**
     * the Bottom of the rectangle.
     *
     * @return Y of the bottommost point in the rectangle
     */
    public int bottom() {
        return (int) this.center.getY() + height / 2;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.center.translate(-width / 2, -height / 2);
    }

    /**
     * Gets  width.
     *
     * @return the width of the rectangle
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height of the rectangle
     */
    public int getHeight() {
        return this.height;
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
