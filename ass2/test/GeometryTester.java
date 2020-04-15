import java.util.Arrays;

/**
 * This class does some simple tessting of the Point and Line classes.
 */
public class GeometryTester {

    /**
     * Main method, running tests on both the point and the line classes.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        GeometryTester tester = new GeometryTester();
        if (tester.testPoint() & tester.testLine()) {
            System.out.println("all is good");
        }
    }

    /**
     * The method is in charge of testing the Point class.
     *
     * @return true if not mistakes were found, false otherwise.
     */
    public boolean testPoint() {
        boolean mistake = false;
        Point p1 = new Point(12, 2);
        Point p2 = new Point(9, -2);

        if (p1.getX() != 12) {
            System.out.println("Test p1.getX() failed.");
            mistake = true;
        }
        if (p1.getY() != 2) {
            System.out.println("Test p1.getY() failed.");
            mistake = true;
        }
        if (p1.distance(p1) != 0) {
            System.out.println("Test distance to self failed.");
            mistake = true;
        }
        if (p1.distance(p2) != p2.distance(p1)) {
            System.out.println("Test distance symmetry failed.");
            mistake = true;
        }
        if (p1.distance(p2) != 5) {
            System.out.println("Test distance failed.");
            mistake = true;
        }
        if (!p1.equals(p1)) {
            System.out.println("Equality to self failed.");
            mistake = true;
        }
        if (!p1.equals(new Point(12, 2))) {
            System.out.println("Equality failed.");
            mistake = true;
        }
        if (p1.equals(p2)) {
            System.out.println("Equality failed -- should not be equal.");
            mistake = true;
        }

        return !mistake;
    }

    /**
     * The method is in charge of testing the Line class.
     *
     * @return true if not mistakes were found, false otherwise.
     */
    public boolean testLine() {
        boolean mistakes = false;
        Line l1 = new Line(12, 2, 9, -2);
        Line l2 = new Line(0, 0, 20, 0);
        Line l3 = new Line(9, 2, 12, -2);
        Line l4 = new Line(12, 2, 9, 4);
        Line l5 = new Line(9, -2, 9, 4);
        Line l6 = new Line(0, 0, 25, 0);

        Line[] lines = {
                new Line(267.0446810274051, 274.5668477112261, 46.81122451334447, 64.28268879119824),
                new Line(251.422218039734, 291.0411319912057, 192.44705500015434, 112.23861205342179),
                new Line(89.28387156180028, 14.355682131522618, 91.8038802616687, 61.13334611395508),
                new Line(18.282016233736087, 7.659353241690847, 288.42886556031794, 65.06803250150297),
                new Line(206.96838135483398, 160.86145820963537, 102.54891539648523, 83.24953940171368),
                new Line(211.38563695915113, 126.67036905910855, 147.64575905348454, 75.7244293778779),
                new Line(223.60748643938035, 207.5652501399821, 151.28853516716734, 52.3831734125492),
                new Line(388.56241971818537, 12.671395582086431, 173.54688053839382, 250.69027257004402),
                new Line(164.8492437383366, 99.98639106582321, 103.44361726706578, 173.02751845115424),
                new Line(208.58639859060327, 163.66011793793902, 50.79111697183096, 2.655977448848168)};

        Point[] points = {
                new Point(236.3086263382217, 245.2193293498537), new Point(205.26532522828026, 215.57844477522306),
                new Point(128.93940647783063, 142.70063646088104), new Point(220.84396193415475, 198.33313484143702),
                new Point(89.74121321185913, 22.845086962839737), new Point(90.85584525096368, 43.535445290574394),
                new Point(65.53668238805025, 17.701402518019705), new Point(199.12571071135733, 155.0322317178855),
                new Point(149.5502909235929, 118.18427985255639), new Point(202.82589048292292, 157.7824665459396),
                new Point(170.78531910314308, 94.21939154716243), new Point(219.82878842241982, 199.45691622083314),
                new Point(198.2148073186347, 153.0776143223886), new Point(156.22992692495313, 110.23894695555776)};
        var newPoints = Arrays.asList(points);
        int counter = 0;
        for (Line line : lines) {
            for (Line other : lines) {
                if (line.isIntersecting(other)) {
                    if (!newPoints.contains(line.intersectionWith(other))) {
                        System.out.println("Test multiple intersection failed.");
                        System.out.println(line.intersectionWith(other));
                        mistakes = true;
                    } else {
                        counter++;
                    }
                }
            }
        }
        if (counter / 2 != points.length) {
            System.out.println("Test multiple intersection failed. " + counter);
            mistakes = true;
        }

        if (!l1.isIntersecting(l2)) {
            System.out.println("Test isIntersecting failed (1).");
            mistakes = true;
        }
        if (l1.isIntersecting(new Line(0, 0, 1, 1))) {
            System.out.println("Test isIntersecting failed (2).");
            mistakes = true;
        }
        Point intersectL1L2 = l1.intersectionWith(l2);
        if (!l1.middle().equals(intersectL1L2)) {
            System.out.println("Test intersectionWith middle failed.");
            mistakes = true;
        }
        if (l1.equals(l3)) {
            System.out.println("Test equals failed.");
            mistakes = true;
        }
        if (l2.isIntersecting(l6)) {
            System.out.println(l2.intersectionWith(l6));
            System.out.println("Test isIntersecting failed (3).");
            mistakes = true;
        }
        if (!l1.isIntersecting(l4)) {
            System.out.println("Test isIntersecting failed (4).");
            mistakes = true;
        } else {
            final Point p = new Point(12, 2);
            if (!p.equals(l1.intersectionWith(l4))) {
                System.out.println(l1.intersectionWith(l4));
                mistakes = true;
            }
        }

        if (!l1.isIntersecting(l5)) {
            System.out.println("Test isIntersecting failed (5).");
            mistakes = true;
        } else {
            final Point p = new Point(9, -2);
            if (!p.equals(l1.intersectionWith(l5))) {
                System.out.println(l1.intersectionWith(l5));
                mistakes = true;
            }
        }

        return !mistakes;
    }
}
