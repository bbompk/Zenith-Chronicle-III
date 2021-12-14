package entity;

import java.util.ArrayList;
import java.util.Random;

import component.Enemy;
import component.Sprite;
import logic.SceneManager;

public class Monster extends Enemy {
	
	private static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private int type;
	private int randomMove;

	public Monster(double x, double y, int type) {
		super(x, y, 120, 120);
		// TODO Auto-generated constructor stub
		this.moveSpeed = new Random().nextDouble()*4+4;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		double distance = getX() - SceneManager.getInstance().getPlayer().getX();
		if(distance > -800 || distance < 800) {}
		if(distance > 0)moveLeft(moveSpeed);
		if(distance < 0)moveRight(moveSpeed);
		super.update();
	}

	@Override
	public Sprite getImage() {
		// TODO Auto-generated method stub
		return null;
	}

}
