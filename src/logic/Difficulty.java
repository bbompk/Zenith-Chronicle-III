package logic;

public class Difficulty {
	
	private static GameMode difficulty = GameMode.EASY;
	private static double hardMultiply = 0.75;
	public static int countDown = 5;
	public static int level = 0;
	
	public static void goNextLevel() {
		level++;
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
		setHardMultiply();
	}

	public static double getHardMultiply() {
		return hardMultiply;
	}

	private static void setHardMultiply() {
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

}
