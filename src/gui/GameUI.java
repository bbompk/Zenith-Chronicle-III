package gui;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class GameUI extends Pane {
	
	Healthbar healthbar;

	public GameUI() {
		// TODO Auto-generated constructor stub
		healthbar = new Healthbar();
		getChildren().add(healthbar);
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

}
