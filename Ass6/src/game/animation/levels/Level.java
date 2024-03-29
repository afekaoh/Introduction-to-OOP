// ID 316044809
package game.animation.levels;

import game.elements.physics.Velocity;
import game.elements.shapes.Point;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The class Level.
 */
public abstract class Level implements LevelInformation {

    /**
     * The Width.
     */
    private final int width;
    /**
     * The Height.
     */
    private final int height;

    /**
     * Instantiates a new Level.
     *
     * @param width  the width
     * @param height the height
     */
    public Level(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        final int numOfBalls = numberOfBalls();
        return IntStream.range(0, numOfBalls)
                        .mapToObj(i -> Velocity.fromAngleAndSpeed(
                                i == numOfBalls / 2 ? 5 : Velocity.map(i, 0, numOfBalls - 1, -45, 45),
                                5
                                                                 ))
                        .collect(Collectors.toList());
    }

    @Override
    public List<Point> initialBallLocation() {
        final Point point = new Point(getWidth() / 2, (3 * getHeight()) / 4);
        return IntStream.range(0, numberOfBalls())
                        .mapToObj(i -> point)
                        .collect(Collectors.toList());
    }

    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleHeight() {
        return height / 30;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getFrameOerSeconds() {
        return 60;
    }
}
