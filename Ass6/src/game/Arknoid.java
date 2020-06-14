// ID 316044809
package game;

import game.animation.AnimationRunner;
import game.animation.levels.LevelInformation;
import game.animation.levels.factory.AbstractLevelFactory;
import game.animation.levels.factory.LevelFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Arknoid {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String TITLE = "Arknoid";


    public void play(String[] args) {
        final AnimationRunner animationRunner = new AnimationRunner(WIDTH, HEIGHT, TITLE);
        final AbstractLevelFactory levelFactory = new AbstractLevelFactory(WIDTH, HEIGHT);
        List<LevelInformation> levels = Arrays.stream(args)
                                              .filter(this::isNum)
                                              .map(Integer::parseInt)
                                              .filter(n -> (1 <= n && n <= 4))
                                              .map(levelFactory::createFactory)
                                              .map(LevelFactory::createLevel)
                                              .collect(Collectors.toList());
        if (levels.isEmpty()) {
            levels = IntStream.range(1, 5)
                              .mapToObj(levelFactory::createFactory)
                              .map(LevelFactory::createLevel)
                              .collect(Collectors.toList());
        }
        final GameFlow game = new GameFlow(animationRunner);
        game.runLevels(levels);
        game.close();
    }

    private boolean isNum(String s) {
        try {
            Integer.parseInt(s);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
