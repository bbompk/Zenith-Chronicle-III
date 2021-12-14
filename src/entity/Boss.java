package entity;

import java.util.Random;

import component.Enemy;
import component.Sprite;

public class Boss extends Enemy {

	private static Random r = new Random();
	private Sprite idle;
	private Sprite death;
	private Sprite run;
	private Sprite hurt;
	private int immune;
	
	public Boss(double x, double y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}

	@Override
	public Sprite getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void takeDamage(int x) {
		if(immune==0) {
			immune += 201;
			super.takeDamage(x);
		}
	}
}
