package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.Difficulty;
import logic.GameManager;
import logic.GameMode;
import logic.GameState;
import logic.KeyHandler;
import logic.SceneManager;
import entity.Powerup;

public class GameUI extends Pane {
	
	private Healthbar healthbar;
	public Pane pausePane;
	private Button con;
	private Button restart;
	private Button main;
	private Button quit;
	private static GameUI instance = null;
	
	public GameUI() {
		// TODO Auto-generated constructor stub
		pausePane = new Pane();
		con = new Button("Continue");
		restart = new Button("Restart");
		main = new Button("Main Menu");
		quit = new Button("Quit");
		
		pausePane.setVisible(false);
		pausePane.setLayoutX(445);pausePane.setLayoutY(120);
		pausePane.setMinSize(390,460);pausePane.setMaxSize(390, 460);
		pausePane.setStyle("-fx-border-width: 10;-fx-border-color:black;-fx-background-color:gray;-fx-border-radius: 20;-fx-background-radius: 25;");
		con.setFont(FontHolder.getInstance().getFont().get(50));
		restart.setFont(FontHolder.getInstance().getFont().get(50));
		main.setFont(FontHolder.getInstance().getFont().get(50));
		quit.setFont(FontHolder.getInstance().getFont().get(50));
		con.setTextFill(Color.BLACK);con.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0; -fx-background-color: transparent;");
		restart.setTextFill(Color.BLACK);restart.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0; -fx-background-color: transparent;");
		main.setTextFill(Color.BLACK);main.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0; -fx-background-color: transparent;");
		quit.setTextFill(Color.BLACK);quit.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0; -fx-background-color: transparent;");
		con.setPadding(new Insets(0));restart.setPadding(new Insets(0));main.setPadding(new Insets(0));quit.setPadding(new Insets(0));
		con.setLayoutX(50);con.setLayoutY(50);
		restart.setLayoutX(50);restart.setLayoutY(150);
		main.setLayoutX(50);main.setLayoutY(250);
		quit.setLayoutX(50);quit.setLayoutY(350);
		
		con.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				GameManager.getInstance().clear();
				setPausePane(false);
			}
		});
		con.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				con.setFont(FontHolder.getInstance().getFont().get(65));
			}
		});
		con.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				con.setFont(FontHolder.getInstance().getFont().get(55));
			}
		});
		restart.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Powerup.clear();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				clear();
				SceneManager.getInstance().restart();
				GameManager.getInstance().restart();
				KeyHandler.getInstance().restart();
				Difficulty.clear();
				Difficulty.setDifficulty(Difficulty.getDifficulty());
				GameManager.getInstance().gameStart(e);
			}
		});
		restart.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				restart.setFont(FontHolder.getInstance().getFont().get(65));
			}
		});
		restart.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				restart.setFont(FontHolder.getInstance().getFont().get(55));
			}
		});
		main.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Difficulty.setDifficulty(GameMode.EASY);
				GameManager.getInstance().gameStart(e);
			}
		});
		main.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				main.setFont(FontHolder.getInstance().getFont().get(65));
			}
		});
		main.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				main.setFont(FontHolder.getInstance().getFont().get(55));
			}
		});
		quit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
			}
		});
		quit.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				quit.setFont(FontHolder.getInstance().getFont().get(65));
			}
		});
		quit.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				quit.setFont(FontHolder.getInstance().getFont().get(55));
			}
		});
		
		pausePane.getChildren().addAll(con,restart,main,quit);
		healthbar = new Healthbar();
		
		getChildren().add(pausePane);
		getChildren().add(healthbar);
		getChildren().add(InventoryPane.getInstance());
		getChildren().add(PowerupPane.getInstance());
	}
	
	public static GameUI getInstance() {
		if(instance == null) instance = new GameUI();
		return instance;
	}
	
	public void update() {
		healthbar.update();
	}

	public GameUI(Node arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public GameUI(Node arg0, Node arg1, Node arg2, Node arg3, Node arg4) {
		super(arg0, arg1, arg2, arg3, arg4);
		// TODO Auto-generated constructor stub
	}

	public void setPausePane(boolean visible) {
		pausePane.setVisible(visible);
	}
	
	private void clear() {
		InventoryPane.getInstance().clear();
		PowerupPane.getInstance().clear();
		instance = null;
	}

}
