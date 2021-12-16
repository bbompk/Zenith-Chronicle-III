package gui;

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
import logic.Difficulty;
import logic.GameManager;
import logic.GameMode;

public class HomeScreen extends Pane {

//	private Button start;
//	private Button how;
//	private Button credit;
//	private Button quit;
//	private Pane difPane;
//	private Pane creditPane;
//	private Pane howPane;
//	private Pane blackPane;
//	private Pane exitPane;
//	private Button backh;
//	private Button backc;
//	private Button easy;
//	private Button normal;
//	private Button hard;
//	private Button x;
//	private Button yes;
//	private Button no;
//	private Text exitt;
	private boolean escfree;
//	private ImageView title;
	
	public HomeScreen(Stage stage) {
		// TODO Auto-generated constructor stub 11 * 12
		setPrefSize(1280, 720);
//---boolean---------------------------------
		escfree = true;
		ImageView title = new ImageView(new Image(ClassLoader.getSystemResource("ui/title.png").toString()));
		title.setFitHeight(720);title.setFitWidth(1280);
//---button---------------------------------
		Button start = new UIButton("Start",80,300,55,Color.YELLOWGREEN,true);
		Button how = new UIButton("How to play",80,400,55,Color.SLATEBLUE,true);
		Button credit = new UIButton("Credit",80,500,55,Color.DEEPSKYBLUE,true);
		Button quit = new UIButton("Exit",80,600,55,Color.ORANGE,true);
		Button backh = new UIButton("Back",1050,10,55,Color.BLACK,false);
		Button backc = new UIButton("Back",1050,10,55,Color.BLACK,false);
		Button x = new UIButton("X",380,0,120,Color.BLACK,true);
		Button easy = new UIButton("Easy",50,50,55,Color.BLACK,true);
		Button normal = new UIButton("Normal",50,150,55,Color.BLACK,true);
		Button hard = new UIButton("Hard",50,250,55,Color.BLACK,true);
		Button yes = new UIButton("Yes",40,110,55,Color.WHITE,false);
		Button no = new UIButton("No",220,110,55,Color.WHITE,false);
		backh.setMinSize(180, 80);backh.setMaxSize(180, 80);
		backc.setMinSize(180, 80);backc.setMaxSize(180, 80);
//---Pane---------------------------------
		Pane exitPane = new UIPane(460,240,360,240,false,false);
		Pane difPane = new UIPane(400,180,480,360,false,false);
		Pane howPane = new UIPane(20,20,1240,680,false,false);
		Pane creditPane = new UIPane(20,20,1240,680,false,false);
		Pane blackPane = new UIPane(0,0,1280,720,false,true);
//---text------------------------------------
		Text exitt = new Text("Exit?");
		exitt.setFont(FontHolder.getInstance().getFont().get(55));
		exitt.setFill(Color.BLACK);exitt.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0; -fx-background-color: transparent;");
		exitt.setLayoutX(120);exitt.setY(70);
		
//---Handler--------------------------------
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
//---how-pane-------------------------------
		ImageView howto = new ImageView(new Image(ClassLoader.getSystemResource("ui/ctrz.png").toString()));
		howto.setLayoutX(-20);howto.setLayoutY(-20);
		ImageView run = new ImageView(new Image(ClassLoader.getSystemResource("sprite/character/player/run.gif").toString()));
		ImageView jump = new ImageView(new Image(ClassLoader.getSystemResource("sprite/character/player/full_jump.gif").toString()));
		ImageView dash = new ImageView(new Image(ClassLoader.getSystemResource("sprite/character/player/roll_4_frame.gif").toString()));
		ImageView atk = new ImageView(new Image(ClassLoader.getSystemResource("sprite/character/player/attack.gif").toString()));
		run.setLayoutX(90);run.setY(110);
		jump.setLayoutX(320);jump.setY(105);
		dash.setX(550);dash.setLayoutY(110);
		atk.setLayoutX(750);atk.setLayoutY(110);
		howPane.getChildren().addAll(howto,run,jump,dash,atk);
//---credit-pane---------------------------
		ImageView creditt = new ImageView(new Image(ClassLoader.getSystemResource("ui/credits.png").toString()));
		creditt.setLayoutX(-20);creditt.setLayoutY(-20);
		creditPane.getChildren().add(creditt);
//---button-handler-------------------------
		start.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if(!blackPane.isVisible()) {
					difPane.setVisible(true);
					blackPane.setVisible(true);
					UIButton.clickSound.play();
				}
			}
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
