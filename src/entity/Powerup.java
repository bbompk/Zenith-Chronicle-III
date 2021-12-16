package entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import gui.PowerupPane;
import javafx.scene.media.AudioClip;
import component.Collidable;
import component.Entity;
import component.FallObject;
import component.Sprite;
import gui.PowerupPane;
import logic.Difficulty;
import logic.SceneManager;

public class Powerup extends FallObject implements Collidable{
	
	private static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private int type;
	private boolean renewable;
	private int level;
	private static Queue<Integer> renew = new LinkedList<Integer>();
	private static ArrayList<Thread> thread = new ArrayList<Thread>();
	private static final AudioClip boostSound= new AudioClip(ClassLoader.getSystemResource("audio/sfx/boost.mp3").toString());
	boolean interrupt;

	public Powerup(double x,int type) {
		super(x, 520 ,50,50);
		// TODO Auto-generated constructor stub
		this.type = type;
		level = 0;
	}
	
	public Powerup(double x,double y,int type) {
		super(x, y ,50,50);
		// TODO Auto-generated constructor stub
		this.type = type;
		level = 0;
	}
	
	public Powerup(double x,double y,int type,boolean renewable,int level) {
		super(x, y ,50,50);
		// TODO Auto-generated constructor stub
		this.type = type;
		this.level = level;
		this.renewable = renewable;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(level!=0 && level!=Difficulty.level) {
			SceneManager.getInstance().getCollidable().remove(this);
			return;
		}
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
		int rand1 = rand.nextInt(2)*2+1;
		int rand2 = rand.nextInt(2)*2+1;
		SceneManager.getInstance().getCollidable().add(new Powerup(2880,600,rand1));
		SceneManager.getInstance().getCollidable().add(new Powerup(5500,600,rand2));
		SceneManager.getInstance().getCollidable().add(new Powerup(2600,600,2,true,Difficulty.level));
		SceneManager.getInstance().getCollidable().add(new Powerup(8540,600,2,true,Difficulty.level));
		SceneManager.getInstance().getCollidable().add(new Powerup(6400,600,2,true,Difficulty.level));
		SceneManager.getInstance().getCollidable().add(new Powerup(7900,300,0));
	}

	@Override
	public void checkCollide() {
		// TODO Auto-generated method stub
		if(collideWith(SceneManager.getInstance().getPlayer()) 
				&& SceneManager.getInstance().getPlayer().isAlive()) {
			if(type==0)SceneManager.getInstance().getPlayer().changeHp(15);
			else {
				Thread t = new Thread(()->{
					
					PowerupPane.getInstance().getText().get(type).setText(String.valueOf(Integer.parseInt(PowerupPane.getInstance().getText().get(type).getText())+1));
					if(type==1){
						SceneManager.getInstance().getPlayer().changeAtk(100);
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
//							e.printStackTrace();
							SceneManager.getInstance().getPlayer().changeAtk(100);
						}
						SceneManager.getInstance().getPlayer().changeAtk(-100);
					}
					if(type==2){
						SceneManager.getInstance().getPlayer().changeJumpH(2);
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
//							e.printStackTrace();
							SceneManager.getInstance().getPlayer().changeJumpH(2);
						}
						SceneManager.getInstance().getPlayer().changeJumpH(-2);
					}
					if(type==3){
						SceneManager.getInstance().getPlayer().changemvsp(2);
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
//							e.printStackTrace();
							SceneManager.getInstance().getPlayer().changemvsp(2);
						}
						SceneManager.getInstance().getPlayer().changemvsp(-2);
					}
					PowerupPane.getInstance().getText().get(type).setText(String.valueOf(Integer.parseInt(PowerupPane.getInstance().getText().get(type).getText())-1));
				});
				thread.add(t);
				t.start();
			}
			boostSound.play();
			if(renewable) {
				Thread t = new Thread(() ->{
				double x = getX();double y = getY();int type = this.type;int level = this.level;
				try {
					System.out.println("renew");;
					Thread.sleep(15000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					interrupt = true;
				}	if(!interrupt)renew.add((int) x);renew.add((int) y);renew.add(type);renew.add(level);
			});thread.add(t);t.start();}
			SceneManager.getInstance().getCollidable().remove(this);
		}
	}

	public static Sprite getSprites(int x) {
		return sprites.get(x);
	}
	
	public static void renewPowerup() {
		while(renew.size() >3) {
			int x = renew.poll();int y = renew.poll(); int type = renew.poll();int level = renew.poll();
			if(level==Difficulty.level)SceneManager.getInstance().getCollidable().add(new Powerup(x, y, type,true,level));
		}
	}
	
	public static void clear() {
		for(Thread t : thread) {
			t.interrupt();
		}
	}
	

}
