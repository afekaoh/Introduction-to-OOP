package game.geometry.shapes;// ID 316044809

import java.util.List;
import java.util.Objects;

import static java.util.Collections.min;

/**
 * The class geometry.abstract_geometry.Line.
 * representing a line in 2D space.
 */
public class Line {
    /**
     * The Start point.
     */
    private Point start;
    /**
     * The End point.
     */
    private Point end;

    /**
     * Instantiates a new geometry.abstract_geometry.Line using points.
     *
     * @param start the start point.
     * @param end   the end point.
     */
    public Line(final Point start, final Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new geometry.abstract_geometry.Line using XY coordinates.
     *
     * @param xStart the x start
     * @param yStart the y start
     * @param xEnd   the x end
     * @param yEnd   the y end
     */
    public Line(final double xStart, final double yStart, final double xEnd, final double yEnd) {
        this.start = new Point(xStart, yStart);
        this.end = new Point(xEnd, yEnd);
    }

    /**
     * Check edges.
     * check if 2 coincident lines are parallel or have 1 intersection point
     *
     * @param line1 the first line to check
     * @param line2 the other line to check
     * @return the line result
     */
    private LineResult checkEdges(final Line line1, final Line line2) {
        // if they coincident but have only 1 intersection point

        if (line1.start.equals(line2.start()) && !line1.isPointOn(line2.end())) {
            return new LineResult(0, 0);
        }

        if (line1.end.equals(line2.end()) && !line1.isPointOn(line2.start())) {
            return new LineResult(1, 1);
        }

        if (line1.start.equals(line2.end()) && !line1.isPointOn(line2.start())) {
            return new LineResult(0, 1);
        }

        if (line1.end.equals(line2.start()) && !line1.isPointOn(line2.end())) {
            return new LineResult(1, 0);
        }

        // they are parallel.
        return new LineResult();
    }

    /**
     * calculating the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Is point on the line.
     * gets a point and checking if the point on the line.
     *
     * @param p the point to check
     * @return if the point is on the line
     */
    public boolean isPointOn(Point p) {
        final double epsilon = 10e-12;
        // if the point is on the line the distance between it and the edges of the line is the length of the line
        return Math.abs(length() - (start.distance(p) + end.distance(p))) <= epsilon;
    }

    /**
     * Start point.
     *
     * @return the start point of the line
     */
    public Point start() {
        return start;
    }

    /**
     * End point.
     *
     * @return the end point of the line
     */
    public Point end() {
        return end;
    }

    /**
     * Middle point.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        return getPointByPercentage(0.5);
    }

    /**
     * Get point by percentage.
     *
     * @param percent the percent
     * @return a point on the line located at the given percentage from the start
     */
    public Point getPointByPercentage(double percent) {
        double xStart = start.getX() * (1 - percent);
        double yStart = start.getY() * (1 - percent);
        double xEnd = end.getX() * percent;
        double yEnd = end.getY() * percent;
        double x = xStart + xEnd;
        double y = yStart + yEnd;
        return new Point(x, y);
    }

    /**
     * Calculating the u and the t from the intersection formula.
     *
     * @param other the other line
     * @return a lineResult object containing the u the t and if the lines are parallel
     */
    private LineResult calcUT(final Line other) {
        /*
        setting some constants for better readability.
        all the names selected to be consistent with the formula in the wikipedia article
         */
        final double x1 = this.start.getX();
        final double y1 = this.start.getY();
        final double x2 = this.end.getX();
        final double y2 = this.end.getY();
        final double x3 = other.start().getX();
        final double y3 = other.start().getY();
        final double x4 = other.end().getX();
        final double y4 = other.end().getY();

        final double denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        if (denominator == 0) {
            //checking is they Coincident in exactly one point or if they are parallel
            return other.length() < this.length() ? checkEdges(this, other) : checkEdges(other, this);
        }

        // calculating t
        final double tNumerator = (x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4);
        final double t = tNumerator / denominator;

        //calculating u
        final double uNumerator = (x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3);
        final double u = -1 * (uNumerator / denominator);

        return new LineResult(t, u);
    }

    /**
     * Is intersecting boolean.
     * checking is the lines are intersecting using the line intersection formula
     *
     * @param other the other line
     * @return if the line segments are intersecting
     */
    public boolean isIntersecting(final Line other) {

        // checking the case that one of lines is a point
        if (this.length() == 0) {
            return other.isPointOn(this.start);
        }
        if (other.length() == 0) {
            return this.isPointOn(other.start());
        }

        // calculating the u and t from intersection formula
        final LineResult lineResult = calcUT(other);

        if (lineResult.isParallel()) {
            // they have 0 or infinity intersection points
            return false;
        }

        // checking if the intersection point is inside both of the line segments
        final double t = lineResult.getT();
        final double u = lineResult.getU();
        return (0 <= t && t <= 1) && (0 <= u && u <= 1);
    }

    /**
     * calculating the intersection point using the formula.
     *
     * @param other the other line
     * @return point of intersection or null if it doesn't exist
     */
    public Point intersectionWith(final Line other) {
        if (!isIntersecting(other)) {
            return null;
        }
        // checking the case that one of lines is a point
        if (this.length() == 0) {
            return this.start;
        }
        if (other.length() == 0) {
            return other.start();
        }

        // getting all the variables for the calculation
        final LineResult lineResult = calcUT(other);
        final double x1 = this.start.getX();
        final double y1 = this.start.getY();
        final double x2 = this.end.getX();
        final double y2 = this.end.getY();
        final double t = lineResult.getT();

        // calculating the point location
        return new Point(x1 + t * (x2 - x1), y1 + t * (y2 - y1));
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            // same reference
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            // not a line
            return false;
        }
        final Line line = (Line) o;
        // checking if they have the same starting and end points
        return (start.equals(line.start) && end.equals(line.end));
    }

