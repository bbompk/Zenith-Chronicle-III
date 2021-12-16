package entity;

import component.Sprite;
import enity.base.Entity;
import javafx.scene.canvas.GraphicsContext;
import logic.SceneManager;

public class Background extends Entity {
	// Position
	public static final double x = 0;
	public static final double y = 0;
	public static final int width = 1280;
	public static final int height = 720;

	// Images
	private Sprite backgroundSprite;
	

	public Background() {
		// TODO Auto-generated constructor stub
		super(x,y,1280,720);
		backgroundSprite = new Sprite("sprite/background/jungle.png");
	}
	
	public Background(double x, double y, String URL) {
		// TODO Auto-generated constructor stub
		super(x,y,1280,720);
		backgroundSprite = new Sprite(URL);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sprite getImage() {
		// TODO Auto-generated method stub
		return backgroundSprite;
	}
	
	@Override
	public void draw(GraphicsContext gc,boolean f) {
		//System.out.println("draw bg");
		//super.draw(gc, getImage().getImage(), SceneManager.getInstance().getOffsetX(), SceneManager.getInstance().getOffsetY(), width, height);
		gc.drawImage(getImage().getImage(), x, y, (double) width, (double) height);
	}

}
