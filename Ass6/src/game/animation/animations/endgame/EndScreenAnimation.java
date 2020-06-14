// ID 316044809
package game.animation.animations.endgame;

import biuoop.DrawSurface;
import game.animation.Animation;

import java.awt.Color;

public abstract class EndScreenAnimation implements Animation {
    private String text;

    public EndScreenAnimation(int score) {
        text = getEndText();
        text += " Your score is " + score;
    }

    protected abstract String getEndText();

    @Override
    public boolean shouldStop() {
        return true;
    }

    @Override
    public void doOneFrame(final DrawSurface canvas) {
        canvas.setColor(Color.BLACK);
        canvas.drawText(0, canvas.getHeight() / 2, text, 32);
    }

    @Override
    public void drawBackground(final DrawSurface canvas) {
        canvas.setColor(Color.green);
        canvas.fillRectangle(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
