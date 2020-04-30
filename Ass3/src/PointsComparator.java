// ID 316044809

import java.util.Comparator;

/**
 * The class Points comparator - to compare 2 points based on their distance to a third point.
 */
public final class PointsComparator implements Comparator<Point> {

    private final Point compareTo;

    /**
     * Instantiates a new Points comparator.
     *
     * @param compareTo the compare to
     */
    public PointsComparator(final Point compareTo) {
        this.compareTo = compareTo;
    }

    @Override
    public int compare(final Point p1, final Point p2) {
        return Double.compare(compareTo.distance(p1), compareTo.distance(p2));
    }
}
