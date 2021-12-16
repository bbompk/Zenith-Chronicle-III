package enity.base;

import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import logic.SceneManager;

public abstract class Enemy extends Character {

	public Enemy(double x, double y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void checkCollide() {}
	
	public void takeDamage(int x) {
		super.takeDamage(x);
		justTakeDamage += 30;	
	}
	
	public void die() {
		new Thread(() -> {
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			needRemove =true;
		}).start();
		
	}

}
