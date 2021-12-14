package util;

import entity.Tile;
import logic.SceneManager;

public class TileGenerator {

	public TileGenerator() {
		// TODO Auto-generated constructor stub
	}
	
	public static void generate() {
		SceneManager.getInstance().addTile(new Tile(0, 670, 3200, 50, false));
		SceneManager.getInstance().addTile(new Tile(1000, 440, 240, 60, false));
		SceneManager.getInstance().addTile(new Tile(1500, 600, 240, 60, false));
		SceneManager.getInstance().addTile(new Tile(1700, 470, 240, 60, true));
		SceneManager.getInstance().addTile(new Tile(1320, 480, 120, 60, true));
		//SceneManager.getInstance().addTile(new Tile(600, 540, 3200, 50, true));
	}
	
	public static void generateBossArena() {
		SceneManager.getInstance().addTile(new Tile(0, 650, 1280, 50, false));
		SceneManager.getInstance().addTile(new Tile(0, 550, 100, 10, true));
		SceneManager.getInstance().addTile(new Tile(1180, 550, 100, 10, true));
		SceneManager.getInstance().addTile(new Tile(215, 505, 300, 10, true));
		SceneManager.getInstance().addTile(new Tile(750, 505, 300, 10, true));
	}

}
