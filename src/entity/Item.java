package entity;

import java.util.ArrayList;
import java.util.Random;

import component.Collidable;
import component.Entity;
import component.FallObject;
import component.Interactable;
import component.KeyStatus;
import component.Sprite;
import gui.GameUI;
import gui.InventoryPane;
import gui.PowerupPane;
import javafx.scene.media.AudioClip;
import logic.GameManager;
import logic.GameState;
import logic.KeyHandler;
import logic.SceneManager;

public class Item extends FallObject  implements Interactable,Collidable{	
	
	private static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private int type;
	private static Random r = new Random();
	private static AudioClip dropSound = new AudioClip(ClassLoader.getSystemResource("audio/sfx/coin_drop.mp3").toString());
	private static AudioClip equipSound = new AudioClip(ClassLoader.getSystemResource("audio/sfx/equip.mp3").toString());

	public Item(double x,int type) {
		super(x, 520, 50, 50);
		// TODO Auto-generated constructor stub
		this.type = type;
	}
	
	public Item(double x,double y, int type) {
		super(x, y, 50, 50);
		// TODO Auto-generated constructor stub
		this.type = type;
//		setVy(-5);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		fall();
		checkInteract();
	}

	@Override
	public Sprite getImage() {
		// TODO Auto-generated method stub
		return sprites.get(type);
	}
	
	public static void setUp() {
		sprites.add(new Sprite("sprite/item/armor.png"));
		sprites.add(new Sprite("sprite/item/sword.png"));
		sprites.add(new Sprite("sprite/item/boots.png"));
		sprites.add(new Sprite("sprite/item/ring.png"));
		sprites.add(new Sprite("sprite/item/necklace.png"));
		sprites.add(new Sprite("sprite/item/shield.png"));
	}
	
	public static void generate(int x) {
		SceneManager.getInstance().getInteractable().add(new Item(x, r.nextInt(6)));
		dropSound.play();
	}
	
	public static void generate(double x,double y) {
		SceneManager.getInstance().getInteractable().add(new Item(x, y, r.nextInt(6)));
		dropSound.play();
	}
	
	public static void generate(int lowerbound,int upperbound) {
		SceneManager.getInstance().getInteractable().add(new Item(r.nextInt(upperbound-lowerbound)+lowerbound, r.nextInt(6)));
		dropSound.play();
	}
	
	public static void generate(int type,int lowerbound,int upperbound) {
		SceneManager.getInstance().getInteractable().add(new Item(r.nextInt(upperbound-lowerbound)+lowerbound, type));
		dropSound.play();
	}

	@Override
	public void checkInteract() {
		// TODO Auto-generated method stub
		if(KeyHandler.getInstance().getKeyStatus(69).equals(KeyStatus.DOWN) &&
				collideWith(SceneManager.getInstance().getPlayer()) &&
				SceneManager.getInstance().getPlayer().isAlive()) {
			if(type==0)SceneManager.getInstance().getPlayer().changeMaxHp(20);
			else if(type==1)SceneManager.getInstance().getPlayer().changeAtk(30);
			else if(type==2)SceneManager.getInstance().getPlayer().changemvsp(1);
			else if(type==3)SceneManager.getInstance().getPlayer().changeJumpH(1);
			else if(type==4)SceneManager.getInstance().getPlayer().changeDashSpeedMultiplier(0.5);
			equipSound.play();
			SceneManager.getInstance().getPlayer().inventory.set(type, SceneManager.getInstance().getPlayer().getInventory().get(type)+1);
			SceneManager.getInstance().getInteractable().remove(this);
			InventoryPane.getInstance().getText().get(type).setText(String.valueOf(Integer.parseInt(InventoryPane.getInstance().getText().get(type).getText())+1));
			boolean win =true;
			for(int i : SceneManager.getInstance().getPlayer().getInventory()) {
				if(i==0)win = false;
			}
			if(win) {
				GameUI.getInstance().win();
				GameManager.getInstance().setMusicState(GameState.VICTORY);
			}
		}
	}

	@Override
	public void checkCollide() {
		// TODO Auto-generated method stub
		
	}

	public static Sprite getSprites(int x) {
		return sprites.get(x);
	}
}
