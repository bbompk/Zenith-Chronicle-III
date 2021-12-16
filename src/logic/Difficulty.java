package logic;

import gui.GameUI;
import util.Logger;

public class Difficulty {
	
	private static GameMode difficulty = GameMode.EASY;
	private static double hardMultiply = 0.75;
	public static int countDown = 5;
	public static int level = 0;
	
	public static void goNextLevel() {
		level++;
		GameUI.getInstance().setFloor(level);
		long curTime = System.currentTimeMillis();
		long cleartime = (long) (curTime - GameManager.getInstance().getLastLevelTime())/1000;
		if(level > 1) Logger.log("Floor "+level+"! Clear Time: " + ((cleartime-(cleartime%60))/60)+ "m " + cleartime%60 +" s");
		else Logger.log("Floor "+level+"!");
		GameManager.getInstance().setLastLevelTime(curTime);
		if(countDown == 0 ) {
			countDown = 4;
			goHarder();
			return;
		}
		countDown--;
		hardMultiply += 0.05;
	}
	
	public static void goHarder() {
		hardMultiply += 0.3;
		if(difficulty.equals(GameMode.NORMAL)) {
			hardMultiply += 0.1;
		}
		if(difficulty.equals(GameMode.HARD)) {
			hardMultiply += 0.2;
		}
	}
	
	public static double getExtremeHardMultiply() {
		return (hardMultiply < 1) ? hardMultiply +0.5 : hardMultiply *1.5;
	}

	public static GameMode getDifficulty() {
		return difficulty;
	}

	public static void setDifficulty(GameMode difficulty) {
		Difficulty.difficulty = difficulty;
		if(difficulty.equals(GameMode.EASY)) {
			hardMultiply = 0.70;
		}
		if(difficulty.equals(GameMode.NORMAL)) {
			hardMultiply = 0.95;
		}
		if(difficulty.equals(GameMode.HARD)) {
			hardMultiply = 1.20;
		}
	}

	public static double getHardMultiply() {
		return hardMultiply;
	}

//	private static void setHardMultiply() {
//		if(difficulty.equals(GameMode.EASY)) {
//			hardMultiply = 0.70;
//		}
//		if(difficulty.equals(GameMode.NORMAL)) {
//			hardMultiply = 0.95;
//		}
//		if(difficulty.equals(GameMode.HARD)) {
//			hardMultiply = 1.20;
//		}
//	}

	
	public static void clear() {
		level = 0;countDown = 5;
	}
}
