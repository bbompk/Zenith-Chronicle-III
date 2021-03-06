package gui;

import java.util.ArrayList;

import entity.Artifact;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;

public class InventoryPane extends HBox {
	
	private ArrayList<Text> text;
	private static InventoryPane instance = null;

	public InventoryPane() {
		// TODO Auto-generated constructor stub
		text = new ArrayList<Text>();
		setLayoutX(1038);setLayoutY(638);
		for(int i=0;i<6;i++) {
			VBox box = new VBox();
			ImageView image = new ImageView(Artifact.getSprites(i).getImage());
			image.setFitHeight(40);image.setFitWidth(40);
			Text textt = new Text("0");
			textt.setFill(Color.YELLOW);
			textt.setFont(FontHolder.getInstance().getFont().get(30));
			box.setAlignment(Pos.CENTER);
			text.add(textt);
			box.getChildren().add(text.get(i));
			box.getChildren().add(image);
			getChildren().add(box);
		}
	}
	
	public static InventoryPane getInstance() {
		if(instance==null)instance = new InventoryPane();
		return instance;
	}

	public ArrayList<Text> getText() {
		return text;
	}

	public void clear() {
		instance = null;
	}
}
