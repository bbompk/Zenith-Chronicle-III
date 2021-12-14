package gui;

import javax.swing.UIManager;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.paint.Color;
import logic.SceneManager;

public class Healthbar extends ProgressBar {

	public Healthbar() {
		// TODO Auto-generated constructor stub
		setLayoutX(10);setLayoutY(10);
		setMinSize(450,50);
		setStyle("-fx-accent: red;"
				+ "-fx-border-color: pink; -fx-skin: RED; -fx-border-width: 5; -fx-border-radius: 20;");
	}

	public Healthbar(double arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public void update() {
		// TODO Auto-generated method stub
		setProgress((double)SceneManager.getInstance().getPlayer().getHp()/(double)SceneManager.getInstance().getPlayer().getMaxHp());
	}

}
