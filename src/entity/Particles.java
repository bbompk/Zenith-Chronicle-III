package entity;

import component.Entity;
import component.Sprite;

public class Particles extends Entity {
	
	private int lifetime;
	private static Sprite particleSprite;
	
	
	public Particles(double x, double y, int w, int h, int lifetime, String URL) {
		super(x, y, w, h);
		this.lifetime = lifetime;
		this.particleSprite = new Sprite(URL);
		// TODO Auto-generated constructor stub
	}

	public Particles(double x, double y, int r,  int lifetime, String URL) {
		super(x, y, r);
		this.lifetime = lifetime;
		this.particleSprite = new Sprite(URL);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		lifetime--;
		if(lifetime < 0) lifetime = -1;
	}
	
	@Override
	public Sprite getImage() {
		// TODO Auto-generated method stub
		return particleSprite;
	}

}
