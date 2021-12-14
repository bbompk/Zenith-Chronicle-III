package entity;

import java.util.ArrayList;

import component.Entity;
import component.Sprite;

public class Item extends Entity {	
	
	private static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private int type;

	public Item(double x, double y) {
		super(x, y, 50, 50);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public Sprite getImage() {
		// TODO Auto-generated method stub
		return sprites.get(type);
	}
	
	public static void setUp() {
		sprites.add(new Sprite("sprite/powerup/potion_red.png"));
		sprites.add(new Sprite("sprite/powerup/apple.png"));
		sprites.add(new Sprite("sprite/powerup/flower_red.png"));
		sprites.add(new Sprite("sprite/powerup/fish_blue.png"));
	}
	

}
