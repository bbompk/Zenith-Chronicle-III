package entity;

import java.util.Random;

import component.BossStatus;
import component.Enemy;
import component.KeyStatus;
import component.PlayerStatus;
import component.Sprite;
import javafx.scene.canvas.GraphicsContext;
import logic.KeyHandler;
import logic.SceneManager;
import util.GameUtil;

public class Boss extends Enemy {

	private static Random r = new Random();
	private static Sprite idle = new Sprite("sprite/character/boss/idle.gif");
	private static Sprite death = new Sprite("sprite/character/boss/death.gif");;
	private static Sprite run = new Sprite("sprite/character/boss/walk.gif");;
	private static Sprite hurt = new Sprite("sprite/character/boss/hurt.gif");;
	private static Sprite strike = new Sprite("sprite/character/boss/ayyo.gif");; 
	private static Sprite pre_strike = new Sprite("sprite/character/boss/prepare_to_strike.gif");;
	
	
	private int stunImmune;
	private int direction;
	private Boolean isRight;
	private double attackRangeX;
	private double attackRangeY;
	private AttackBox attackBox;
	private BossStatus status;
	
	private int prepareAtk;
	private int attacking;
	
	
	public Boss(double x, double y) {
		super(x, y, 200, 250);
		// TODO Auto-generated constructor stub
		status = BossStatus.WALK;
		direction = -1;
		moveSpeed = 1;
		hp = 800;
		maxHp = 800;
		atk = 20;
		status = BossStatus.WALK;
		prepareAtk = 0;
		attacking = 0;
		isRight = false;
		
		setAttackBox(180, 125);
		
	}
	
