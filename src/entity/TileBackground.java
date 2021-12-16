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
	private Sprite tileBackgroundSprite;
	
	
	
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
