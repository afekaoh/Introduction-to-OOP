// ID 316044809
package game.elements.shapes;

import java.awt.Color;

/**
 * The class Draw shapes.
 */
public final class DrawShapes {

    private static final Color[] GREEN_COLORS = {
            Color.decode("#BFE6B1"),
            Color.decode("#91CA87"),
            Color.decode("#62AE5C"),
            Color.decode("#349132"),
            Color.decode("#057507"),
    };

    private static final Color[] DEFAULT_COLORS = {
            Color.RED,
            Color.ORANGE,
            Color.YELLOW,
            Color.GREEN,
            Color.CYAN,
            Color.BLUE,
            Color.MAGENTA
    };

//    public static Polygon getCloud() {
//        Polygon cloud = new Polygon();
//        CLOUD.forEach(p -> cloud.addPoint((int) p.getX(), (int) p.getY()));
//        return cloud;
//    }
//
//    public static Polygon getCloud(int deltaX, int deltaY) {
//
//    }

    public static Color[] getColorSpace(String color) {
        switch (color) {
            case "Green":
                return GREEN_COLORS;
            default:
                return DEFAULT_COLORS;
        }
    }
}
