package entity;

import java.util.ArrayList;
import java.util.Random;

import component.Collidable;
import component.Entity;
import component.Fallable;
import component.Sprite;
import logic.SceneManager;

public class Powerup extends Entity implements Collidable, Fallable{
	
	private static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private int type;

	public Powerup(double x,int type) {
		super(x, 520 ,50,50);
		// TODO Auto-generated constructor stub
		this.type = type;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		fall();
		checkCollide();
	}

	@Override
	public Sprite getImage() {
		// TODO Auto-generated method stub
		return sprites.get(type);
	}
	
	public static void setUp() {
		sprites.add(new Sprite("sprite/powerup/potion_red.png"));
		sprites.add(new Sprite("sprite/powerup/apple.png"));
		sprites.add(new Sprite("sprite/powerup/flower_red.png"));
		sprites.add(new Sprite("sprite/powerup/fish_blue.png"));
	}
	
	public static void generate(int x) {
		SceneManager.getInstance().getCollidable().add(new Powerup(x, new Random().nextInt(4)));
	}
	
	public static void generate(int lowerbound,int upperbound) {
		SceneManager.getInstance().getCollidable().add(new Powerup(new Random().nextInt(upperbound-lowerbound)+lowerbound, new Random().nextInt(4)));
	}
	
	public static void generate(int type,int lowerbound,int upperbound) {
		SceneManager.getInstance().getCollidable().add(new Powerup(new Random().nextInt(upperbound-lowerbound)+lowerbound, type));
	}
	
	public static void generate() {
		Random rand = new Random();
		int rand1 = rand.nextInt(600)+500;
		int rand2 = rand.nextInt(600)+1400;
		int rand3 = rand.nextInt(4);
		int rand4 = rand.nextInt(4);
		SceneManager.getInstance().getCollidable().add(new Powerup(rand1,rand3));
		SceneManager.getInstance().getCollidable().add(new Powerup(rand2,rand4));
	}

	@Override
	public void checkCollide() {
		// TODO Auto-generated method stub
		if(collideWith(SceneManager.getInstance().getPlayer())) {
			if(type==0)SceneManager.getInstance().getPlayer().changeHp(20);
			else {
				new Thread(()->{
					if(type==1){
						SceneManager.getInstance().getPlayer().changeAtk(10);
						try {
							Thread.sleep(20000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						SceneManager.getInstance().getPlayer().changeAtk(-10);
					}
					if(type==2){
						SceneManager.getInstance().getPlayer().changeJumpH(5);
						try {
							Thread.sleep(20000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						SceneManager.getInstance().getPlayer().changeJumpH(-5);
					}
					if(type==3){
						SceneManager.getInstance().getPlayer().changemvsp(5);
						try {
							Thread.sleep(20000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						SceneManager.getInstance().getPlayer().changemvsp(-5);
					}
				}).start();;
			}
			SceneManager.getInstance().getCollidable().remove(this);
		}
	}

}
