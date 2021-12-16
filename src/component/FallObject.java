package component;

import entity.Tile;
import logic.GameManager;
import logic.SceneManager;

public abstract class FallObject extends Entity {
	double Vy;
	
	public FallObject(double x, double y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
		Vy = 0;
	}

	public FallObject(double x, double y, int r) {
		super(x, y, r);
		// TODO Auto-generated constructor stub
		Vy = 0;
	}
	
	protected int fall() {
		
		double prevy = getY();
		increaseY(Vy);
		Vy = Vy + GameManager.gravity;
		for(Tile tile : SceneManager.getInstance().getTiles()) {
			if((getX() >= tile.getLeftBound() && getX() <= tile.getRightBound()) || (getX()+getW() >= tile.getLeftBound() && getX()+getW() <= tile.getRightBound())) {
				if(getY()+getH() > tile.getUpperBound() && getY() <= tile.getLowerBound()) {
					if(!tile.isTransparent()) {
						setY(tile.getUpperBound()  - getH());
						setVy(0);
					} else {
						if(prevy +getH() <= tile.getUpperBound() && prevy <= tile.getUpperBound() ) {
							setY(tile.getUpperBound()  - getH());
							setVy(0);
						}
					}
				}
			}
		}
		if(prevy == getY()) return 0;
		if(prevy < getY()) return 1;
		if(prevy > getY()) return -1;
		return 0;
	}

	public double getVy() {
		return Vy;
	}

	public void setVy(double vy) {
		Vy = vy;
	}
	
	
	
}
