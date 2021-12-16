package gui;

import entity.Background;
import entity.Powerup;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.Difficulty;
import logic.GameManager;
import logic.GameState;
import logic.HomeScreen;
import logic.KeyHandler;
import logic.SceneManager;

public class UIButton extends Button {

	public static final AudioClip clickSound = new AudioClip(ClassLoader.getSystemResource("audio/sfx/clicker.mp3").toString());
	
	public UIButton(String t,int x,int y) {
		// TODO Auto-generated constructor stub
		super(t);
		setFont(FontHolder.getInstance().getFont().get(50));
		setTextFill(Color.BLACK);
		setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0; -fx-background-color: transparent;");
		setPadding(new Insets(0));
		setLayoutX(x);
		setLayoutY(y);
		
		if(t == "Continue")setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				GameManager.getInstance().continuee();
				clickSound.play();
			}
		});
		if(t == "Restart")setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Powerup.clear();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				GameUI.getInstance().clear();
				SceneManager.getInstance().restart();
				GameManager.getInstance().restart();
				KeyHandler.getInstance().restart();
				Difficulty.clear();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Difficulty.setDifficulty(Difficulty.getDifficulty());
				GameManager.getInstance().gameStart(e);
				clickSound.play();
			}
		});
		if(t == "Main Menu")setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Powerup.clear();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				GameUI.getInstance().clear();
				SceneManager.getInstance().restart();
				GameManager.getInstance().restart();
				KeyHandler.getInstance().restart();
				Difficulty.clear();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
				StackPane root = new StackPane();
//				ImageView image = new ImageView(new Background().getImage().getImage());
//				image.setFitHeight(720);image.setFitWidth(1280);
//				root.getChildren().add(image);
				SceneManager.getInstance();
				GameManager.getInstance().setState(GameState.TITLE);
				SceneManager.getInstance().enterHomeScreen();
				root.getChildren().add(KeyHandler.getInstance());
				root.getChildren().add(SceneManager.getInstance());	
				GameManager.getInstance().appStart();
				root.getChildren().add(new HomeScreen(stage));
				Scene scene = new Scene(root);
				scene.setFill(Color.BLACK);
				stage.setScene(scene);
				GameManager.getInstance().stopBGM();
				GameManager.titleBGM.play();
				clickSound.play();
			}
		});
		if(t == "Quit")setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.exit(0);
				((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
				clickSound.play();
			}
		});
		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setFont(FontHolder.getInstance().getFont().get(65));
			}
		});
		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setFont(FontHolder.getInstance().getFont().get(55));
			}
		});
	}
	
	public UIButton(String t,int x,int y,int fontSize,Color color,boolean transparent) {
		super(t);
		setLayoutX(x);setLayoutY(y);
		setFont(FontHolder.getInstance().getFont().get(fontSize));
		setTextFill(color);
		if(transparent) {
			setPadding(new Insets(0));
			setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0; -fx-background-color: transparent;");
		}
		else if(color.equals(Color.BLACK)) {
			setPadding(new Insets(4,0,4,0));
			setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 20; -fx-background-color: white;");
		}
		else {
			setPadding(new Insets(8,12,8,12));
			setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 20; -fx-background-color: black;");
		}
		
	}

}
