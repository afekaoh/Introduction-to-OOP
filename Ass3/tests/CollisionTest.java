import java.awt.Color;
import java.util.ArrayList;

// ID 316044809
public class CollisionTest extends Animation {

    /**
     * Instantiates a new Bouncing ball animation.
     *
     * @param width  the width of the animation
     * @param height the height of the animation
     * @param title  the title of the animation
     */
    public CollisionTest(int width, int height, String title) {
        super(width, height, title);
    }

    public static void main(String[] args) {
        CollisionTest animation = new CollisionTest(800, 600, "test");
        animation.drawGame(null);
    }


    @Override
    public void drawGame(ArrayList<Sprite> list) {
        ArrayList<Sprite> sprites = new ArrayList<>();
        GameEnvironment environment = new GameEnvironment();
        Ball ball = new Ball(new Point(100, 400), 5, Color.CYAN, new Velocity(10, -10));
        sprites.add(ball);
        Block block = new Block(new Point(0, 200), 800, 10);
        sprites.add(block);
        environment.addCollidable(block);
        ball.setEnvironment(environment);
        super.drawGame(sprites);
    }
}
