package entity;

import java.util.ArrayList;
import java.util.Random;
import component.Enemy;
import component.Sprite;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import logic.Difficulty;
import logic.SceneManager;

public class Monster extends Enemy {
	
	private static ArrayList<Sprite> idle = new ArrayList<Sprite>();
	private static ArrayList<Sprite> death = new ArrayList<Sprite>();
	private static ArrayList<Sprite> hurt = new ArrayList<Sprite>();
	private static ArrayList<Sprite> run = new ArrayList<Sprite>();
	private static ArrayList<Integer> size = new ArrayList<Integer>();
	private static ArrayList<AudioClip> audio = new ArrayList<AudioClip>();
	private int type;
	private int randomMove;
	private int direction;
	private static Random r = new Random();

	public Monster(double x,int type) {
		super(x, r.nextInt(300)+100, size.get(type), size.get(type));
		// TODO Auto-generated constructor stub
		this.type = type;
		this.moveSpeed = 2 + r.nextDouble()*2 + r.nextDouble() * Difficulty.getHardMultiply();
		randomMove = 0;
		maxHp = (int) (40 + r.nextInt(41) + 40*Difficulty.getHardMultiply());
		hp = maxHp;
		atk = (int) (5 + r.nextInt(11) + 5*Difficulty.getHardMultiply());
	}
	
	public Monster(double x,double y,int type) {
		super(x, y, size.get(type), size.get(type));
		// TODO Auto-generated constructor stub
		this.type = type;
		this.moveSpeed = 2 + r.nextDouble()*2 + r.nextDouble() * Difficulty.getHardMultiply();
		randomMove = 0;
		maxHp = (int) (40 + r.nextInt(41) + 40*Difficulty.getHardMultiply());
		hp = maxHp;
		atk = (int) (5 + r.nextInt(11) + 5*Difficulty.getHardMultiply());
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(needRemove) {
			if(r.nextInt(10)==0)Item.generate(getX()+getW()/2-25,getY());
			SceneManager.getInstance().getEnemy().remove(this);
			return;
		}
		justTakeDamage = justTakeDamage == 0 ? justTakeDamage : justTakeDamage -1;
		if(fall()==0 && alive) {
			double distance = getX() - SceneManager.getInstance().getPlayer().getX();
			if(getY()+getH() == SceneManager.getInstance().getPlayer().getPrevGround() && justTakeDamage == 0  && SceneManager.getInstance().getPlayer().isAlive()) {
				if(distance > -800 || distance < 800) {
					if(distance > 0) {direction = 0; moveLeft(moveSpeed);}
					if(distance < 0) {direction = 1; moveRight(moveSpeed);}
				}
			}else if(distance < 3000 || distance > -3000 || !SceneManager.getInstance().getPlayer().isAlive()){
				if(randomMove==0) {
					randomMove = r.nextInt(21)+21;
					direction = r.nextInt(2);
				}else {
					randomMove--;
					if(direction==0)moveLeft(moveSpeed);
					else moveRight(moveSpeed);
				}
			}
		}
		//System.out.println(this.hashCode() + " " + getX() + " " + getY());
		fall();

	}
	
	@Override
	public void takeDamage(int x) {
		super.takeDamage(x);
		if(!alive) audio.get(type).play();
	}

	@Override
	public Sprite getImage() {
		// TODO Auto-generated method stub
		if(!alive)return death.get(type);
		if(justTakeDamage > 0)return hurt.get(type);
		return run.get(type);
	}

	public static void setUp() {
		idle.add(new Sprite("sprite/character/enemy/skeleton/idle.gif"));
		hurt.add(new Sprite("sprite/character/enemy/skeleton/hurt.gif"));
		death.add(new Sprite("sprite/character/enemy/skeleton/death.gif"));
		run.add(new Sprite("sprite/character/enemy/skeleton/run.gif"));
		audio.add(new AudioClip(ClassLoader.getSystemResource("audio/sfx/skeleton_die.mp3").toString()));
		audio.get(0).setVolume(0.5);
		size.add(120);
		idle.add(new Sprite("sprite/character/enemy/mushroom/idle.gif"));
		hurt.add(new Sprite("sprite/character/enemy/mushroom/hurt.gif"));
		death.add(new Sprite("sprite/character/enemy/mushroom/death.gif"));
		run.add(new Sprite("sprite/character/enemy/mushroom/run.gif"));
		audio.add(new AudioClip(ClassLoader.getSystemResource("audio/sfx/thanos.mp3").toString()));
		size.add(90);
	}
	
	public static void generate() {
		int k = 15 + r.nextInt(5) + r.nextInt(5) + r.nextInt((int) (10*Difficulty.getHardMultiply()));
		int l = 1280;int u = 9000;int d = (u-l)/k;
		for(int i=0;i<k;i++) {
			double x = r.nextDouble()*(l+(i+1)*d-l+i*d)+l+i*d;
			if(x> 7680 && x < 8030) generate(x,200.0);else generate(x);
		}
	}
	
	public static void generaterandom() {
		SceneManager.getInstance().getEnemy().add(new Monster(r.nextInt(1001)+1000, r.nextInt(2)));
	}
	
	public static void generate(double x) {
		SceneManager.getInstance().getEnemy().add(new Monster(x, r.nextInt(2)));
	}
	
	public static void generate(double x,double y) {
		SceneManager.getInstance().getEnemy().add(new Monster(x,y, r.nextInt(2)));
	}

	public static void generate(int lowerbound,int upperbound) {
		SceneManager.getInstance().getEnemy().add(new Monster(r.nextDouble()*(upperbound-lowerbound)+lowerbound,r.nextInt(2)));
	}
	
	public static void generate(int lowerbound,int upperbound,double y) {
		SceneManager.getInstance().getEnemy().add(new Monster(r.nextDouble()*(upperbound-lowerbound)+lowerbound, y,r.nextInt(2)));
	}
	
	@Override
	public void draw(GraphicsContext gc,boolean f) {
		super.draw(gc,direction==0);
	}
}
