import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.GameManager;
import logic.GameState;
import logic.KeyHandler;
import logic.SceneManager;
import java.time.Instant;

import entity.Background;
import gui.HomeScreen;


public class Main extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		StackPane root = new StackPane();
//		ImageView image = new ImageView(new Background().getImage().getImage());
//		image.setFitHeight(720);image.setFitWidth(1280);
//		//root.getChildren().add(image);
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
		stage.setTitle("Zenith Chronicles 3");
		stage.setResizable(false);
		stage.show();
		GameManager.getInstance().stopBGM();
		GameManager.titleBGM.play();
		
	}

}
