package logic;

import component.KeyStatus;

public class GameManager {
	
	private static GameManager instance = null;
	private boolean isGameOver = false;
	private boolean isVictory = false;
	
	public static GameState state;
	
	
	
	public GameManager() {
		// TODO Auto-generated constructor stub
		state = GameState.TITLE;
	}

	public static GameManager getInstance() {
		if(instance == null)instance = new GameManager();
		return instance;
	}
	
	public void update() {
		if(state == GameState.LEVEL) {
			if(KeyHandler.getInstance().getKeyStatus(65).equals(KeyStatus.DOWN)) {
				state = GameState.PAUSE;
				// TODO handle pause
			}
			
			if(isGameOver) {
				state = GameState.GAMEOVER;
				//TODO handle game over screen
			}
			
			if(isVictory) {
				state = GameState.VICTORY;
				//TODO handle victory screen
			}
			
		}
		
		//TODO might have to do with other states, should set up homescreen and run game with gamemanager
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	public boolean isVictory() {
		return isVictory;
	}

	public void setVictory(boolean isVictory) {
		this.isVictory = isVictory;
	}
	
	
}
