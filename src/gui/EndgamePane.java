package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.GameManager;
import logic.SceneManager;

public class EndgamePane extends Pane {

	private Text text;
	private Text time;
	private Button restart;
	private Button continuee;
	private Button mainmenu;
	private Button exit;
	
	public EndgamePane() {
		// TODO Auto-generated constructor stub
		setVisible(false);
		setLayoutX(400);setLayoutY(120);
		setMinSize(480,520);setMaxSize(480, 520);
		setStyle("-fx-border-width: 10;-fx-border-color:black;-fx-background-color:gray;-fx-border-radius: 20;-fx-background-radius: 25;");
		
		text = new Text();time = new Text();time.setTextAlignment(TextAlignment.CENTER);
		text.setLayoutX(70);text.setY(70);
		time.setLayoutX(60);time.setY(130);
		continuee = new UIButton("Continue",112,190);
		continuee.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				setVisible(false);
				SceneManager.getInstance().endgame(false);
				GameUI.getInstance().setContinue();
				GameManager.getInstance().continuee();
			}
		});
		restart = new UIButton("Restart",120,270);
		mainmenu = new UIButton("Main Menu",95,350);
		exit = new UIButton("Quit",175,430);
		
		text.setFont(FontHolder.getInstance().getFont().get(50));
		time.setFont(FontHolder.getInstance().getFont().get(30));
		
		getChildren().addAll(text,time,continuee,restart,mainmenu,exit);
		
	}
	
	public void setGameText(boolean win,boolean conti) {
		if(win) {
			text.setText("Victory!!!");time.setText("Total time : \n" + SceneManager.getInstance().getPlayer().getplaytime());
			System.out.println("Victory!!!");
			text.setLayoutX(112);
		}else {
			continuee.setVisible(false);
			restart.setLayoutY(220);
			mainmenu.setLayoutY(300);
			exit.setLayoutY(380);
			if(conti) {
				text.setText("You have die!");time.setText("Survive time : \n" + SceneManager.getInstance().getPlayer().getplaytime());
				System.out.println("You have die!");
				text.setLayoutX(70);
			}else {
				text.setText("You lose!!!");time.setText("Survive time : \n" + SceneManager.getInstance().getPlayer().getplaytime());
				if(SceneManager.getInstance().getPlayer().getplaytimem() > 4)System.out.println("Nice try.");
				else System.out.println("You lose!!!");
				text.setLayoutX(95);
			}
		}
		setVisible(true);
		GameManager.getInstance().pause();
	}

}
