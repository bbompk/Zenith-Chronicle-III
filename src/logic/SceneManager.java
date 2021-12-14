package logic;

import java.io.Serializable;
import java.util.ArrayList;

import component.Collidable;
import component.Enemy;
import component.Entity;
import component.Interactable;
import entity.Background;
import entity.Player;
import entity.Portal;
import entity.Powerup;
import entity.Tile;
import entity.TileBackground;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import util.TileGenerator;

public class SceneManager extends Canvas implements Serializable {

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
	
	private double leftBound = 0;
	private double rightBound = 3200;
	
	private SceneManager() {
		// TODO Auto-generated constructor stub
		super(1280, 720);
		
		enemy = new ArrayList<Enemy>();
		props = new ArrayList<Entity>();
		tiles = new ArrayList<Tile>();
		collidable = new ArrayList<Collidable>();
		interactable = new ArrayList<Interactable>();
		player = new Player();
		level = 0;
		offsetX = 0;
		offsetY = 0;
		
	}
	
	public static SceneManager getInstance() {
		if(instance == null)instance = new SceneManager();
		return instance;
	}
	
	public void update() {
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
		GraphicsContext gc = getGraphicsContext2D();
		gc.setFill(Color.RED);
		for(Enemy e : enemy) {
			e.draw(gc,false);
		}
		for(Entity e : props) {
			e.draw(gc,false);
		}
		for(Collidable c : collidable) {
			if(c instanceof Entity) ((Entity) c).draw(gc,false);
		}
		for(Interactable i : interactable) {
			if(i instanceof Entity) ((Entity) i).draw(gc,false);
		}
		for(Tile tile : tiles) {
			Color strokeColor = (tile.isTransparent()) ? Color.RED: Color.BLACK;
			gc.setStroke(strokeColor);
			gc.strokeRect(tile.getX() - offsetX, tile.getY(), tile.getW(), tile.getH());
		
		}
		player.draw(gc,false);
	}
	
	public void startlLevel() {
		
	}
	
	public void gameStart() {		
		props.add(new Background());
		props.add(new TileBackground());
		props.add(new Portal());
		TileGenerator.generate();
		Powerup.setUp();
		Powerup.generate();
	}
	
	public void startBossLevel() {
		props.add(new Background(0, 0, "sprite/background/level_tiles2.png"));
		props.add(new TileBackground());
	}

	public double getOffsetX() {
		return offsetX;
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
	
	
	
}
