package logic;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HomeScreen extends Pane {

	private Label name1;
	private Label name2;
	private Button start;
	private Button quit;
	
	public HomeScreen(Stage stage) {
		// TODO Auto-generated constructor stub 11 * 12
		setPrefSize(1280, 720);
		name1 = new Label("ZENITH");
		name2 = new Label("CHRONICLE");
		start = new Button("Start");
		quit = new Button("Quit");
		
		name1.setStyle("-fx-text-fill: white;-fx-font-style: italic; -fx-font-size: 120px;-fx-font-weight: bold; -fx-font-family: \"Tahoma\";");
		name2.setStyle("-fx-text-fill: white;-fx-font-style: italic; -fx-font-size: 120px;-fx-font-weight: bold; -fx-font-family: \"Tahoma\";");
		
		name1.setLayoutX(60);name2.setLayoutY(90);
		name2.setLayoutX(400);name2.setLayoutY(150);
		start.setLayoutX(120);start.setLayoutY(500);
		quit.setLayoutX(120);quit.setLayoutY(600);
		
		start.setBorder(null);
		
		start.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				GameManager.getInstance();
				GameManager.getInstance().gameStart(e);
			}
		});
		
		quit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				stage.close();
			}
		});
		
		getChildren().addAll(name1,name2,start,quit);
		
	}

}
