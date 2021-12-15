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
import javafx.scene.effect.Glow;
import javafx.scene.effect.Shadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HomeScreen extends Pane {

	private Label name1;
	private Label name2;
	private Label name3;
	private Button start;
	private Button quit;
	
	public HomeScreen(Stage stage) {
		// TODO Auto-generated constructor stub 11 * 12
		setPrefSize(1280, 720);
		name1 = new Label("ZENITH");
		name2 = new Label("CHRONICLE");
		name3 = new Label("III");
		start = new Button("Start");
		quit = new Button("Quit");
		
		name1.setStyle("-fx-text-fill: gray;-fx-font-style: italic; -fx-font-size: 128px;-fx-font-weight: bold; -fx-font-family: \"Roboto\";");
		name2.setStyle("-fx-text-fill: white;-fx-font-style: italic; -fx-font-size: 128px;-fx-font-weight: bold; -fx-font-family: \"Roboto\";");
		name3.setStyle("-fx-text-fill: white;-fx-font-style: italic; -fx-font-size: 148px;-fx-font-weight: bold; -fx-font-family: \"Roboto\";");
		
		name1.setLayoutX(70);name2.setLayoutY(90);
//		name1.setEffect(new Glow());
		name2.setLayoutX(300);name2.setLayoutY(120);
//		Shadow s = new Shadow();s.setColor(Color.PINK);s.setRadius(2);s.setWidth(2);
//		name2.setEffect(s);
		name3.setLayoutX(1030);name3.setLayoutY(100);
		start.setLayoutX(120);start.setLayoutY(500);
		quit.setLayoutX(120);quit.setLayoutY(600);
		
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
		
		getChildren().addAll(name1,name2,name3,start,quit);
		
	}

}
