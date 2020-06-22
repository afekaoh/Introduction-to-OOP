// ID 316044809
package game.elements.shapes;

import java.awt.Color;

/**
 * The class Draw shapes.
 */
public final class Colors {

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

    /**
     * Get color space color [ ].
     *
     * @param color the color
     * @return the color [ ]
     */
    public static Color[] getColorSpace(String color) {
        if (color.equals("Green")) {
            return GREEN_COLORS;
        }
        return DEFAULT_COLORS;
    }
}
