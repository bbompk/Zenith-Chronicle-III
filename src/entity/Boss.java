package entity;

import java.util.Random;

import component.Enemy;
import component.Sprite;

public class Boss extends Enemy {

	private static Random r = new Random();
	private static Sprite idle = new Sprite("sprite/character/boss/idle.gif");
	private static Sprite death = new Sprite("sprite/character/boss/death.gif");;
	private static Sprite run = new Sprite("sprite/character/boss/walk.gif");;
	private static Sprite hurt = new Sprite("sprite/character/boss/hurt.gif");;
	private static Sprite pre_strike = new Sprite("sprite/character/boss/prepare_to_strike.gif");;
	private static Sprite strike = new Sprite("sprite/character/boss/strike.gif");; 
	private int immune;
	private int direction;
	private double attackRange;
	
	public Boss(double x, double y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		//AttackBox range = new AttackBox(getX()+(direction*attackRange), );
		
		
		fall();
	}
	
	private void attack() {
		
	}
	
	

	@Override
	public Sprite getImage() {
		// TODO Auto-generated method stub
		if(!alive)return death;
		if(justTakeDamage > 0)return hurt;
		return run;
	}

	@Override
	public void takeDamage(int x) {
		if(immune==0) {
			immune += 201;
			super.takeDamage(x);
		}
	}
}
