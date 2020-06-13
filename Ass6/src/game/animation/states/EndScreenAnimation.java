// ID 316044809
package game.animation.states;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.animation.Animation;

import java.awt.Color;

public class EndScreenAnimation implements Animation {
    private final KeyboardSensor keyboardSensor;
    private String text;

    public EndScreenAnimation(KeyboardSensor keyboardSensor, boolean win, int score) {
        this.keyboardSensor = keyboardSensor;
        if (win) {
            text = "You Win! ";
        } else {
            text = "Game Over. ";
        }
        text += "Your score is " + score;
    }

    @Override
    public boolean shouldStop() {
        return true;
    }

    @Override
    public void doOneFrame(final DrawSurface canvas) {
        canvas.setColor(Color.BLACK);
        canvas.drawText(canvas.getWidth() / 2, canvas.getHeight() / 2, text, 32);
    }

    @Override
    public void drawBackground(final DrawSurface canvas) {
        canvas.setColor(Color.green);
        canvas.fillRectangle(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
