package logic;

public class Difficulty {
	
	private static GameMode difficulty;
	private static double hardMultiply;
	
	public static void setup(GameMode difficulty) {
		Difficulty.setDifficulty(difficulty);
		Difficulty.setHardMultiply(Difficulty.difficulty);
	}
	
	public static void goNextLevel() {
		hardMultiply += 0.05;
	}
	
	public static void goHarder() {
		if(difficulty.equals(GameMode.NORMAL)) {
			hardMultiply += 0.25;
		}
		if(difficulty.equals(GameMode.HARD)) {
			hardMultiply += 0.50;
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
	}

	public static double getHardMultiply() {
		return hardMultiply;
	}

	public static void setHardMultiply(GameMode difficulty) {
		if(difficulty.equals(GameMode.EASY)) {
			hardMultiply = 0.75;
		}
		if(difficulty.equals(GameMode.NORMAL)) {
			hardMultiply = 1;
		}
		if(difficulty.equals(GameMode.HARD)) {
			hardMultiply = 1.25;
		}
	}

}
