package entity;

import java.util.ArrayList;
import java.util.Random;

import component.Entity;
import component.Fallable;
import component.Interactable;
import component.KeyStatus;
import component.Sprite;
import logic.KeyHandler;
import logic.SceneManager;

public class Item extends Entity implements Interactable,Fallable{	
	
	private static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private int type;
	private static Random r = new Random();

	public Item(double x, double y) {
		super(x, y-50, 50, 50);
		// TODO Auto-generated constructor stub
		type = r.nextInt(5);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		fall();
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
	
	public static void generate(int lowerbound,int upperbound) {
		SceneManager.getInstance().getInteractable().add(new Item(new Random().nextInt(upperbound-lowerbound)+lowerbound, new Random().nextInt(4)));
	}
	
	public static void generate(int type,int lowerbound,int upperbound) {
		SceneManager.getInstance().getInteractable().add(new Item(new Random().nextInt(upperbound-lowerbound)+lowerbound, type));
	}

	@Override
	public void checkInteract() {
		// TODO Auto-generated method stub
		if(KeyHandler.getInstance().getKeyStatus(69).equals(KeyStatus.DOWN)) {
			if(type==0)SceneManager.getInstance().getPlayer().changeMaxHp(20);
			else if(type==1)SceneManager.getInstance().getPlayer().changeAtk(5);
			else if(type==2)SceneManager.getInstance().getPlayer().changemvsp(1);
			else if(type==3)SceneManager.getInstance().getPlayer().changeJumpH(2);
			else if(type==4)SceneManager.getInstance().getPlayer().changeDashSpeedMultiplier(0.5);
			else if(type==5)SceneManager.getInstance().getPlayer().changeMaxDash(1);
			SceneManager.getInstance().getInteractable().remove(this);
		}
	}
}
