package entity;

import component.Sprite;
import enity.base.Entity;
import javafx.scene.canvas.GraphicsContext;
import logic.SceneManager;

public class Particles extends Entity {
	
	private int lifetime;
	private boolean inf;
	private Sprite particleSprite;
	
	
	public Particles(double x, double y, int w, int h, int lifetime, String URL) {
		super(x, y, w, h);
		this.lifetime = lifetime;
		inf = lifetime < 0;
		this.particleSprite = new Sprite(URL);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(!inf)lifetime--;
		if(lifetime < 0 && !inf) SceneManager.getInstance().getProps().remove(this);
	}
	
	
	@Override
	public Sprite getImage() {
		// TODO Auto-generated method stub
		return particleSprite;
	}

}
