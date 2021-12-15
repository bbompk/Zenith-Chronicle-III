package entity;

import java.util.Random;

import component.BossStatus;
import component.Enemy;
import component.PlayerStatus;
import component.Sprite;
import javafx.scene.canvas.GraphicsContext;
import logic.SceneManager;
import util.GameUtil;

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
	private AttackBox attackBox;
	private BossStatus status;
	private int atkCooldown;
	private int prepareAttack;
	
	public Boss(double x, double y) {
		super(x, y, 200, 250);
		// TODO Auto-generated constructor stub
		direction = -1;
		moveSpeed = 3;
		hp = 500000;
		atk = 3;
		status = BossStatus.WALK;
		setAttackBox(200);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		//AttackBox range = new AttackBox(getX()+(direction*attackRange), );
		Player player = SceneManager.getInstance().getPlayer();
		if(player.collideWith(attackBox) && alive) {
			
			attack();	
		}
		
		justTakeDamage = justTakeDamage == 0 ? justTakeDamage : justTakeDamage -1;
		if(status.equals(BossStatus.PREPARING)) {
			
		}else if(status.equals(BossStatus.STRIKING)) {
			
		}else {
			if(fall()==0 && alive) {
				double distance = getX() - SceneManager.getInstance().getPlayer().getX();
				if(getY()+getH() == SceneManager.getInstance().getPlayer().getPrevGround() && justTakeDamage == 0) {
					if(distance > -800 || distance < 800) {
						if(distance > 0) {direction = 0; moveLeft(moveSpeed);}
						if(distance < 0) {direction = 1; moveRight(moveSpeed);}
					}
				}
	
			}
		}
	}
	
	@Override
	protected int fall() {
		prevy = getY();
		increaseY(getVy());
		setVy(getVy() + GameUtil.gravity);
		for(Tile tile : SceneManager.getInstance().getTiles()) {
			if( (getX() >= tile.getLeftBound() && getX() <= tile.getRightBound()) || (getX()+getW() >= tile.getLeftBound() && getX()+getW() <= tile.getRightBound()) ) {
				if(getY()+getH() > tile.getUpperBound() && getY() <= tile.getUpperBound()  ) {
					if(!tile.isTransparent()) {
						setY(tile.getUpperBound()  - getH());
						setVy(0);
					} else {
						if(prevy +getH() <= tile.getUpperBound() && prevy <= tile.getUpperBound() ) {
							setY(tile.getUpperBound()  - getH());
							
							setVy(0);
						}
					}
				}
				
			}
		}
		attackBox.setY(getY());
		if(prevy == getY()) { return 0; }
		if(prevy < getY()) return 1;
		if(prevy > getY()) return -1;
		return 0;
	}
	
	@Override
	protected void moveRight(double moveSpeed) {
		if(getX() < SceneManager.getInstance().getRightBound()) increaseX(moveSpeed);;
		
		for(Tile tile : SceneManager.getInstance().getTiles()) {
			if( (tile.getUpperBound() < getY()+getH() && tile.getUpperBound() > getY()) || (tile.getLowerBound() < getY()+getH() && tile.getLowerBound() > getY()) || 
					(getY() >= tile.getUpperBound() && getY() <= tile.getLowerBound() && getY()+getH() >= tile.getUpperBound() && getY()+getH() <= tile.getLowerBound()) ) {
				if(getX()+getW() > tile.getLeftBound() -1  && getX() < tile.getLeftBound() && !tile.isTransparent()) {
					
					setX(tile.getLeftBound()-getW() - 1);
				}
			}
		}
			
		if(getX() > SceneManager.getInstance().getRightBound()- getW()) setX(SceneManager.getInstance().getRightBound() - getW());
		direction = 1;
		attackBox.setX(getX()+(direction*attackRange));
		
		
	}
	
	@Override
	protected void moveLeft(double moveSpeed) {
		if(getX() > SceneManager.getInstance().getLeftBound()) increaseX(-moveSpeed);;
		
		for(Tile tile : SceneManager.getInstance().getTiles()) {
			if( (tile.getUpperBound() < getY()+getH() && tile.getUpperBound() > getY()) || (tile.getLowerBound() < getY()+getH() && tile.getLowerBound() > getY()) || 
					(getY() >= tile.getUpperBound() && getY() <= tile.getLowerBound() && getY()+getH() >= tile.getUpperBound() && getY()+getH() <= tile.getLowerBound()) ) {
				if(getX()+getW() >= tile.getRightBound() && getX() < tile.getRightBound() + 1 && !tile.isTransparent()) {
					
					setX(tile.getRightBound() + 1);
				}
			}
		}
		
		if(getX() < SceneManager.getInstance().getLeftBound()) setX(SceneManager.getInstance().getLeftBound());
		direction = -1;
		attackBox.setX(getX()+(direction*attackRange));
		
	}
	
	private void attack() {
		new Thread(() -> {
			status = BossStatus.PREPARING;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			status = BossStatus.STRIKING;
			if(alive) {
				AttackBox a = new AttackBox(attackBox.getX(), attackBox.getY(), attackBox.getH(), attackBox.getH());
				Player player = SceneManager.getInstance().getPlayer();
				if(player.collideWith(a)) {
					player.takeDamage(atk);
				}
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			status = BossStatus.WALK;
		}).start();
	}
	
	

	@Override
	public Sprite getImage() {
		// TODO Auto-generated method stub
		if(!alive)return death;
		if(status.equals(BossStatus.PREPARING)) return pre_strike;
		if(status.equals(BossStatus.STRIKING)) return strike;
		if(justTakeDamage > 0)return hurt;
		return run;
	}
	
	@Override
	public void draw(GraphicsContext gc, boolean f) {
		
		if(!isAlive()) {
			//TODO death animation;
			
		}
		
		attackBox.drawHitBox(gc);
		if(status.equals(BossStatus.PREPARING) || status.equals(BossStatus.STRIKING)) {
			if(direction < 0) {
				super.draw(gc, getImage().getImage(), getX()-290, getY()-110, 780, 360);
			}else {
				super.draw(gc, getImage().getImage(), getX()-290+780, getY()-110, -780, 360);
			}
		}else {
			if(direction < 0) {
				super.draw(gc, getImage().getImage(), getX()-80, getY()-110, 360, 360);
			}else {
				super.draw(gc, getImage().getImage(), getX()-80+360, getY()-110, -360, 360);
			}
		}
		
	}
	
	@Override
	public void die() {
		new Thread(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			needRemove =true;
		}).start();
		
	}
	

	@Override
	public void takeDamage(int x) {
		if(immune==0) {
			immune += 201;
			super.takeDamage(x);
		}
	}

	public double getAttackRange() {
		return attackRange;
	}

	private void setAttackBox(int atkRange) {
		attackRange = atkRange;
		this.attackBox = new AttackBox(getX()+(direction*atkRange), getY(), atkRange, 250);
	}
	
	
}
