package component;

import java.io.Serializable;

import entity.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.SceneManager;
import util.GameUtil;

public abstract class Entity implements Serializable{

	private double x,y;
	private int w,h;
	private int radius;
	private double Vy;
	protected double prevx;
	protected double prevy;

	public Entity(double x,double y,int w,int h) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.radius = w/2;
		this.Vy = 0;
	}
	
	public Entity(double x,double y,int r) {
		// TODO Auto-generated constructor stub
		this.x = x-r;
		this.y = y-r;
		this.w = 2*r;
		this.h = 2*r;
		this.radius = r;
		this.Vy = 0;
	}
	
	public abstract void update();
	public abstract Sprite getImage();
	
	public void draw(GraphicsContext gc,Image img,double x,double y,int w,int h) {
		gc.drawImage(img, x-SceneManager.getInstance().getOffsetX(), y, w, h);
	}
	
	public void draw(GraphicsContext gc,boolean f) {
		if(f) {
			gc.drawImage(getImage().getImage(), x + w-SceneManager.getInstance().getOffsetX(), y, -w, h);
		}
		else gc.drawImage(getImage().getImage(), x-SceneManager.getInstance().getOffsetX(), y, w, h);
	}
	
	public boolean collideWith(Entity other) {
		if(!(this instanceof Collidable) || !(other instanceof Collidable))return false;
		return Math.hypot(this.x+this.w/2-other.x-other.w/2, this.y+this.h/2-other.y-other.h/2) <= this.radius+other.radius;
	}
	
	protected int fall() {
		if(!(this instanceof Fallable)) return 0;
		double prevy = getY();
		increaseY(Vy);
		Vy = Vy + GameUtil.gravity;
		for(Tile tile : SceneManager.getInstance().getTiles()) {
			if(getX() >= tile.getLeftBound() && getX()+getW() <= tile.getRightBound()) {
				if(getY()+getH() > tile.getUpperBound() && getY() <= tile.getLowerBound()) {
					setY(tile.getUpperBound()  - getH());
					setVy(0);
				}
			}
		}
		if(prevy == getY()) return 0;
		if(prevy < getY()) return 1;
		if(prevy > getY()) return -1;
		return 0;
	}
	

	public void increaseX(double x) {
		this.x += x;
		
	}

	public void increaseY(double y) {
		this.y += y;
	}

	public void setW(int w) {
		this.w += w;
		this.radius = this.w/2;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setH(int h) {
		this.h += h;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public int getRadius() {
		return radius;
	}

	public double getVy() {
		return Vy;
	}

	public void setVy(double vy) {
		Vy = vy;
	}
	
	
	
}
