package entity;

import component.Collidable;
import component.Creature;
import component.Enemy;
import component.Entity;
import component.Fallable;
import component.KeyStatus;
import component.PlayerStatus;
import component.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import logic.KeyHandler;
import logic.SceneManager;
import util.GameUtil;

public class Player extends Creature implements Collidable, Fallable{
	
	//Utility
	private int atkable = 0;
	private int immune = 0;
	private int dashing = 0;
	protected static PlayerStatus face = PlayerStatus.RIGHT;
//	private static final AudioClip atkSound = new AudioClip(ClassLoader.getSystemResource("attackk.wav").toString());
	
		
	// IDLE/RUN
	private PlayerStatus status;
	private PlayerStatus lastFrameStatus;
	private int direction;
	private double moveSpeed = 7.0;
	private double dashSpeed ;
	private double dashSpeedMultiplier = 10/7;
	
	// Jumping
	private PlayerStatus jumpStatus;
	private double initJumpSpeed = 10;
	private double jumpCount;
	private double prevGround;
	
	// Position
	public static boolean needResetPos = false;
	
	// Images
	private static final Sprite idle = new Sprite("sprite/character/player/idle.gif");
	private static final Sprite run = new Sprite("sprite/character/player/run.gif");
	private static final Sprite jump_up = new Sprite("sprite/character/player/jump_up.gif");
	private static final Sprite jump_down = new Sprite("sprite/character/player/jump_down.gif");
	private static final Sprite death = new Sprite("sprite/character/player/death.gif");
	private static final Sprite attack = new Sprite("sprite/character/player/attack.gif");
	private static final Sprite hurt = new Sprite("sprite/character/player/hurt.gif");
	private static final Sprite roll = new Sprite("sprite/character/player/roll_4_frame.gif");
	
	public Player() {
		// TODO Auto-generated constructor stub
		
		super(150,550,120,120);
		lastFrameStatus = PlayerStatus.IDLE;
		status = PlayerStatus.IDLE;
		jumpStatus = PlayerStatus.ONGROUND;
		direction = 0;
		prevGround = 550+120;
		
		hp =100;
		maxHp =100;
		atk = 20;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		prevy = getY();
		prevx = getX();
		
		lastFrameStatus = status;
		status = PlayerStatus.RUN;
		if(needResetPos) {
			needResetPos = false;
		}
		if(atkable == 0 && jumpStatus.equals(PlayerStatus.ONGROUND) && KeyHandler.getInstance().getKeyStatus(83).equals(KeyStatus.DOWN) && !status.equals(PlayerStatus.DASHING)) {
			atkable += 61;
			//atkSound.play();
			attack();
			attack.loadImage(attack.getFilepath());
		}
		if(dashing == 0 && KeyHandler.getInstance().getKeyStatus(17).equals(KeyStatus.DOWN) && !status.equals(PlayerStatus.DASHING)) {
			dashing += 41;
			roll.loadImage(roll.getFilepath());
		}
		if(atkable > 41) {
			status = PlayerStatus.ATTACKING;
		}else if(dashing > 21) {
			status = PlayerStatus.DASHING;
			dash();
			
		}else {
			if(KeyHandler.getInstance().getKeyStatus(68).equals(KeyStatus.DOWN)) {
				moveRight();
			}
			if(KeyHandler.getInstance().getKeyStatus(65).equals(KeyStatus.DOWN)) {
				moveLeft();
			}
			if(KeyHandler.getInstance().getKeyStatus(68).equals(KeyStatus.FREE) && KeyHandler.getInstance().getKeyStatus(65).equals(KeyStatus.FREE)) {
				direction = 0;
			}else if(KeyHandler.getInstance().getKeyStatus(68).equals(KeyStatus.DOWN) && KeyHandler.getInstance().getKeyStatus(65).equals(KeyStatus.DOWN)) {
				direction = 0;
			}
			if(direction == 0) {status = PlayerStatus.IDLE;}
			if(KeyHandler.getInstance().getKeyStatus(32).equals(KeyStatus.DOWN)) {
				prevGround = getY()+getH();
				jump();
			}
		}
		
		int fallBack;
		if(!status.equals(PlayerStatus.DASHING)) {
			fallBack = fall();
		} else fallBack = 0;
		
		if(fallBack == -1) {
			jumpStatus = PlayerStatus.GOINGUP;
		}
		if(fallBack == 1) {
			jumpStatus = PlayerStatus.FALLING;
		}
		
		if(immune ==0) {
			for(Enemy e:SceneManager.getInstance().getEnemy()) {
				if(e.collideWith(this)) {
					takeDamage(e.getAtk());
					immune += 201;
				}
			}
		}
<<<<<<< HEAD
		justTakeDamage = justTakeDamage == 0 ? justTakeDamage : justTakeDamage -1;
||||||| 5d38bdb
=======
		
		// if(prevy != getY()) System.out.println(getY());
>>>>>>> 5e04ac973293f8246bdf62c33821359248258250
		dashing = (dashing == 0) ? dashing : dashing - 1;
		atkable = (atkable == 0) ? atkable : atkable - 1;
		immune = (immune == 0) ? immune : immune - 1;
	}
	
	
	@Override
	protected int fall() {
		prevy = getY();
		increaseY(getVy());
		if(lastFrameStatus.equals(PlayerStatus.DASHING)) setVy(0);
		setVy(getVy() + GameUtil.gravity);
		for(Tile tile : SceneManager.getInstance().getTiles()) {
			if( (getX() >= tile.getLeftBound() && getX() <= tile.getRightBound()) || (getX()+getW() >= tile.getLeftBound() && getX()+getW() <= tile.getRightBound()) ) {
				if(getY()+getH() > tile.getUpperBound() && getY() <= tile.getUpperBound() && !(jumpStatus.equals(PlayerStatus.GOINGUP)) ) {
					if(!tile.isTransparent()) {
						setY(tile.getUpperBound()  - getH());
						jumpStatus = PlayerStatus.ONGROUND;
						setVy(0);
					} else {
						if(prevy +getH() <= tile.getUpperBound() && prevy <= tile.getUpperBound() && !(jumpStatus.equals(PlayerStatus.GOINGUP))) {
							setY(tile.getUpperBound()  - getH());
							jumpStatus = PlayerStatus.ONGROUND;
							setVy(0);
						}
					}
				}
				
				if(getY()+getH() >= tile.getLowerBound() && getY() < tile.getLowerBound() && !tile.isTransparent()) {
					setY(tile.getLowerBound() );
					jumpStatus = PlayerStatus.FALLING;
					setVy(0);
				}
			}
		}
		if(prevy == getY()) {prevGround = getY()+getH(); return 0;}
		if(prevy < getY()) return 1;
		if(prevy > getY()) return -1;
		return 0;
	}
	
