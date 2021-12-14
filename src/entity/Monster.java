package entity;

import java.util.ArrayList;
import java.util.Random;

import com.sun.javafx.scene.traversal.Direction;

import component.Enemy;
import component.Sprite;
import javafx.scene.canvas.GraphicsContext;
import logic.Difficulty;
import logic.SceneManager;

public class Monster extends Enemy {
	
	private static ArrayList<Sprite> idle = new ArrayList<Sprite>();
	private static ArrayList<Sprite> death = new ArrayList<Sprite>();
	private static ArrayList<Sprite> hurt = new ArrayList<Sprite>();
	private static ArrayList<Sprite> run = new ArrayList<Sprite>();
	private static ArrayList<Integer> size = new ArrayList<Integer>();
	private int type;
	private int randomMove;
	private int direction;
	private static Random r = new Random();

	public Monster(double x,int type) {
		super(x, r.nextInt(100)+100, size.get(type), size.get(type));
		// TODO Auto-generated constructor stub
		this.type = type;
		this.moveSpeed = 3 + r.nextDouble()*2 + 3*Difficulty.getHardMultiply();
		randomMove = 0;
		maxHp = (int) (40 + r.nextInt(41) + 40*Difficulty.getHardMultiply());
		hp = maxHp;
		atk = (int) (5 + r.nextInt(11) + 10*Difficulty.getHardMultiply());
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		double distance = getX() - SceneManager.getInstance().getPlayer().getX();
		if(getY()+getH() == SceneManager.getInstance().getPlayer().getPrevGround() && justTakeDamage == 0) {
			if(distance > -800 || distance < 800) {
				if(distance > 0) {direction = 0; moveLeft(moveSpeed);}
				if(distance < 0) {direction = 1; moveRight(moveSpeed);}
			}else {
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
		super.update();
	}

	@Override
	public Sprite getImage() {
		// TODO Auto-generated method stub
		if(hp == 0 )return death.get(type);
		if(justTakeDamage > 0)return hurt.get(type);
		return run.get(type);
	}

	public static void setUp() {
		idle.add(new Sprite("sprite/character/enemy/skeleton/idle.gif"));
		hurt.add(new Sprite("sprite/character/enemy/skeleton/hurt.gif"));
		death.add(new Sprite("sprite/character/enemy/skeleton/death.gif"));
		run.add(new Sprite("sprite/character/enemy/skeleton/run.gif"));
		size.add(120);
		idle.add(new Sprite("sprite/character/enemy/mushroom/idle.gif"));
		hurt.add(new Sprite("sprite/character/enemy/mushroom/hurt.gif"));
		death.add(new Sprite("sprite/character/enemy/mushroom/death.gif"));
		run.add(new Sprite("sprite/character/enemy/mushroom/run.gif"));
		size.add(90);
	}
	
	public static void generate() {
		SceneManager.getInstance().getEnemy().add(new Monster(r.nextInt(1001)+1000, r.nextInt(2)));
	}
	
	public static void generate(int x) {
		SceneManager.getInstance().getEnemy().add(new Monster(x, r.nextInt(2)));
	}
	
	public static void generate(int x,int type) {
		SceneManager.getInstance().getEnemy().add(new Monster(x,type));
	}
	
	@Override
	public void draw(GraphicsContext gc,boolean f) {
		super.draw(gc,direction == 0);
	}
}
