package entity;

import component.Collidable;
import component.Entity;
import component.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import logic.SceneManager;

public class Portal extends Entity implements Collidable {

	private Sprite portal;
	private boolean flip;
	private static AudioClip warpSound = new AudioClip(ClassLoader.getSystemResource("audio/sfx/warp.mp3").toString());
	
	public Portal() {
		super(SceneManager.getInstance().getRightBound() - 200, 520, 90, 150);
		// TODO Auto-generated constructor stub
		flip = false;
		portal = new Sprite("sprite/checkpoint/portal_end.gif");
	}
	
	public Portal(double x, double y) {
		super(x, y, 90, 150);
		// TODO Auto-generated constructor stub
		portal = new Sprite("sprite/checkpoint/portal_end.gif");
	}
	
	public Portal(boolean golden) {
		super(SceneManager.getInstance().getRightBound() - 200, 520, 90, 150);
		// TODO Auto-generated constructor stub
		flip = true;
		portal = golden ? new Sprite("sprite/checkpoint/boss_portal_end.gif") : new Sprite("sprite/checkpoint/portal_end.gif");
	}
	
	public Portal(boolean golden, int x, int y) {
		super(x, y, 90, 150);
		// TODO Auto-generated constructor stub
		flip = true;
		portal = golden ? new Sprite("sprite/checkpoint/boss_portal_end.gif") : new Sprite("sprite/checkpoint/portal_end.gif");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		checkCollide();
	}

	@Override
	public Sprite getImage() {
		// TODO Auto-generated method stub
		return portal;
	}

	@Override
	public void checkCollide() {
		// TODO Auto-generated method stub
		if(collideWith(SceneManager.getInstance().getPlayer())) {
			SceneManager.getInstance().changeState = true;
			warpSound.play(0.4);
		}
	}

	public void setPortal(Sprite portal) {
		this.portal = portal;
	}
	
	@Override
	public void draw(GraphicsContext gc,boolean flip) {
		super.draw(gc, this.flip);
	}

}
