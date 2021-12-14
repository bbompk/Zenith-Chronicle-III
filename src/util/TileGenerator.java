package util;

import entity.Tile;
import logic.SceneManager;

public class TileGenerator {

	public TileGenerator() {
		// TODO Auto-generated constructor stub
	}
	
	public static void generate() {
		SceneManager.getInstance().addTile(new Tile(0, 670, 8960, 50, false));
		SceneManager.getInstance().addTile(new Tile(1280, 610, 500, 60, false));
		SceneManager.getInstance().addTile(new Tile(1780, 470, 500, 200, false));
		SceneManager.getInstance().addTile(new Tile(2560, 275, 120, 10, true));
		SceneManager.getInstance().addTile(new Tile(2560, 415, 120, 10, true));
		SceneManager.getInstance().addTile(new Tile(2560, 555, 120, 10, true));
		SceneManager.getInstance().addTile(new Tile(2680, 130, 135, 540, false));
		SceneManager.getInstance().addTile(new Tile(2815, 500, 800, 30, false));
		SceneManager.getInstance().addTile(new Tile(3035, 250, 120, 10, true));
		SceneManager.getInstance().addTile(new Tile(3345, 380, 120, 10, true));
		SceneManager.getInstance().addTile(new Tile(3615, 130, 225, 400, false));
		SceneManager.getInstance().addTile(new Tile(4040, 130, 840, 50, false));
		SceneManager.getInstance().addTile(new Tile(4040, 390, 840, 10, true));
		SceneManager.getInstance().addTile(new Tile(4040, 530, 840, 10, true));
		SceneManager.getInstance().addTile(new Tile(5370, 270, 250, 100, false));
		SceneManager.getInstance().addTile(new Tile(5270, 370, 350, 100, false));
		SceneManager.getInstance().addTile(new Tile(5170, 470, 250, 100, false));
		SceneManager.getInstance().addTile(new Tile(5070, 570, 300, 100, false));
		SceneManager.getInstance().addTile(new Tile(6400, 310, 120, 10, true));
		SceneManager.getInstance().addTile(new Tile(6400, 490, 120, 10, true));
		SceneManager.getInstance().addTile(new Tile(6520, 130, 120, 540, false));
		SceneManager.getInstance().addTile(new Tile(6640, 230, 120, 440, false));
		SceneManager.getInstance().addTile(new Tile(6760, 330, 120, 340, false));
		SceneManager.getInstance().addTile(new Tile(6880, 430, 120, 240, false));
		SceneManager.getInstance().addTile(new Tile(7000, 530, 120, 140, false));
		SceneManager.getInstance().addTile(new Tile(7120, 630, 120, 40, false));
		SceneManager.getInstance().addTile(new Tile(7630, 0, 50, 180, false));
		SceneManager.getInstance().addTile(new Tile(7680, 130, 400, 50, false));
		SceneManager.getInstance().addTile(new Tile(8030, 180, 50, 310, false));
		SceneManager.getInstance().addTile(new Tile(7830, 350, 200, 10, true));
		SceneManager.getInstance().addTile(new Tile(7630, 490, 450, 50, false));
		SceneManager.getInstance().addTile(new Tile(8320, 290, 120, 10, true));
		SceneManager.getInstance().addTile(new Tile(8590, 430, 120, 10, true));
		SceneManager.getInstance().addTile(new Tile(8860, 570, 120, 100, false));
		
	}
	
	public static void generateBossArena() {
		SceneManager.getInstance().addTile(new Tile(0, 650, 1280, 50, false));
		SceneManager.getInstance().addTile(new Tile(0, 550, 100, 10, true));
		SceneManager.getInstance().addTile(new Tile(1180, 550, 100, 10, true));
		SceneManager.getInstance().addTile(new Tile(215, 505, 300, 10, true));
		SceneManager.getInstance().addTile(new Tile(750, 505, 300, 10, true));
	}

}
