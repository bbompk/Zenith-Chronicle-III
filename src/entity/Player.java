package entity;

import component.Collidable;

import java.util.ArrayList;
import java.util.Arrays;

import component.Character;
import component.Enemy;
import component.Entity;
import component.KeyStatus;
import component.PlayerStatus;
import component.Sprite;
import gui.GameUI;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import javafx.util.Pair;
import logic.GameManager;
import logic.GameState;
import logic.KeyHandler;
import logic.SceneManager;
import util.Logger;


public class Player extends Character {
	
	public static final boolean WallHacks = false;
	
	
	//Utility
	private int atkable = 0;
	private int immune = 0;
	protected static PlayerStatus face = PlayerStatus.RIGHT;
	protected boolean freezeFace;
	private int playtime;
	private int playtimem;
	
	private static AudioClip atkHit = new AudioClip(ClassLoader.getSystemResource("audio/sfx/atk_hit.mp3").toString());
	private static AudioClip atkMiss = new AudioClip(ClassLoader.getSystemResource("audio/sfx/atk_missed.mp3").toString());
	private static AudioClip takeDmg = new AudioClip(ClassLoader.getSystemResource("audio/sfx/oof.mp3").toString());
	private static AudioClip dies = new AudioClip(ClassLoader.getSystemResource("audio/sfx/long_oof.mp3").toString());
	private static AudioClip whooshSound = new AudioClip(ClassLoader.getSystemResource("audio/sfx/whoosh.mp3").toString());
	
		
	// IDLE/RUN
	private PlayerStatus status;
	private PlayerStatus lastFrameStatus;
	private int direction;
	private double moveSpeed;
	
	
	//Dashing
	private int dashing = 0;
	private double dashSpeed ;
	private double dashSpeedMultiplier;
	private int maxDash;
	private int dashAvail;
	
	// Jumping
	private PlayerStatus jumpStatus;
	private double initJumpSpeed;
	private double jumpCount;
	private double prevGround;
	
	// Images
	private Sprite idle;
	private Sprite run;
	private Sprite jump_up;
	private Sprite jump_down;
	private Sprite death;
	private Sprite attack;
	private Sprite hurt;
	private Sprite roll;
	
	//Item
	public ArrayList<Integer> inventory;
	private int healing;
	
	
	public Player() {
		// TODO Auto-generated constructor stub
		
		super(150,550,120,120);
		lastFrameStatus = PlayerStatus.IDLE;
		status = PlayerStatus.IDLE;
		jumpStatus = PlayerStatus.ONGROUND;
		direction = 0;
		prevGround = 550+120;
		maxDash = 1;
		dashAvail = maxDash;
		inventory = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0));
		alive = true;
		idle = new Sprite("sprite/character/player/idle.gif");
		run = new Sprite("sprite/character/player/run.gif");
		jump_up = new Sprite("sprite/character/player/jump_up.gif");
		jump_down = new Sprite("sprite/character/player/jump_down.gif");
		death = new Sprite("sprite/character/player/death.gif");
		attack = new Sprite("sprite/character/player/attack.gif");
		hurt = new Sprite("sprite/character/player/hurt.gif");
		roll = new Sprite("sprite/character/player/roll_4_frame.gif");
		maxHp =100;
		hp = maxHp;
		atk = 70;
		moveSpeed = 7;
		initJumpSpeed = 10;
		dashSpeedMultiplier = 11/7;
		playtime = 0;playtimem = 0;
		new Thread(()->{
			while(true) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				SceneManager.getInstance().getPlayer().heal();
				playtime += 1;
				if(playtime > 5999) {
					playtime -= 6000;
					playtimem += 1;
				}
				}
		}).start();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(needRemove) {
			GameUI.getInstance().lose();
			setX(-5000);
			prevy = 999;
			GameManager.getInstance().setMusicState(GameState.GAMEOVER);
			Logger.log("You Died !!");
			return;
		}
