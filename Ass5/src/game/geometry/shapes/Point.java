// ID 316044809
package game.geometry.shapes;

import java.util.Objects;

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
     * Instantiates identical to a given point.
     *
     * @param p the point to copy
     */
    public Point(final Point p) {
        this.x = p.getX();
        this.y = p.getY();
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


    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


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

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }

    /**
     * translate point.
     * the method gets a x and a y and returns an new point moved by that x and a y
     *
     * @param deltaX the delta x
     * @param deltaY the delta y
     * @return the point translated to a new location
     */
    public Point translate(double deltaX, double deltaY) {
        return new Point(this.x + deltaX, this.y + deltaY);
    }
}
