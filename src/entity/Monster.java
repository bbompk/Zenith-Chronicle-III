package entity;

import java.util.ArrayList;
import java.util.Random;

import component.Enemy;
import component.Sprite;
import logic.SceneManager;

public class Monster extends Enemy {
	
	private static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private int type;
	private int randomMove;
	private int randomDirection;
	private static Random r = new Random();

	public Monster(double x,int type) {
		super(x, r.nextInt(300)+100, 120, 120);
		// TODO Auto-generated constructor stub
		this.moveSpeed = r.nextDouble()*4+4;
		randomMove = 0;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		double distance = getX() - SceneManager.getInstance().getPlayer().getX();
		if(getY()+getH() == SceneManager.getInstance().getPlayer().getPrevGround()) {
			if(distance > -800 || distance < 800) {
				if(distance > 0)moveLeft(moveSpeed);
				if(distance < 0)moveRight(moveSpeed);
			}else {
				if(randomMove==0) {
					randomMove = r.nextInt(20)+21;
					randomDirection = r.nextInt(2);
				}else {
					randomMove--;
					if(randomDirection==0)moveLeft(moveSpeed);
					else moveRight(moveSpeed);
				}
			}
		}
		super.update();
	}

	@Override
	public Sprite getImage() {
		// TODO Auto-generated method stub
		return sprites.get(type);
	}

	public void setUp() {
		
	}
	
	public static void generate() {
		SceneManager.getInstance().getEnemy().add(new Monster(r.nextInt(1000)+1000, r.nextInt(2)));
	}
}
