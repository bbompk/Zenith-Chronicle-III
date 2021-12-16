package gui;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class UIPane extends Pane {

	public UIPane(int x,int y,int w,int h,boolean visible,boolean fade) {
		// TODO Auto-generated constructor stub
		setVisible(false);
		setLayoutX(x);setLayoutY(y);
		setMinSize(w,h);setMaxSize(w,h);
		if(!fade)setStyle("-fx-border-width: 10;-fx-border-color:black;-fx-background-color:gray;-fx-border-radius: 20;-fx-background-radius: 25;");
		else {
			setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-color:black;");
			setOpacity(0.80);
		}
	}


}
