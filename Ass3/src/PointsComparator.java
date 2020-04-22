// ID 316044809


import java.util.Comparator;

/**
 * The class Points comperator.
 */
public class PointsComparator implements Comparator<Point> {

    private final Point compareTo;

    /**
     * Instantiates a new Points comparator.
     *
     * @param compareTo the compare to
     */
    public PointsComparator(Point compareTo) {
        this.compareTo = compareTo;
    }

    @Override
    public int compare(Point p1, Point p2) {
        return Double.compare(compareTo.distance(p1), compareTo.distance(p2));
    }
}
