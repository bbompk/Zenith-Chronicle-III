package entity;

import component.Sprite;
import enity.base.Entity;
import javafx.scene.canvas.GraphicsContext;
import logic.SceneManager;

public class TileBackground extends Entity {
	// Position
	public static final double x = 0;
	public static final double y = 0;
	public int width ;
	public int height ;

	// Images
	private static Sprite tileBackgroundSprite;
	
	

	public TileBackground() {
		// TODO Auto-generated constructor stub
		super(x,y,3200,720);
		width = 3200;
		height = 720;
		tileBackgroundSprite = new Sprite("sprite/background/plain_tiles.png");
		
	}
	
	public TileBackground(double x, double y, int w, int h, String URL) {
		// TODO Auto-generated constructor stub
		super(x,y,w,h);
		width = w;
		height = h;
		tileBackgroundSprite = new Sprite(URL);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sprite getImage() {
		// TODO Auto-generated method stub
		return tileBackgroundSprite;
	}
	
	@Override
	public void draw(GraphicsContext gc,boolean f) {
		//System.out.println("draw bg");
		//super.draw(gc, getImage().getImage(), SceneManager.getInstance().getOffsetX(), SceneManager.getInstance().getOffsetY(), width, height);
		super.draw(gc, tileBackgroundSprite.getImage(), x, y, width, height);
	}

}
