package logic;

import component.KeyStatus;
import gui.GameUI;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.Logger;

public class GameManager {
	
	private static GameManager instance = null;
	private boolean Gameend;
	
	private GameState state;
	private GameState musicState;
	private boolean escPress;
	private boolean escPress2;
	private AnimationTimer animation;
	private boolean continuee;
	private boolean pause;
	private int playtime;
	private int playtimem;
	private Thread timerThread;
	
	public static final int screenWidth = 1280;
	public static final int screenHeight = 720;
	public static final double gravity = 0.35;
	public static AudioClip titleBGM = new AudioClip(ClassLoader.getSystemResource("audio/bgm/title_theme.wav").toString());
	public static AudioClip levelBGM = new AudioClip(ClassLoader.getSystemResource("audio/bgm/main_theme(loop).wav").toString());
	public static AudioClip bossBGM = new AudioClip(ClassLoader.getSystemResource("audio/bgm/boss_battle(loop).wav").toString());
	public static AudioClip victoryBGM = new AudioClip(ClassLoader.getSystemResource("audio/bgm/title_theme.wav").toString());
	public static AudioClip gameOverBGM = new AudioClip(ClassLoader.getSystemResource("audio/bgm/game_over.wav").toString());
	
	public GameManager() {
		// TODO Auto-generated constructor stub
		state = GameState.TITLE;
		musicState = GameState.TITLE;
		escPress = false;escPress2 = false;
		continuee = false;pause = false;
		titleBGM.setVolume(0.2);
		levelBGM.setVolume(0.2);
		bossBGM.setVolume(0.2);
		victoryBGM.setVolume(0.2);
		gameOverBGM.setVolume(0.2);
		Gameend = false;
		playtime = 0;
		playtimem = 0;
		timerThread  = new Thread(()->{
			while(!GameManager.getInstance().isGameend()) {
			if(!GameManager.getInstance().getState().equals(GameState.PAUSE)) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
			playtime += 1;
			if(playtime > 5999) {
				playtime -= 6000;
				playtimem += 1;
			}
//			Logger.log(getplaytime());
			}}
		});
		
		timerThread.setDaemon(true);
		timerThread.start();
	}

	public static GameManager getInstance() {
		if(instance == null)instance = new GameManager();
		return instance;
	}
	
	public void update(ActionEvent e) {
		if(pause || (escPress2 && KeyHandler.getInstance().getKeyStatus(27).equals(KeyStatus.FREE)))escPress2 = false;
		if(state == GameState.LEVEL) {
			if((KeyHandler.getInstance().getKeyStatus(27).equals(KeyStatus.DOWN) && !escPress2) || pause) {
				state = GameState.PAUSE;
				GameUI.getInstance().setPausePane(true);
				escPress = true;
				pause = false;
				// TODO handle pause
			}
			
		}
		if(continuee || (escPress && KeyHandler.getInstance().getKeyStatus(27).equals(KeyStatus.FREE)))escPress = false;	
		if(state == GameState.PAUSE) {
			stopBGM();
			if(continuee || (KeyHandler.getInstance().getKeyStatus(27).equals(KeyStatus.DOWN) && !escPress)) {
				state = GameState.LEVEL;
				GameUI.getInstance().setPausePane(false);
				KeyHandler.getInstance().requestFocus();
				escPress2 = true;
				continuee = false;
				// TODO handle pause
			}
		}
		
		if(musicState.equals(GameState.TITLE) && !state.equals(GameState.PAUSE)) {
			playBGM(titleBGM);
		}else if(musicState.equals(GameState.LEVEL) && !state.equals(GameState.PAUSE)) {
			playBGM(levelBGM);
		}else if(musicState.equals(GameState.BOSS) && !state.equals(GameState.PAUSE)) {
			playBGM(bossBGM);
		}else if(musicState.equals(GameState.GAMEOVER) && !state.equals(GameState.PAUSE)) {
			playBGM(gameOverBGM);
		}else if(musicState.equals(GameState.VICTORY) && !state.equals(GameState.PAUSE)) {
			playBGM(victoryBGM);
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
						update(e);
						KeyHandler.getInstance().update();
						if(!state.equals(GameState.PAUSE)) {
							ui.update();
							SceneManager.getInstance().update();
						}
//					}).start();
//				}).start();
				try {Thread.sleep(10);} catch(Exception e) {} 
			}
		};
		animation.start();
	}

	public void appStart() {
		
		animation = new AnimationTimer(){
			public void handle(long now){
//				new Thread(() -> {
//					new Thread(() -> {
//						if(KeyHandler.getInstance().getKeyStatus(69).equals(KeyStatus.DOWN))System.out.println("rgfdfz");;
						
						KeyHandler.getInstance().update();
						if(!state.equals(GameState.PAUSE)) {
							SceneManager.getInstance().update();
						}
//					}).start();
//				}).start();
				try {Thread.sleep(10);} catch(Exception e) {} 
			}
		};
		animation.start();
	}
	
	public void stopBGM() {
		if(titleBGM.isPlaying()) titleBGM.stop();
		if(levelBGM.isPlaying()) levelBGM.stop();
		if(bossBGM.isPlaying()) bossBGM.stop();
		if(gameOverBGM.isPlaying()) gameOverBGM.stop();
		if(victoryBGM.isPlaying()) victoryBGM.stop();
	}

	public void playBGM(AudioClip bgm) {
		if(!bgm.isPlaying()) {
			stopBGM();
			bgm.play();
		}
	}
	
	
	public GameState getState() {
		return state;
	}	
	
	public void setState(GameState state) {
		this.state = state;
	}

	public void clear() {
		escPress= false;escPress2 = true;state = GameState.LEVEL;continuee = false;setGameend(true);
	}
	
	public void restart() {
		animation.stop();setGameend(true);
		instance = null;		
	}

	public void continuee() {
		continuee = true;
	}
	
	public void pause() {
		pause = true;
	}

	public boolean isGameend() {
		return Gameend;
	}

	public void setGameend(boolean gameend) {
		Gameend = gameend;
	}
	
	public String getplaytime(){
		return playtimem + " minute and " + (int)(playtime/100) + " second";
	}
	
	public GameState getMusicState() {
		return musicState;
	}

	public void setMusicState(GameState musicState) {
		this.musicState = musicState;
	}

	public int getplaytimem(){
		return playtimem;
	}
}
