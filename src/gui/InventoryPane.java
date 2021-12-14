package gui;

import entity.Item;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class InventoryPane extends HBox {

	public InventoryPane() {
		// TODO Auto-generated constructor stub
		setLayoutX(1038);setLayoutY(678);
		for(int i=0;i<6;i++) {
			ImageView image = new ImageView(Item.getSprites(i).getImage());
			image.setFitHeight(40);image.setFitWidth(40);
			getChildren().add(image);
		}
	}

	public InventoryPane(double arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public InventoryPane(Node... arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public InventoryPane(double arg0, Node... arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