	private void jump() {
		
		if(jumpStatus.equals(PlayerStatus.ONGROUND)) {
			setVy(-initJumpSpeed);
			jumpStatus = PlayerStatus.GOINGUP;
		}
		
	}
	
	private void moveRight() {
		
		super.moveRight(moveSpeed);
		
		if(getX() > SceneManager.getInstance().getRightBound()- getW()) setX(SceneManager.getInstance().getRightBound() - getW());
		
		if(getX() >= SceneManager.getInstance().getLeftBound() + 640 - getW() && prevx != getX()) {
			double newOffSetX = SceneManager.getInstance().getOffsetX()+moveSpeed ;
			newOffSetX = (newOffSetX > SceneManager.getInstance().getRightBound() - 1280) ?  SceneManager.getInstance().getRightBound() - 1280 : newOffSetX; 
			SceneManager.getInstance().setOffsetX(newOffSetX);
		}
		
		direction = 1;
		face = PlayerStatus.RIGHT;
	}
	
	private void moveLeft() {
		
		super.moveLeft(moveSpeed);
		
		if(getX() < SceneManager.getInstance().getLeftBound()) setX(SceneManager.getInstance().getLeftBound());
			
		if(getX() <= SceneManager.getInstance().getRightBound() - 640 - getW() && prevx != getX()) {
			double newOffSetX = SceneManager.getInstance().getOffsetX()-moveSpeed;
			newOffSetX = (newOffSetX < SceneManager.getInstance().getLeftBound()) ?  SceneManager.getInstance().getLeftBound() : newOffSetX; 
			SceneManager.getInstance().setOffsetX(newOffSetX);
		}
		
		direction = -1;
		face = PlayerStatus.LEFT;
	}

	private void dash() {
		double originalMS = moveSpeed;
		
		setMoveSpeed(dashSpeed);
		if(face.equals(PlayerStatus.RIGHT)) moveRight();
		else moveLeft();
		
		setMoveSpeed(originalMS);
	}
	
	
	@Override
	public Sprite getImage() {
		// TODO Auto-generated method stub
		if(justTakeDamage > 0)return hurt;
		if(status.equals(PlayerStatus.DASHING)) {
			return roll;
		}
		if(atkable > 40) {
			return attack;
		}
		if(jumpStatus.equals(PlayerStatus.GOINGUP)){
			return jump_up;
		}
		if(jumpStatus.equals(PlayerStatus.FALLING)){
			return jump_down;
		}
		if(status.equals(PlayerStatus.RUN)) {
			return run;
		}
		
		return idle;
	}

	@Override
	public void draw(GraphicsContext gc,boolean f) {
		// TODO Auto-generated method stub
		if(immune%2 == 0) {
			if(!lastFrameStatus.equals(status) && atkable < 41) getImage().loadImage(getImage().getFilepath());
			if(atkable < 41 && status != PlayerStatus.DIE) {
//				if(direction != -1) super.draw(gc, false);
				if(!face.equals(PlayerStatus.LEFT))super.draw(gc, getImage().getImage(), getX(), getY(), getW(), getH());
				else super.draw(gc, getImage().getImage(),getX()+getW(),getY(),-getW(),getH());
			}else {
				if(!face.equals(PlayerStatus.LEFT))super.draw(gc, getImage().getImage(), getX()-getW()/3, getY(), (getW()*5)/3, getH());
				else super.draw(gc,getImage().getImage(), getX()+getW()*4/3,getY(),-getW()*5/3,getH());
			}
		}
	}

	private void attack() {
		
	}
	
	protected void takeDamage(int x) {
		super.takeDamage(x);
		justTakeDamage += 30;
	}


	@Override
	public void checkCollide() {
		// TODO Auto-generated method stub
		
	}

	public void changeJumpH(int h) {
		initJumpSpeed += h;
	}
	
	public void changemvsp(int sp) {
		moveSpeed += sp;
		dashSpeed = moveSpeed*dashSpeedMultiplier;
	}

	public double getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(double moveSpeed) {
		this.moveSpeed = moveSpeed;
		dashSpeed = moveSpeed*dashSpeedMultiplier;
	}

	public double getDashSpeedMultiplier() {
		return dashSpeedMultiplier;
	}

	public void setDashSpeedMultiplier(double dashSpeedMultiplier) {
		this.dashSpeedMultiplier = dashSpeedMultiplier;
		dashSpeed = moveSpeed*dashSpeedMultiplier;
	}
	
	public void changeDashSpeedMultiplier(double k) {
		dashSpeedMultiplier += k;
		dashSpeed = moveSpeed*dashSpeedMultiplier;
	}

	public double getPrevGround() {
		return prevGround;
	}
	
	
}
