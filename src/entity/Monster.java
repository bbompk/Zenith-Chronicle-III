package entity;

import java.util.ArrayList;

import component.Enemy;
import component.Sprite;
import logic.SceneManager;

public class Monster extends Enemy {
	
	private static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private int type;

	public Monster(double x, double y, int type) {
		super(x, y, 120, 120);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sprite getImage() {
		// TODO Auto-generated method stub
		return null;
	}

}