	public Boss(double x, double y, int ax, int  ay) {
		super(x, y, 200, 250);
		// TODO Auto-generated constructor stub
		status = BossStatus.WALK;
		direction = -1;
		moveSpeed = 1;
		hp = 800;
		maxHp = 800;
		atk = 20;
		status = BossStatus.WALK;
		prepareAtk = 0;
		attacking = 0;
		isRight = false;
		
		setAttackBox(ax, ay);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		Player player = SceneManager.getInstance().getPlayer();
		if(!player.isAlive()) {
			status = BossStatus.IDLE;
		}
		
		if(player.collideWith(attackBox) && alive && !(status.equals(BossStatus.PREPARING)) && !(status.equals(BossStatus.STRIKING)) && player.isAlive()) {
			if(prepareAtk == 0) {
				prepareAtk += 91;
				//atkSound.play();
				status = BossStatus.PREPARING;
				pre_strike.loadImage(pre_strike.getFilepath());
			}
		}
		
		if(prepareAtk == 46) {
			attacking += 61;
			status = BossStatus.STRIKING;
			strike.loadImage(strike.getFilepath());
			attack();
		}
		
		if(attacking == 31) {
			status = BossStatus.WALK;
		}
		
		if(status.equals(BossStatus.WALK) && attacking == 0){
			if(fall()==0 && alive && stunImmune < 471) {
				double distance = getX() - SceneManager.getInstance().getPlayer().getX();
					if(distance > -800 || distance < 800) {
						if(distance > 0 && !(status.equals(BossStatus.IDLE))) {
							if(isRight) {
								new Thread(()-> {
									status = BossStatus.IDLE;
									try {
										Thread.sleep(1500);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									if(status.equals(BossStatus.IDLE)) {
										isRight = false;
										status = BossStatus.WALK;
									}
								}).start();
							}else {
								direction = -1; moveLeft(moveSpeed);
							}
							
						}
						if(distance < 0  && !(status.equals(BossStatus.IDLE))) {
							if(!isRight) {
								new Thread(()-> {
									status = BossStatus.IDLE;
									try {
										Thread.sleep(1500);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									if(status.equals(BossStatus.IDLE)) {
										isRight = true;
										status = BossStatus.WALK;
									}
									
								}).start();
							}else {
								direction = 1; moveRight(moveSpeed);
							}
							
						}
					}
	
			}
		}
		
		attacking = (attacking == 0) ? attacking : attacking -1 ;
		prepareAtk = (prepareAtk ==0 ) ? prepareAtk : prepareAtk-1;
		justTakeDamage = justTakeDamage == 0 ? justTakeDamage : justTakeDamage -1;
		stunImmune = stunImmune == 0 ? stunImmune : stunImmune -1;
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
		attackBox.setY(getY() + attackRangeY);
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
		attackBox.setX(getX()+(direction*attackRangeX));
		
		
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
		attackBox.setX(getX()+(direction*attackRangeX));
		
	}
	
	private void attack() {
			AttackBox a = new AttackBox(attackBox.getX(), attackBox.getY(), attackBox.getW()+20, attackBox.getH());
			Player player = SceneManager.getInstance().getPlayer();
			if(player.collideWith(a)) {
				player.takeDamage(atk);
			}
				
	}
	
	
	
	@Override
	public Sprite getImage() {
		// TODO Auto-generated method stub
		if(!alive)return death;
		
		if(status.equals(BossStatus.PREPARING)) return pre_strike;
		if(status.equals(BossStatus.STRIKING)) return strike; 
		
		if(justTakeDamage > 0 && stunImmune < 501)  return hurt;  
		if(status.equals(BossStatus.IDLE)) return idle;
		return run;
	}
	
	@Override
	public void draw(GraphicsContext gc, boolean f) {
		
		if(!isAlive()) {
			//TODO death animation;
			
		}
		if(justTakeDamage == 30 && !status.equals(BossStatus.PREPARING) && !status.equals(BossStatus.STRIKING)) getImage() .loadImage(getImage().getFilepath());
		if(alive) attackBox.drawHitBox(gc);
		if(!alive) {
			if(!isRight) {
				super.draw(gc, getImage().getImage(), getX()-90, getY()-230, 480, 480);
			}else {
				super.draw(gc, getImage().getImage(), getX()-90+480, getY()-230, -480, 480);
			}
			
		}
		else if(status.equals(BossStatus.PREPARING) || status.equals(BossStatus.STRIKING) && justTakeDamage == 0 && alive) {
			
			if(!isRight) {
				super.draw(gc, getImage().getImage(), getX()-290, getY()-110, 780, 360);
			}else {
				super.draw(gc, getImage().getImage(), getX()-290+780, getY()-110, -780, 360);
			}
			
		}else if (status.equals(BossStatus.IDLE)) {
			if(!isRight) {
				super.draw(gc, getImage().getImage(), getX()-50, getY()-50, 300, 300);
			}else {
				super.draw(gc, getImage().getImage(), getX()-50+300, getY()-50, -300, 300);
			}
			
		}else {
			if(!isRight) {
				super.draw(gc, getImage().getImage(), getX()-80, getY()-110, 360, 360);
			}else {
				super.draw(gc, getImage().getImage(), getX()-80+360, getY()-110, -360, 360);
			}
		}
		
	}
	
	@Override
	public void die() {
		status = BossStatus.DIE;
		death.loadImage(death.getFilepath());
		
		
		new Thread(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Item.generate(getX() + 75, getY()+100);
			Item.generate(getX() + 150, getY() + 80);
			Powerup.generate(0, (int) getX(), (int) getX()+ (int) getW());
		}).start();
		
		
		
		new Thread(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			needRemove =true;
		}).start();
		
		new Thread(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SceneManager.getInstance().getProps().add(new Portal(SceneManager.getInstance().getRightBound() - 200, 490));
		}).start();
	}
	

	@Override
	public void takeDamage(int x) {
		if(stunImmune==0) {
			stunImmune += 501;
		}
		super.takeDamage(x);
	}

	public double getAttackRangeX() {
		return attackRangeX;
	}

	private void setAttackBox(int atkRangeX, int atkRangeY) {
		attackRangeX = atkRangeX; 
		attackRangeY = atkRangeY;
		this.attackBox = new AttackBox(getX()+(direction*atkRangeX), getY()+(getH() - atkRangeY), atkRangeX, atkRangeY);
	}
	
	
}
