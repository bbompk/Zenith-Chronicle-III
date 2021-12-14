package gui;

import entity.Powerup;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class PowerupPane extends HBox {

	public PowerupPane() {
		// TODO Auto-generated constructor stub
		setLayoutX(1108);setLayoutY(2);
		setPadding(new Insets(5));
		for(int i=0;i<4;i++) {
			ImageView image = new ImageView(Powerup.getSprites(i).getImage());
			image.setFitHeight(40);image.setFitWidth(40);
			getChildren().add(image);
		}
	}

	public PowerupPane(double arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public PowerupPane(Node... arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public PowerupPane(double arg0, Node... arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
