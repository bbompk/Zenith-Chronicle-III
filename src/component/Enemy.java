package component;

import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import logic.SceneManager;

public abstract class Enemy extends Creature implements Collidable{

	public Enemy(double x, double y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GraphicsContext gc,boolean f) {
		boolean t = getX() > SceneManager.getInstance().getPlayer().getX();
		draw(gc,t);
	}

	@Override
	public void checkCollide() {}

}
