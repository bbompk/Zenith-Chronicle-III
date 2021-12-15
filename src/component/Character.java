package component;

import entity.Player;
import entity.Tile;
import logic.GameManager;
import logic.SceneManager;
import util.GameUtil;

public abstract class Character extends Entity implements Fallable{
	

	//Stats
	protected int hp;
	protected int maxHp;
	protected int atk;
	protected boolean alive;
	protected boolean needRemove;
	
	protected double moveSpeed;
	protected int justTakeDamage;

	public Character(double x, double y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
		justTakeDamage = 0;
		alive = true;
	}

	public Character(double x, double y, int r) {
		super(x, y, r);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		justTakeDamage = justTakeDamage == 0 ? justTakeDamage : justTakeDamage -1;
		int f = fall(); 
 	}
	
	
	
	
	protected void moveRight(double moveSpeed) {
		if(getX() < SceneManager.getInstance().getRightBound()) increaseX(moveSpeed);;
		
		for(Tile tile : SceneManager.getInstance().getTiles()) {
			if( (tile.getUpperBound() < getY()+getH() && tile.getUpperBound() > getY()) || (tile.getLowerBound() < getY()+getH() && tile.getLowerBound() > getY()) || 
					(getY() >= tile.getUpperBound() && getY() <= tile.getLowerBound() && getY()+getH() >= tile.getUpperBound() && getY()+getH() <= tile.getLowerBound()) ) {
				if(getX()+getW() > tile.getLeftBound() -1  && getX() < tile.getLeftBound() && !tile.isTransparent()) {
					if(this instanceof Player && Player.WallHacks) return;
					else setX(tile.getLeftBound()-getW() - 1);
				}
			}
		}
			
		if(getX() > SceneManager.getInstance().getRightBound()- getW()) setX(SceneManager.getInstance().getRightBound() - getW());

	}
	
	protected void moveLeft(double moveSpeed) {
		if(getX() > SceneManager.getInstance().getLeftBound()) increaseX(-moveSpeed);;
		
		for(Tile tile : SceneManager.getInstance().getTiles()) {
			if( (tile.getUpperBound() < getY()+getH() && tile.getUpperBound() > getY()) || (tile.getLowerBound() < getY()+getH() && tile.getLowerBound() > getY()) || 
					(getY() >= tile.getUpperBound() && getY() <= tile.getLowerBound() && getY()+getH() >= tile.getUpperBound() && getY()+getH() <= tile.getLowerBound()) ) {
				if(getX()+getW() >= tile.getRightBound() && getX() < tile.getRightBound() + 1 && !tile.isTransparent()) {
					if(this instanceof Player && Player.WallHacks) return;
					else setX(tile.getRightBound() + 1);
				}
			}
		}
		
		if(getX() < SceneManager.getInstance().getLeftBound()) setX(SceneManager.getInstance().getLeftBound());

	}
	
	public void changeMaxHp(int hp) {
		this.maxHp += hp;
		this.changeHp(hp);;
	}
	
	public void takeDamage(int x) {
		if(hp > 0) {
			changeHp(-x);
			alive = false;
			die();
		}
	}

	protected abstract void die();

	public void changeHp(int hp) {
		this.hp += hp;
		hp = (hp>maxHp) ? maxHp : (hp<0) ? 0 : hp;
	}
	
	public void changeAtk(int atk) {
		this.atk += atk;
	}
	
	public int getAtk(){
		return atk;
	}

	public boolean isAlive() {
		return alive;
	}

	public int getHp() {
		return hp;
	}

	public int getMaxHp() {
		return maxHp;
	}
	
}
