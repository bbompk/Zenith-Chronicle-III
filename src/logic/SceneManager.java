package logic;

import java.io.Serializable;
import java.util.ArrayList;

import baseEntity.Collidable;
import baseEntity.Enemy;
import baseEntity.Entity;
import baseEntity.Interactable;
import component.Sprite;
import entity.Background;
import entity.Boss;
import entity.Item;
import entity.Monster;
import entity.Particles;
import entity.Player;
import entity.Portal;
import entity.Powerup;
import entity.Tile;
import entity.TileBackground;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;

public class SceneManager extends Canvas implements Serializable {
	
	public static final boolean isDev = false;

	private static SceneManager instance = null;
	private double offsetX;
	private double offsetY;
	
	private ArrayList<Enemy> enemy;
	private ArrayList<Entity> props;
	private ArrayList<Tile> tiles;
	private ArrayList<Collidable> collidable;
	private ArrayList<Interactable> interactable;
	private Player player;
	private int level;
	
	private double leftBound ;
	private double rightBound ;
	public boolean changeState;
	private boolean gameend;
	private static AudioClip bossEnterSound = new AudioClip(ClassLoader.getSystemResource("audio/sfx/boss_quip.mp3").toString());
	
	private SceneManager() {
		// TODO Auto-generated constructor stub
		super(1280, 720);
		
		enemy = new ArrayList<Enemy>();
		props = new ArrayList<Entity>();
		tiles = new ArrayList<Tile>();
		collidable = new ArrayList<Collidable>();
		interactable = new ArrayList<Interactable>();
		player = new Player();
		gameend = false;
		level = 0;
	}
	
	public static SceneManager getInstance() {
		if(instance == null)instance = new SceneManager();
		return instance;
	}
	
	public void update() {
		if(gameend)return;
		Powerup.renewPowerup();
		if(changeState) {
			clear();
			startLevel();
			changeState = false;
		}
		for(int i = enemy.size()-1;i>-1;i--) {
			enemy.get(i).update();
		}
		for(int i = props.size()-1;i>-1;i--) {
			props.get(i).update();
		}		
		for(int i= collidable.size()-1;i>-1;i--) {
			if(collidable.get(i) instanceof Entity) ((Entity) collidable.get(i)).update();
		}
		for(int i= interactable.size()-1;i>-1;i--) {
			if(interactable.get(i) instanceof Entity) ((Entity) interactable.get(i)).update();
		}
		player.update();
		draw();
	}
	
	public void draw() {
		if(gameend)return;
		GraphicsContext gc = getGraphicsContext2D();
		gc.setFill(Color.RED);
//		for(Tile tile : tiles) {
//			Color strokeColor = (tile.isTransparent()) ? Color.RED: Color.BLACK;
//			gc.setStroke(strokeColor);
//			gc.strokeRect(tile.getX() - offsetX, tile.getY(), tile.getW(), tile.getH());
//		}
		for(Entity e : props) {
			e.draw(gc,false);
		}
		for(Tile tile : tiles) {
			Color strokeColor = (tile.isTransparent()) ? Color.RED: Color.BLACK;
			gc.setStroke(strokeColor);
			if(isDev) gc.strokeRect(tile.getX() - offsetX, tile.getY(), tile.getW(), tile.getH());
		}
		for(Enemy e : enemy) {
			e.drawHitBox(gc);
			e.draw(gc,false);
		}
		for(Collidable c : collidable) {
			if(c instanceof Entity) {
				((Entity) c).draw(gc,false);
				((Entity) c).drawHitBox(gc);
			}
		}
		for(Interactable i : interactable) {
			if(i instanceof Entity) ((Entity) i).draw(gc,false);
		}
		player.drawHitBox(gc);
		player.draw(gc,false);
	}
	
	public void startLevel() {
		Difficulty.goNextLevel();
		if(Difficulty.countDown==0)startBossLevel();
		else gameStart();
	}
		
