// ID 316044809
package game.elements.shapes;

import java.awt.Polygon;
import java.util.List;

/**
 * The class Draw shapes.
 */
public class DrawShapes {
    private static final List<Point> CLOUD = List.of(
            new Point(173, 146), new Point(170, 148), new Point(167, 150),
            new Point(164, 152), new Point(161, 153), new Point(158, 154), new Point(155, 156),
            new Point(152, 156), new Point(149, 156), new Point(146, 156), new Point(142, 156),
            new Point(139, 156), new Point(136, 155), new Point(133, 154), new Point(129, 153),
            new Point(127, 152), new Point(124, 150), new Point(121, 148), new Point(119, 146),
            new Point(116, 143), new Point(114, 140), new Point(112, 138), new Point(111, 135),
            new Point(110, 133), new Point(108, 129), new Point(107, 126), new Point(106, 123),
            new Point(106, 119), new Point(106, 116), new Point(106, 113), new Point(106, 110),
            new Point(107, 106), new Point(108, 104), new Point(110, 100), new Point(111, 97),
            new Point(113, 95), new Point(114, 92), new Point(117, 89), new Point(119, 87),
            new Point(121, 85), new Point(124, 83), new Point(127, 81), new Point(130, 80),
            new Point(133, 79), new Point(136, 78), new Point(139, 78), new Point(140, 74),
            new Point(142, 71), new Point(143, 68), new Point(145, 66), new Point(147, 63),
            new Point(150, 60), new Point(152, 59), new Point(154, 57), new Point(157, 55),
            new Point(160, 53), new Point(163, 52), new Point(166, 51), new Point(169, 50),
            new Point(173, 49), new Point(176, 49), new Point(179, 49), new Point(182, 49),
            new Point(186, 50), new Point(189, 51), new Point(192, 52), new Point(195, 53),
            new Point(198, 55), new Point(201, 57), new Point(203, 59), new Point(205, 61),
            new Point(207, 63), new Point(210, 66), new Point(211, 67), new Point(213, 64),
            new Point(215, 62), new Point(217, 59), new Point(219, 57), new Point(222, 55),
            new Point(224, 53), new Point(227, 51), new Point(230, 49), new Point(233, 48),
            new Point(236, 47), new Point(239, 46), new Point(243, 46), new Point(246, 45),
            new Point(249, 46), new Point(253, 46), new Point(256, 47), new Point(259, 47),
            new Point(262, 49), new Point(265, 50), new Point(268, 51), new Point(270, 53),
            new Point(273, 55), new Point(276, 57), new Point(278, 60), new Point(280, 62),
            new Point(281, 65), new Point(283, 68), new Point(284, 71), new Point(285, 74),
            new Point(286, 78), new Point(287, 80), new Point(290, 80), new Point(293, 81),
            new Point(297, 81), new Point(300, 82), new Point(302, 83), new Point(306, 84),
            new Point(308, 86), new Point(312, 88), new Point(314, 90), new Point(316, 92),
            new Point(319, 95), new Point(321, 97), new Point(322, 100), new Point(324, 102),
            new Point(325, 106), new Point(327, 108), new Point(327, 112), new Point(328, 115),
            new Point(328, 118), new Point(328, 121), new Point(328, 125), new Point(327, 128),
            new Point(327, 131), new Point(326, 135), new Point(325, 137), new Point(323, 140),
            new Point(321, 143), new Point(320, 146), new Point(317, 148), new Point(315, 150),
            new Point(312, 152), new Point(309, 154), new Point(306, 156), new Point(304, 157),
            new Point(300, 158), new Point(297, 159), new Point(294, 160), new Point(291, 160),
            new Point(287, 160), new Point(284, 160), new Point(283, 163), new Point(281, 165),
            new Point(280, 169), new Point(278, 171), new Point(276, 174), new Point(274, 177),
            new Point(272, 179), new Point(270, 180), new Point(267, 182), new Point(264, 184),
            new Point(261, 186), new Point(258, 187), new Point(255, 188), new Point(251, 188),
            new Point(248, 188), new Point(245, 188), new Point(241, 188), new Point(238, 188),
            new Point(235, 187), new Point(232, 186), new Point(229, 185), new Point(226, 184),
            new Point(223, 182), new Point(220, 180), new Point(218, 178), new Point(216, 176),
            new Point(214, 173), new Point(212, 171), new Point(210, 168), new Point(207, 167),
            new Point(203, 167), new Point(200, 167), new Point(197, 166), new Point(194, 165),
            new Point(191, 164), new Point(188, 162), new Point(185, 161), new Point(182, 159),
            new Point(180, 156), new Point(178, 154), new Point(176, 152), new Point(174, 149)
                                                    );

    public static Polygon getCloud() {
        Polygon cloud = new Polygon();
        CLOUD.forEach(p -> cloud.addPoint((int) p.getX(), (int) p.getY()));
        return cloud;
    }

    public static Polygon getCloud(int deltaX, int deltaY) {
        Polygon cloud = getCloud();
        cloud.translate(deltaX, deltaY);
        return cloud;
    }


}
