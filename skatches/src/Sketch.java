import processing.core.PApplet;
import java.util.ArrayList;

public class Sketch extends PApplet{
	private ArrayList<Ball> balls = new ArrayList<>();


	public void settings(){
	size(500,500);
		balls.add(new Ball(this, width/2, height/2));
}

public void draw(){
	background(42);
	for (Ball b : balls){
		b.step();
		b.render();
	}

}

	public void mousePressed() {
		if(mouseButton == LEFT)
			balls.add(new Ball(this, mouseX, mouseY));
		else
			try {
				balls.remove(balls.get(balls.size()-1));
			} catch (Exception e) {
				println("no balls to delete");
			}
	}
	public static void main(String[] args){
		String[] processingArgs = {"MySketch"};
		Sketch mySketch = new Sketch();
		PApplet.runSketch(processingArgs, mySketch);
	}
}
