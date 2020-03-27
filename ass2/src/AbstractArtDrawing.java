// ID 316044809

import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.util.ArrayList;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * The class Abstract art drawing.
 */
public class AbstractArtDrawing {
    /**
     * The radius of a point to draw.
     */
    public static final int POINT_RADIUS = 3;
    /**
     * The color of a line.
     */
    public static final Color LINE_COLOR = Color.BLACK;

    /**
     * The Width of the art.
     */
    private final int width;
    /**
     * The Height of the art.
     */
    private final int height;
    /**
     * The Gui.
     */
    private final GUI gui;

    /**
     * Instantiates a new Abstract art drawing.
     *
     * @param width the width
     * @param height the height
     */
    public AbstractArtDrawing(final int width, final int height) {
        this.width = width;
        this.height = height;
        // setting up the gui
        gui = new GUI("Abstract Art", this.width, this.height);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(final String[] args) {
        AbstractArtDrawing art = new AbstractArtDrawing(800, 600);
        art.drawRandomLines();
    }

    /**
     * Draw random lines.
     * drawing random lines their middle point and all there intersections.
     */
    private void drawRandomLines() {
        final DrawSurface canvas = gui.getDrawSurface();

        // generating the lines
        final Line[] lines = new Line[10];
        for (int i = 0; i < 10; i++) {
            lines[i] = new Line(width, height);
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
        gui.show(canvas);
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

    /**
     * Draw point.
     *
     * @param canvas the DrawSurface to draw the point to
     * @param point the point to draw
     * @param color the color of the point.
     */
    private void drawPoint(final DrawSurface canvas, final Point point, final Color color) {
        final int x = (int) point.getX();
        final int y = (int) point.getY();
        canvas.setColor(color);
        canvas.fillCircle(x, y, POINT_RADIUS);
    }

    /**
     * Draw line.
     *
     * @param canvas the DrawSurface to draw the line to
     * @param line the line to draw
     */
    private void drawLine(final DrawSurface canvas, final Line line) {
        final int x1 = (int) line.start().getX();
        final int y1 = (int) line.start().getY();
        final int x2 = (int) line.end().getX();
        final int y2 = (int) line.end().getY();
        canvas.setColor(Color.getHSBColor(current().nextFloat(), current().nextFloat(), current().nextFloat()));
        canvas.drawLine(x1, y1, x2, y2);
    }
}
