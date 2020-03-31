// ID 316044809

import biuoop.DrawSurface;

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
        drawAnimation(null);
    }

    /**
     * Draw animation.
     *
     * @param args the args not used can be null
     */
    @Override
    public void drawAnimation(final String[] args) {

        final DrawSurface canvas = getGui().getDrawSurface();

        // generating the lines
        final Line[] lines = new Line[10];
        for (int i = 0; i < 10; i++) {
            lines[i] = new Line(getWidth(), getHeight());
        }

        // finding all intersection points.
        final ArrayList<Point> intersectionPoints = findIntersectionPoints(lines);

        // drawing the lines and the middle points.
        for (final Line line : lines) {
            drawLine(canvas, line);
            drawPoint(canvas, line.middle(), Color.BLUE);
        }

        // drawing the intersection points
        for (final Point point : intersectionPoints) {
            drawPoint(canvas, point, Color.RED);
        }
        getGui().show(canvas);
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
