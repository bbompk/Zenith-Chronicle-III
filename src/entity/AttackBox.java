package entity;

import component.Sprite;
import enity.base.Collidable;
import enity.base.Entity;

class AttackBox extends Entity implements Collidable{

	public AttackBox(double x, double y, int w, int h) {super(x, y, w, h);}

	@Override
	public void checkCollide() {}

	@Override
	public void update() {}

	@Override
	public Sprite getImage() {return null;}
	
}