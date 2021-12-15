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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.Difficulty;
import logic.GameManager;
import logic.HomeScreen;
import logic.KeyHandler;
import logic.SceneManager;

public class UIButton extends Button {

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
				Difficulty.setDifficulty(Difficulty.getDifficulty());
				GameManager.getInstance().gameStart(e);
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
				Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
				StackPane root = new StackPane();
				ImageView image = new ImageView(new Background().getImage().getImage());
				image.setFitHeight(720);image.setFitWidth(1280);
				root.getChildren().add(image);
				root.getChildren().add(new HomeScreen(stage));
				Scene scene = new Scene(root);
				scene.setFill(Color.BLACK);
				stage.setScene(scene);
			}
		});
		if(t == "Quit")setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
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

}