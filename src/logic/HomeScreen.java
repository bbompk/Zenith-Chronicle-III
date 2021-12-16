package logic;

import gui.FontHolder;
import gui.UIButton;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeScreen extends Pane {

	private Button start;
	private Button how;
	private Button credit;
	private Button quit;
	private Pane difPane;
	private Pane creditPane;
	private Pane howPane;
	private Pane blackPane;
	private Pane exitPane;
	private Button backh;
	private Button backc;
	private Button easy;
	private Button normal;
	private Button hard;
	private Button x;
	private Button yes;
	private Button no;
	private Text exitt;
	private boolean escfree;
	private ImageView title;
	
	public HomeScreen(Stage stage) {
		// TODO Auto-generated constructor stub 11 * 12
		setPrefSize(1280, 720);
//		node = new ArrayList<Node>();
		escfree = true;
		difPane = new Pane();
		creditPane = new Pane();
		howPane = new Pane();

		start = new Button("Start");
		how = new Button("How to play");
		credit = new Button("Credit");
		quit = new Button("Exit");
		backh = new Button("Back");
		backc = new Button("Back");
		x = new Button("X");
		easy = new Button("Easy");
		normal = new Button("Normal");
		hard = new Button("Hard");
		yes = new Button("Yes");
		no = new Button("No");
		exitt = new Text("Exit?");
		blackPane = new Pane();
		exitPane = new Pane();
		
		blackPane.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-color:black;");
		blackPane.setOpacity(0.80);
		blackPane.setVisible(false);
		blackPane.setPrefSize(1280, 720);
		
//		exitPane.setViewOrder(0);
		exitPane.setVisible(false);
		exitPane.setLayoutX(460);exitPane.setLayoutY(240);
		exitPane.setMinSize(360,240);exitPane.setMaxSize(360, 240);
		exitPane.setStyle("-fx-border-width: 10;-fx-border-color:black;-fx-background-color:gray;-fx-border-radius: 20;-fx-background-radius: 25;");
//		difPane.setViewOrder(0);
		difPane.setVisible(false);
		difPane.setLayoutX(400);difPane.setLayoutY(180);
		difPane.setMinSize(480,360);difPane.setMaxSize(480, 360);
		difPane.setStyle("-fx-border-width: 10;-fx-border-color:black;-fx-background-color:gray;-fx-border-radius: 20;-fx-background-radius: 25;");
//		howPane.setViewOrder(0);
		howPane.setVisible(false);
		howPane.setLayoutX(20);howPane.setLayoutY(20);
		howPane.setMinSize(1240,680);howPane.setMaxSize(1240, 680);
		howPane.setStyle("-fx-border-width: 10;-fx-border-color:black;-fx-background-color:gray;-fx-border-radius: 20;-fx-background-radius: 25;");
//		creditPane.setViewOrder(0);
		creditPane.setVisible(false);
		creditPane.setLayoutX(20);creditPane.setLayoutY(20);
		creditPane.setMinSize(1240,680);creditPane.setMaxSize(1240, 680);
		creditPane.setStyle("-fx-border-width: 10;-fx-border-color:black;-fx-background-color:gray;-fx-border-radius: 20;-fx-background-radius: 25;");
//		howPane.setEffect(new Glow());creditPane.setEffect(new Glow());
		
		//font----
//		name1.setFont(FontHolder.getInstance().getFont().get(128));
//		name2.setFont(FontHolder.getInstance().getFont().get(128));
//		name3.setFont(FontHolder.getInstance().getFont().get(148));
		credit.setFont(FontHolder.getInstance().getFont().get(50));
		start.setFont(FontHolder.getInstance().getFont().get(55));
		how.setFont(FontHolder.getInstance().getFont().get(55));
		backh.setFont(FontHolder.getInstance().getFont().get(55));
		backc.setFont(FontHolder.getInstance().getFont().get(55));
		quit.setFont(FontHolder.getInstance().getFont().get(55));
		easy.setFont(FontHolder.getInstance().getFont().get(55));
		normal.setFont(FontHolder.getInstance().getFont().get(55));
		hard.setFont(FontHolder.getInstance().getFont().get(55));
		x.setFont(FontHolder.getInstance().getFont().get(120));
		yes.setFont(FontHolder.getInstance().getFont().get(55));
		no.setFont(FontHolder.getInstance().getFont().get(55));
		exitt.setFont(FontHolder.getInstance().getFont().get(55));
		
		//style---
//		name1.setStyle("-fx-text-fill: gray;");
//		name2.setStyle("-fx-text-fill: gray;");
//		name3.setStyle("-fx-text-fill: gray;");
		start.setTextFill(Color.YELLOWGREEN);start.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0; -fx-background-color: transparent;");
		credit.setTextFill(Color.DEEPSKYBLUE);credit.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0; -fx-background-color: transparent;");
		how.setTextFill(Color.SLATEBLUE);how.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0; -fx-background-color: transparent;");
		backh.setTextFill(Color.BLACK);backh.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 20; -fx-background-color: white;");
		backc.setTextFill(Color.BLACK);backc.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 20; -fx-background-color: white;");
		quit.setTextFill(Color.ORANGE);quit.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0; -fx-background-color: transparent;");
		easy.setTextFill(Color.BLACK);easy.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0; -fx-background-color: transparent;");
		normal.setTextFill(Color.BLACK);normal.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0; -fx-background-color: transparent;");
		hard.setTextFill(Color.BLACK);hard.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0; -fx-background-color: transparent;");
		x.setTextFill(Color.BLACK);x.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0; -fx-background-color: transparent;");
		yes.setTextFill(Color.WHITE);yes.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 20; -fx-background-color: black;");
		no.setTextFill(Color.WHITE);no.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 20; -fx-background-color: black;");
		exitt.setFill(Color.BLACK);exitt.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0; -fx-background-color: transparent;");
		
		//Pos--
		start.setPadding(new Insets(0));quit.setPadding(new Insets(0));credit.setPadding(new Insets(0));how.setPadding(new Insets(0));
		easy.setPadding(new Insets(0));normal.setPadding(new Insets(0));hard.setPadding(new Insets(0));x.setPadding(new Insets(0));
		backh.setPadding(new Insets(4,0,4,0));backc.setPadding(new Insets(4,0,4,0));
		yes.setPadding(new Insets(8,12,8,12));no.setPadding(new Insets(8,12,8,12));
		
//		name1.setLayoutX(70);name2.setLayoutY(90);
//		name1.setEffect(new Glow());
//		name2.setLayoutX(300);name2.setLayoutY(120);
//		name2.setEffect(new Glow());
//		name3.setLayoutX(1030);name3.setLayoutY(99);
		start.setLayoutX(80);start.setLayoutY(300);//start.setMinSize(200,60);start.setMaxSize(260,100);
		how.setLayoutX(80);how.setLayoutY(400);
		credit.setLayoutX(80);credit.setLayoutY(500);//credit.setMinSize(220,60);credit.setMaxSize(260,100);
		quit.setLayoutX(80);quit.setLayoutY(600);//quit.setMinSize(180,60);quit.setMaxSize(260,100);
		backh.setLayoutX(1050);backh.setLayoutY(10);backh.setMinSize(180, 80);backh.setMaxSize(180, 80);
		backc.setLayoutX(1050);backc.setLayoutY(10);backc.setMinSize(180, 80);backc.setMaxSize(180, 80);
		easy.setLayoutX(50);easy.setLayoutY(50);
		normal.setLayoutX(50);normal.setLayoutY(150);
		hard.setLayoutX(50);hard.setLayoutY(250);
		x.setLayoutX(380);x.setLayoutY(0);
		yes.setLayoutX(40);yes.setLayoutY(110);
		no.setLayoutX(220);no.setLayoutY(110);
		exitt.setLayoutX(120);exitt.setY(70);
		
		title = new ImageView(new Image(ClassLoader.getSystemResource("ui/title.png").toString()));
//		title.setLayoutX(0);title.setLayoutY(0);
		title.setFitHeight(720);title.setFitWidth(1280);
		
		//Handler--
		start.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if(!blackPane.isVisible()) {
					difPane.setVisible(true);
					blackPane.setVisible(true);
					UIButton.clickSound.play();
				}
			}
		});
		
		setOnKeyPressed(e -> {
			// TODO Auto-generated method stub
			if(e.getCode().getCode() == 27 && escfree) {
				escfree = false;
				if(blackPane.isVisible()) {
					difPane.setVisible(false);
					howPane.setVisible(false);
					creditPane.setVisible(false);
					blackPane.setVisible(false);
					exitPane.setVisible(false);
				}else {
					exitPane.setVisible(true);
					blackPane.setVisible(true);
				}
			}
		});
		setOnKeyReleased(e -> {
			// TODO Auto-generated method stub
			if(e.getCode().getCode() == 27)escfree = true;
		});
		
		
		start.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(!blackPane.isVisible())start.setFont(FontHolder.getInstance().getFont().get(65));
			}
		});
		start.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(!blackPane.isVisible())start.setFont(FontHolder.getInstance().getFont().get(55));
			}
		});
		how.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if(!blackPane.isVisible()) {
					howPane.setVisible(true);
					blackPane.setVisible(true);
					UIButton.clickSound.play();
					howPane.requestFocus();
		
				}
			}
		});
		how.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(!blackPane.isVisible())how.setFont(FontHolder.getInstance().getFont().get(65));
			}
		});
		how.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(!blackPane.isVisible())how.setFont(FontHolder.getInstance().getFont().get(55));
			}
		});
		credit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if(!blackPane.isVisible()) {
					blackPane.setVisible(true);
					creditPane.setVisible(true);
					UIButton.clickSound.play();
					creditPane.requestFocus();
				}
			}
		});
		credit.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(!blackPane.isVisible())credit.setFont(FontHolder.getInstance().getFont().get(65));
			}
		});
		credit.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(!blackPane.isVisible())credit.setFont(FontHolder.getInstance().getFont().get(55));
			}
		});
		quit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if(!blackPane.isVisible()) {
					exitPane.setVisible(true);
					blackPane.setVisible(true);
					UIButton.clickSound.play();
				}
			}
		});
		quit.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(!blackPane.isVisible())quit.setFont(FontHolder.getInstance().getFont().get(65));
			}
		});
		quit.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(!blackPane.isVisible())quit.setFont(FontHolder.getInstance().getFont().get(55));
			}
		});
		backh.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				howPane.setVisible(false);
				blackPane.setVisible(false);
				UIButton.clickSound.play();
			}
		});
		backc.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				creditPane.setVisible(false);
				blackPane.setVisible(false);
				UIButton.clickSound.play();
			}
		});
		x.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				difPane.setVisible(false);
				blackPane.setVisible(false);
				UIButton.clickSound.play();
			}
		});
		x.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				x.setFont(FontHolder.getInstance().getFont().get(140));
				x.setLayoutX(360);x.setLayoutY(0);
			}
		});
		x.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				x.setFont(FontHolder.getInstance().getFont().get(120));
				x.setLayoutX(380);x.setLayoutY(0);
			}
		});
		easy.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Difficulty.setDifficulty(GameMode.EASY);
				System.out.println("Easy? Don't you have a shame?");
				UIButton.clickSound.play();
				GameManager.getInstance().restart();
				GameManager.getInstance().gameStart(e);
			}
		});
		easy.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				easy.setFont(FontHolder.getInstance().getFont().get(65));
			}
		});
		easy.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				easy.setFont(FontHolder.getInstance().getFont().get(55));
			}
		});
		normal.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Difficulty.setDifficulty(GameMode.NORMAL);
				System.out.println("Maybe this game is too easy.");
				UIButton.clickSound.play();
				GameManager.getInstance().restart();
				GameManager.getInstance().gameStart(e);
			}
		});
		normal.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				normal.setFont(FontHolder.getInstance().getFont().get(65));
			}
		});
		normal.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				normal.setFont(FontHolder.getInstance().getFont().get(55));
			}
		});
		hard.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Difficulty.setDifficulty(GameMode.HARD);
				System.out.println("Life is too easy,isn't it?");
				UIButton.clickSound.play();
				GameManager.getInstance().restart();
				GameManager.getInstance().gameStart(e);
			}
		});
		hard.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				hard.setFont(FontHolder.getInstance().getFont().get(65));
			}
		});
		hard.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				hard.setFont(FontHolder.getInstance().getFont().get(55));
			}
		});
		yes.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				UIButton.clickSound.play();
				System.exit(0);
				stage.close();
			}
		});
		yes.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				yes.setFont(FontHolder.getInstance().getFont().get(65));
				yes.setLayoutX(35);yes.setLayoutY(105);
			}
		});
		yes.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				yes.setFont(FontHolder.getInstance().getFont().get(55));
				yes.setLayoutX(40);yes.setLayoutY(110);
			}
		});
		no.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				UIButton.clickSound.play();
				blackPane.setVisible(false);
				exitPane.setVisible(false);
			}
		});
		no.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				no.setFont(FontHolder.getInstance().getFont().get(65));
				no.setLayoutX(215);no.setLayoutY(105);
			}
		});
		no.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				no.setFont(FontHolder.getInstance().getFont().get(55));
				no.setLayoutX(220);no.setLayoutY(110);
			}
		});
		
		exitPane.getChildren().addAll(yes,no,exitt);
		difPane.getChildren().addAll(easy,normal,hard,x);
		howPane.getChildren().add(backh);creditPane.getChildren().add(backc);
		getChildren().addAll(title,start,how,credit,quit,blackPane,difPane,exitPane,howPane,creditPane);
//		getChildren().addAll(name1,name2,name3,start,how,credit,quit,blackPane,difPane,exitPane,howPane,creditPane);
		requestFocus();
	}

}
