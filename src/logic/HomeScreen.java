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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HomeScreen extends GridPane {

	private Label name1;
	private Label name2;
	private Button start;
	private Button quit;
	private Stage stage;
	public HomeScreen(Stage stage) {
		// TODO Auto-generated constructor stub 11 * 12
		this.stage = stage;
		setMinSize(1280, 720);setMaxSize(1280, 720);
		setAlignment(Pos.CENTER);
		setHgap(20);setVgap(20);setPadding(new Insets(25,25,25,25));
		
		name1 = new Label("ZENITH");
		name2 = new Label("CHRONICLE");
		start = new Button("Start");
		quit = new Button("Quit");
		
		name1.setStyle("-fx-text-fill: white;-fx-font-style: italic; -fx-font-size: 120px;-fx-font-weight: bold; -fx-font-family: \"Tahoma\";");
		name2.setStyle("-fx-text-fill: white;-fx-font-style: italic; -fx-font-size: 120px;-fx-font-weight: bold; -fx-font-family: \"Tahoma\";");
		start.setBorder(null);

//		start.setOnMouseClicked(new EventHandler<MouseEvent>() {
//			public void handle(MouseEvent e) {
//				
////				stage.close();
//				Stage stagee = (Stage) ((Node) e.getSource()).getScene().getWindow();
//				setVisible(false);
//				name1.setVisible(false);
//				name2.setVisible(false);
//				start.setVisible(false);
//				quit.setVisible(false);
//
//				StackPane root = new StackPane();
//				Scene scene = new Scene(root,1280,720);
//				scene.setFill(Color.BLACK);
//				stagee.setScene(scene);
//				stagee.setTitle("Zenith chronicle");
//				SceneManager.getInstance().gameStart();
//				root.getChildren().add(KeyHandler.getInstance());
//				root.getChildren().add(SceneManager.getInstance());
//				KeyHandler.getInstance().requestFocus();
//				
//				AnimationTimer animation = new AnimationTimer(){
//					public void handle(long now){
//						 new Thread(() -> {
//							new Thread(() -> { 
//							KeyHandler.getInstance().update();
//							SceneManager.getInstance().update();}).start();
//							} ).start();
//						try {Thread.sleep(5);
//						} catch(Exception e) {} 
//					}
//				};
//				animation.start();
////				stagee.show();
//			}
//		});
		start.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				
//				stage.close();
				Stage stagee = (Stage) ((Node) e.getSource()).getScene().getWindow();
				setVisible(false);
				name1.setVisible(false);
				name2.setVisible(false);
				start.setVisible(false);
				quit.setVisible(false);

				StackPane root = new StackPane();
				Scene scene = new Scene(root,1280,720);
				scene.setFill(Color.BLACK);
				stagee.setScene(scene);
				stagee.setTitle("Zenith chronicle");
				SceneManager.getInstance().gameStart();
				root.getChildren().add(KeyHandler.getInstance());
				root.getChildren().add(SceneManager.getInstance());
				KeyHandler.getInstance().requestFocus();
				
				AnimationTimer animation = new AnimationTimer(){
					public void handle(long now){
						 new Thread(() -> {
							new Thread(() -> { 
							KeyHandler.getInstance().update();
							SceneManager.getInstance().update();}).start();
							} ).start();
						try {Thread.sleep(5);
						} catch(Exception e) {} 
					}
				};
				animation.start();
//				stagee.show();
			}
		});
		
		quit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				stage.close();
			}
		});
		
		add(name1,3,2,6,3);
		add(name2,6,6,8,3);
		add(start,2,10,3,2);
		add(quit,2,12,4,2);
		
	}

}
