// ID 316044809

import java.util.Objects;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * The class Point.
 * representing a point in 2D space.
 */
public class Point {
    /**
     * The X coordinate of the point.
     */
    private double x;
    /**
     * The Y coordinate of the point.
     */
    private double y;

    /**
     * Instantiates a new Point.
     *
     * @param x the x
     * @param y the y
     */
    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * todo
     * Instantiates a new Point.
     *
     * @param p the p
     */
    public Point(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * Get random point.
     *
     * @param xRange the x range
     * @param yRange the y range
     * @return a point in a random location between (0,0) to (xRange,yRange)
     */
    public static Point getRandomPoint(final double xRange, final double yRange) {
        final double x = current().nextDouble(xRange);
        final double y = current().nextDouble(yRange);
        return new Point(x, y);
    }

    /**
     * Gets random point. between 2 points
     *
     * @param start the start of the range
     * @param end   the end of the range
     * @return random point between the start and the end
     */
    public static Point getRandomPoint(final Point start, final Point end) {
        final double x = current().nextDouble(start.getX(), end.getX());
        final double y = current().nextDouble(start.getY(), end.getY());
        return new Point(x, y);
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * Sets x.
     *
     * @param newX the x
     */
    public void setX(final double newX) {
        this.x = newX;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * Sets y.
     *
     * @param newY the y
     */
    public void setY(final double newY) {
        this.y = newY;
    }

    /**
     * calculating the distance between 2 points.
     *
     * @param other the other point
     * @return the distance of this point to the other point
     */
    public double distance(final Point other) {
        return Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Equals boolean.
     *
     * @param o an object to determent equality to
     * @return true is the points are equal, false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            // same reference
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            // not a point
            return false;
        }
        // compering  values
        final Point point = (Point) o;
        final double epsilon = 10e-12;
        final double deltaX = Math.abs(point.x - x);
        final double deltaY = Math.abs(point.y - y);
        return deltaX <= epsilon && epsilon >= deltaY;
    }

    /**
     * todo
     * Move point.
     *
     * @param deltaX the delta x
     * @param deltaY the delta y
     * @return the point
     */
    public Point translate(double deltaX, double deltaY) {
        double newX = this.x + deltaX;
        double newY = this.y + deltaY;
        return new Point(newX, newY);
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }
}