//		takeDamage(1000);
		if(justTakeDamage == 0) {
		prevy = getY();
		prevx = getX();
		lastFrameStatus = status;
		status = PlayerStatus.RUN;
		while(healing >= 20) {
			healing -= 20;
			changeHp(1);
		}
		
		//-attack and dash ----------------
		if(atkable == 0 && jumpStatus.equals(PlayerStatus.ONGROUND) && KeyHandler.getInstance().getKeyStatus(83).equals(KeyStatus.DOWN) && !status.equals(PlayerStatus.DASHING)) {
			atkable += 81;
			//atkSound.play();
			attack();
			attack.loadImage(attack.getFilepath());
		}
		if(dashing == 0 && (KeyHandler.getInstance().getKeyStatus(16).equals(KeyStatus.DOWN) || KeyHandler.getInstance().getKeyStatus(17).equals(KeyStatus.DOWN) )
				&& !status.equals(PlayerStatus.DASHING) && dashAvail > 0) {
			if(!jumpStatus.equals(PlayerStatus.ONGROUND)) dashAvail--;
			dashing += 41;
			roll.loadImage(roll.getFilepath());
			whooshSound.play(0.3);
		}
		//direction-------------
		if(KeyHandler.getInstance().getKeyStatus(68).equals(KeyStatus.DOWN)) {
			direction = 1;
		}
		if(KeyHandler.getInstance().getKeyStatus(65).equals(KeyStatus.DOWN)) {
			direction = -1;
		}
		if(KeyHandler.getInstance().getKeyStatus(68).equals(KeyStatus.FREE) && KeyHandler.getInstance().getKeyStatus(65).equals(KeyStatus.FREE)) {
			direction = 0; status=PlayerStatus.IDLE;
		}else if(KeyHandler.getInstance().getKeyStatus(68).equals(KeyStatus.DOWN) && KeyHandler.getInstance().getKeyStatus(65).equals(KeyStatus.DOWN)) {
			direction = 0; status=PlayerStatus.IDLE;
		}
		
		//attack and dash-----------
		if(dashing > 21) {
			status = PlayerStatus.DASHING;
			dash();
		}else { 
			if(atkable > 41) {
				if(direction == 0 || atkable > 66)status = PlayerStatus.ATTACKING;
				else atkable = 41;
		}
		//---------------moving
		if(alive && atkable < 76){

			if(direction == 1) {
				moveRight();
			}
			if(direction == -1) {
				moveLeft();
			}
			if(direction == 0) {status = PlayerStatus.IDLE;}
			if(KeyHandler.getInstance().getKeyStatus(32).equals(KeyStatus.DOWN)) {
				prevGround = getY()+getH();
				jump();
			}
		}}
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
				if(e.collideWith(this) && e.isAlive() && immune == 0 && !(e instanceof Boss)) {
					if(dashing < 29 || dashing > 33) {
//						takeDamage(e.getAtk());
						immune += 101;
					}
				}
			}
		}
		justTakeDamage = justTakeDamage == 0 ? justTakeDamage : justTakeDamage -1;
		

		//if(prevx!=getX() || prevy!=getY()) System.out.println(getX() + " " + getY());
		if(!isAlive()) status = PlayerStatus.DIE;

		dashing = (dashing == 0) ? dashing : dashing - 1;
		atkable = (atkable == 0) ? atkable : atkable - 1;
		immune = (immune == 0) ? immune : immune - 1;
	}
	
	
	@Override
	protected int fall() {
		prevy = getY();
		increaseY(getVy());
		if(lastFrameStatus.equals(PlayerStatus.DASHING)) setVy(0);
		setVy(getVy() + GameManager.gravity);
		for(Tile tile : SceneManager.getInstance().getTiles()) {
			if( (getX() >= tile.getLeftBound() && getX() <= tile.getRightBound()) || (getX()+getW() >= tile.getLeftBound() && getX()+getW() <= tile.getRightBound()) ) {
				if(getY()+getH() > tile.getUpperBound() && getY() <= tile.getUpperBound() && !(jumpStatus.equals(PlayerStatus.GOINGUP)) ) {
					if(!tile.isTransparent()) {
						setY(tile.getUpperBound()  - getH());
						jumpStatus = PlayerStatus.ONGROUND;
						dashAvail = maxDash;
						setVy(0);
					} else {
						if(prevy +getH() <= tile.getUpperBound() && prevy <= tile.getUpperBound() && !(jumpStatus.equals(PlayerStatus.GOINGUP))) {
							setY(tile.getUpperBound()  - getH());
							jumpStatus = PlayerStatus.ONGROUND;
							dashAvail = maxDash;
							setVy(0);
						}
					}
				}
				
				if(getY()+getH() > tile.getLowerBound() && getY() < tile.getLowerBound() && !tile.isTransparent()) {
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
			whooshSound.play(0.3);
		}
		
	}
	
	private void moveRight() {
		
		super.moveRight(moveSpeed);
		
		if(getX() > SceneManager.getInstance().getRightBound()- getW()) setX(SceneManager.getInstance().getRightBound() - getW());
		
		if(getX() >= SceneManager.getInstance().getLeftBound() + 640 - getW() && prevx < getX() && (direction!= 0 || status.equals(PlayerStatus.DASHING))) {
			double newOffSetX = SceneManager.getInstance().getOffsetX()+moveSpeed ;
			newOffSetX = (newOffSetX > SceneManager.getInstance().getRightBound() - 1280) ?  SceneManager.getInstance().getRightBound() - 1280 : newOffSetX; 
			SceneManager.getInstance().setOffsetX(newOffSetX);
		}
		
		if(atkable<66)face = PlayerStatus.RIGHT;
	}
	
	private void moveLeft() {
		
		super.moveLeft(moveSpeed);
		
		if(getX() < SceneManager.getInstance().getLeftBound()) setX(SceneManager.getInstance().getLeftBound());
			
		if(getX() <= SceneManager.getInstance().getRightBound() - 640 - getW() && prevx > getX() && (direction!= 0 || status.equals(PlayerStatus.DASHING))) {
			double newOffSetX = SceneManager.getInstance().getOffsetX()-moveSpeed;
			newOffSetX = (newOffSetX < SceneManager.getInstance().getLeftBound()) ?  SceneManager.getInstance().getLeftBound() : newOffSetX; 
			SceneManager.getInstance().setOffsetX(newOffSetX);
		}
		
		if(atkable<66)face = PlayerStatus.LEFT;
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
		if(needRemove)return null;
		if(!isAlive()) return death;
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
		if(getImage()==null) return;
		if(status.equals(PlayerStatus.DIE)) {

			if(!face.equals(PlayerStatus.LEFT))super.draw(gc, getImage().getImage(), getX()-getW()/3, getY(), (getW()*5)/3, getH());
			else super.draw(gc,getImage().getImage(), getX()+getW()*4/3,getY(),-getW()*5/3,getH()); return;
		}

		if(immune/2%2 == 0) {
			
			if((!lastFrameStatus.equals(status) && atkable < 41) || justTakeDamage == 31) getImage().loadImage(getImage().getFilepath());
			if(justTakeDamage > 0) {
				if(!face.equals(PlayerStatus.LEFT))super.draw(gc, getImage().getImage(), getX()-getW()/3, getY()-getH()/4, getW()*4/3, getH()*5/4);
				else super.draw(gc, getImage().getImage(),getX()+getW()*4/3,getY()-getH()/4,-getW()*4/3,getH()*5/4);
			}
			else if(atkable < 41 && status != PlayerStatus.DIE) {
				if(!face.equals(PlayerStatus.LEFT))super.draw(gc, getImage().getImage(), getX(), getY(), getW(), getH());
				else super.draw(gc, getImage().getImage(),getX()+getW(),getY(),-getW(),getH());
			
			} else {
				if(!face.equals(PlayerStatus.LEFT))super.draw(gc, getImage().getImage(), getX()-getW()/3, getY(), (getW()*5)/3, getH());
				else super.draw(gc,getImage().getImage(), getX()+getW()*4/3,getY(),-getW()*5/3,getH());
			}
		}
	}
	
	private void attack() {
		AttackBox a;
		if(face.equals(PlayerStatus.RIGHT)) a = new AttackBox(getX()+getW()/4, getY(), 100+getW()*3/4	, getH());
//		else  a = new AttackBox(getX()+getW()*3/4, getY(), -100-getW()*3/4	, getH());
		else  a = new AttackBox(getX()-100, getY(), 100+getW()*3/4	, getH());
		for(Enemy enemy : SceneManager.getInstance().getEnemy()) {
			if(enemy.collideWith(a)) {
				enemy.takeDamage(getAtk());
				atkHit.play(0.4);
			}else atkMiss.play(0.2);
		}
	}
	
	public void takeDamage(int x) {
		super.takeDamage(x);
		if(alive) takeDmg.play(); else dies.play(0.3);
		justTakeDamage += 32;
	}

	
	public void heal() {
		healing += 5 *inventory.get(5);
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
	
	public void changeMaxDash(int x) {
		this.maxDash += x;
	}

	@Override
	protected void die() {
		// TODO Auto-generated method stub
		status = PlayerStatus.DIE;
		new Thread(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			needRemove = true;
		}).start();
	}

	public ArrayList<Integer> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<Integer> inventory) {
		this.inventory = inventory;
	}
	
	public String getplaytime(){
		return playtimem + " minute and " + (int)(playtime/100) + " second";
	}
	
	public int getplaytimem(){
		return playtimem;
	}
	
	public boolean isimmune() {
		if(immune>0 || (dashing > 28 && dashing < 34)) {
//			System.out.println("dodge!");
			return true;
		}
		immune += 101;
		return false;
	}
}