    @Override
    public String toString() {
        return "geometry.abstract_geometry.Line{" + "start=" + start + ", end=" + end + '}';
    }

    /**
     * Closest intersection to start of line point.
     *
     * @param rect the rect
     * @return the point
     */
    public Point closestIntersectionToStartOfLine(final Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        return intersectionPoints.isEmpty() ? null : min(intersectionPoints, new PointsComparator(this.start));
    }

    /**
     * Sets start.
     *
     * @param newStart the new start point
     */
    public void setStart(Point newStart) {
        this.start = newStart;
    }

    /**
     * Set end.
     *
     * @param newEnd the new end point
     */
    public void setEnd(Point newEnd) {
        this.end = newEnd;
    }


    /**
     * an object to help returns multiple element in the calcUT method.
     * the u, t, parallel will be calculate from using this article
     * https://en.wikipedia.org/wiki/Line%E2%80%93line_intersection
     */
    static final class LineResult {
        /**
         * The t from the line intersection formula.
         */
        private final double t;
        /**
         * The u from the line intersection formula.
         */
        private final double u;
        /**
         * a flag will set to true if the line are parallel to each other.
         */
        private final boolean parallel;

        /**
         * Instantiates a new geometry.abstract_geometry.Line result in the case the lines have intersection point.
         *
         * @param t the t
         * @param u the u
         */
        LineResult(final double t, final double u) {
            this.t = t;
            this.u = u;
            this.parallel = false;
        }

        /**
         * Instantiates a new geometry.abstract_geometry.Line result in the case the lines don't have intersection point.
         */
        LineResult() {
            this.t = 0;
            this.u = 0;
            this.parallel = true;
        }

        /**
         * isParallel.
         *
         * @return parallel boolean
         */
        public boolean isParallel() {
            return parallel;
        }

        /**
         * Gets t.
         *
         * @return the t
         */
        public double getT() {
            return t;
        }

        /**
         * Gets u.
         *
         * @return the u
         */
        public double getU() {
            return u;
        }
    }
}
