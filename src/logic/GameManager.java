package logic;

import component.KeyStatus;
import gui.GameUI;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameManager {
	
	private static GameManager instance = null;
	private boolean isGameOver = false;
	private boolean isVictory = false;
	
	private GameState state;
	private boolean escPress;
	private boolean escPress2;
	private AnimationTimer animation;
	private boolean continuee;
	
	
	public GameManager() {
		// TODO Auto-generated constructor stub
		state = GameState.TITLE;
		escPress = false;escPress2 = false;
		continuee = false;
	}

	public static GameManager getInstance() {
		if(instance == null)instance = new GameManager();
		return instance;
	}
	
	public void update(ActionEvent e) {
		if(escPress2 && KeyHandler.getInstance().getKeyStatus(27).equals(KeyStatus.FREE))escPress2 = false;
		if(state == GameState.LEVEL) {
			if(KeyHandler.getInstance().getKeyStatus(27).equals(KeyStatus.DOWN) && !escPress2) {
				state = GameState.PAUSE;
				GameUI.getInstance().setPausePane(true);
				escPress = true;
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
		if(continuee || (escPress && KeyHandler.getInstance().getKeyStatus(27).equals(KeyStatus.FREE)))escPress = false;	
		if(state == GameState.PAUSE) {
			if(continuee || (KeyHandler.getInstance().getKeyStatus(27).equals(KeyStatus.DOWN) && !escPress)) {
				state = GameState.LEVEL;
				GameUI.getInstance().setPausePane(false);
				KeyHandler.getInstance().requestFocus();
				escPress2 = true;
				continuee = false;
				// TODO handle pause
			}
		}
		//TODO might have to do with other states, should set up homescreen and run game with gamemanager
	}
	
	public void gameStart(ActionEvent e) {
		
		state = GameState.LEVEL;
		Stage stagee = (Stage) ((Node) e.getSource()).getScene().getWindow();

		StackPane root = new StackPane();
		Scene scene = new Scene(root,1280,720);
		scene.setFill(Color.BLACK);
		stagee.setScene(scene);
		stagee.setTitle("Zenith chronicle 3");
		SceneManager.getInstance().setUp();
		SceneManager.getInstance().startLevel();
		//SceneManager.getInstance().startBossLevel();
		GameUI ui = GameUI.getInstance();
		root.getChildren().add(KeyHandler.getInstance());
		root.getChildren().add(SceneManager.getInstance());
		root.getChildren().add(ui);
		KeyHandler.getInstance().requestFocus();
		animation = new AnimationTimer(){
			public void handle(long now){
//				new Thread(() -> {
//					new Thread(() -> {
						if(KeyHandler.getInstance().getKeyStatus(69).equals(KeyStatus.DOWN))System.out.println("rgfdfz");;
						update(e);
						KeyHandler.getInstance().update();
						if(!state.equals(GameState.PAUSE)) {
							ui.update();
							SceneManager.getInstance().update();
						}
//					}).start();
//				}).start();
//				try {Thread.sleep(10);} catch(Exception e) {} 
			}
		};
		animation.start();
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

	public GameState getState() {
		return state;
	}
	
	public void clear() {
		escPress= false;escPress2 = true;state = GameState.LEVEL;continuee = false;
	}
	
	public void restart() {
		animation.stop();
		instance = null;
	}

	public void continuee() {
		continuee = true;
	}
}
