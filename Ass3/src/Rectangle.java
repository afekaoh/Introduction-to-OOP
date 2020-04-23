// ID 316044809

import java.util.ArrayList;
import java.util.List;

/**
 * The class Rectangle.
 */
public class Rectangle {

    /**
     * The Rectangle.
     */
    private final Line boundaryLine;

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this(upperLeft, new Point(upperLeft.getX() + width, upperLeft.getY() + height));
    }

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param downRight the down right
     */
    public Rectangle(Point upperLeft, Point downRight) {
        this.boundaryLine = new Line(upperLeft, downRight);
    }

    /**
     * todo
     * Move.
     *
     * @param v the v
     */
    public void move(Velocity v) {
        this.boundaryLine.setStart(v.applyToPoint(boundaryLine.start()));
        this.boundaryLine.setEnd(v.applyToPoint(boundaryLine.end()));
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
                //bottomt line
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
        return boundaryLine.start();
    }

    /**
     * Gets frame width.
     *
     * @return the frame width
     */
    public int getWidth() {
        return (int) (boundaryLine.end().getX() - boundaryLine.start().getX());
    }

    /**
     * Gets frame height.
     *
     * @return the frame height
     */
    public int getHeight() {
        return (int) (boundaryLine.end().getY() - boundaryLine.start().getY());
    }

    public double middle() {
        return (right() + left()) / 2;
    }

    public Line getTopLine() {
        return new Line(left(), top(), right(), top());
    }
}
