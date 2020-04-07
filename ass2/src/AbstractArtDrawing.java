// ID 316044809

import java.awt.Color;
import java.util.ArrayList;

/**
 * The class Abstract art drawing.
 */
public class AbstractArtDrawing extends Animation {

    /**
     * Instantiates a new Abstract art drawing.
     *
     * @param width the width of the art
     * @param height the height of the art
     * @param title the title of the art
     */
    public AbstractArtDrawing(final int width, final int height, final String title) {
        super(width, height, title);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(final String[] args) {
        AbstractArtDrawing art = new AbstractArtDrawing(400, 300, "Abstract Art Drawing");
        art.drawRandomLines();
    }

    /**
     * Draw random lines.
     * drawing random lines their middle point and all there intersections.
     */
    private void drawRandomLines() {

        // generating the lines
        final Line[] lines = createRandomLines(10);

        // finding all intersection points.
        final ArrayList<Point> intersectionPoints = findIntersectionPoints(lines);

        // drawing the lines and the middle points.
        for (final Line line : lines) {
            drawLine(line, BLACK);
            drawPoint(line.middle(), Color.BLUE, POINT_RADIUS);
        }

        // drawing the intersection points
        for (final Point point : intersectionPoints) {
            drawPoint(point, Color.RED, POINT_RADIUS);
        }
        show();
    }


    /**
     * Find intersection points.
     * gets an array of lines and returns all their intersections.
     *
     * @param lines an array of lines
     * @return an arrayList of all the intersection points
     */
    private ArrayList<Point> findIntersectionPoints(final Line[] lines) {
        final ArrayList<Point> intersectionPoints = new ArrayList<>();
        // checking all the lines against all the other lines
        for (final Line line : lines) {
            for (final Line other : lines) {
                if (line.equals(other)) {
                    continue;
                }
                if (line.isIntersecting(other)) {
                    intersectionPoints.add(line.intersectionWith(other));
                }
            }
        }
        return intersectionPoints;
    }
}
