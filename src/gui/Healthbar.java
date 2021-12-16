package gui;

import javafx.scene.control.ProgressBar;
import javafx.scene.effect.Glow;
import logic.SceneManager;

public class Healthbar extends ProgressBar {

	public Healthbar() {
		// TODO Auto-generated constructor stub
		setLayoutX(10);setLayoutY(10);
		setMinSize(450,50);
		setStyle("-fx-accent: red;-fx-border-color: pink; -fx-border-width: 3;"
				+ "-fx-border-insets: 0;-fx-border-radius: 5;-fx-padding: 0;"
				);
		Glow glow = new Glow();
		glow.setLevel(0.9);
		setEffect(glow);
	}

	public void update() {
		// TODO Auto-generated method stub
		double k = (double)SceneManager.getInstance().getPlayer().getHp()/(double)SceneManager.getInstance().getPlayer().getMaxHp();
		k = k > 0 ? k : 0;
		setProgress(k);
	}

}