	public void setUp() {
		Powerup.setUp();Item.setUp();Monster.setUp();
	}
	
	public void gameStart() {
		setLeftBound(0);
		setRightBound(8960);
		props.add(new Background());
		props.add(new TileBackground(0, 0, 9600, 720, "sprite/background/level_tiles.png"));
		props.add(new Portal(Difficulty.countDown==1, 7700, 10));
		Tile.generate();
		Powerup.generate();
		Monster.generate();
		props.add(new Particles(2140, 605, 50, 50, -1,"sprite/item/fake_axe.png"));
		props.add(new Particles(3700, 370, 50, 50, -1,"sprite/item/fake_staff.png"));
		props.add(new Particles(6645, 550, 50, 50, -1,"sprite/item/fake_sword.png"));
		props.add(new Particles(100, 150, 90, 150, -1,"sprite/checkpoint/portal_start.gif"));
		player.setY(100);
		player.setX(100);
		GameManager.getInstance().setMusicState(GameState.LEVEL);
		
		if(isDev) props.add(new Portal(100, 500));
	}
	
	public void startBossLevel() {
		//ameManager.getInstance().setState(GameState.BOSS);
		setLeftBound(0);
		setRightBound(1280);
		props.add(new Background(0, 0, "sprite/background/desert_mountains.png"));
		props.add(new TileBackground(0, 0, 1280, 720, "sprite/background/boss_arena_tiles.png"));
		props.add(new Particles(50, 50, 90, 150, -1, "sprite/checkpoint/boss_portal_start.gif"));
		Tile.generateBossArena();
		enemy.add(new Boss(850, 410));
		player.setY(50);
		bossEnterSound.play(0.7);
		GameManager.getInstance().setMusicState(GameState.BOSS);
		
	}

	public void enterHomeScreen() {
		setLeftBound(0);
		setRightBound(1280);
		props.add(new Background());
		props.add(new TileBackground(0, 0, 3200, 720, "sprite/background/plain_tiles.png"));
		addTile(new Tile(0, 670, 1300, 50, false));
		player.setX(300);
		player.setY(500);
		
		GameManager.getInstance().setMusicState(GameState.TITLE);
	}
	
	public double getOffsetX() {
		return offsetX;
	}
	
	public void clear() {
		player.setY(100);player.setX(100);
		props.clear();enemy.clear();collidable.clear();interactable.clear();tiles.clear();
		offsetX = 0;offsetY = 0;
	}

	public void setOffsetX(double offsetX) {
		this.offsetX = offsetX;
	}

	public double getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(double offsetY) {
		this.offsetY = offsetY;
	}

	public ArrayList<Enemy> getEnemy() {
		return enemy;
	}

	public Player getPlayer() {
		return player;
	}


	public int getLevel() {
		return level;
	}
	
	public double getLeftBound() {
		return leftBound;
	}
	
	public double getRightBound() {
		return rightBound;
	}

	public ArrayList<Entity> getProps() {
		return props;
	}

	public void setProps(ArrayList<Entity> props) {
		this.props = props;
	}

	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	public ArrayList<Collidable> getCollidable() {
		return collidable;
	}

	public void setCollidable(ArrayList<Collidable> collidable) {
		this.collidable = collidable;
	}

	public ArrayList<Interactable> getInteractable() {
		return interactable;
	}

	public void setInteractable(ArrayList<Interactable> interactable) {
		this.interactable = interactable;
	}

	public void setEnemy(ArrayList<Enemy> enemy) {
		this.enemy = enemy;
	}
	
	public void addTile(Tile tile) {
		this.tiles.add(tile);
	}

	public void setLeftBound(double leftBound) {
		this.leftBound = leftBound;
	}

	public void setRightBound(double rightBound) {
		this.rightBound = rightBound;
	};
	
	public void restart() {
		instance = null;
	}
	
	public void endgame(boolean end) {
		gameend = end;
	}
	
}
