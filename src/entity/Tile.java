package entity;

import component.Entity;
import component.Sprite;

public class Tile extends Entity {
	
	private boolean transparent;
	private double upperBound;
	private double lowerBound;
	private double rightBound;
	private double leftBound;
	

	public Tile(double x, double y, int w, int h, boolean t) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
		upperBound = y;
		lowerBound = y+h;
		leftBound = x;
		rightBound = x+w;
		transparent = t;
	}

	public Tile(double x, double y, int r) {
		super(x, y, r);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public Sprite getImage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public double getUpperBound() {
		return upperBound;
	}

	public double getLowerBound() {
		return lowerBound;
	}

	public double getRightBound() {
		return rightBound;
	}

	public double getLeftBound() {
		return leftBound;
	}

	public boolean isTransparent() {
		return transparent;
	}

	
	
	//@Override
	/*public void draw() {
		
	}*/

}
