package gui;

import java.util.ArrayList;

import entity.Powerup;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PowerupPane extends HBox {
	
	private ArrayList<Text> text;
	private static PowerupPane instance = null;

	public PowerupPane() {
		// TODO Auto-generated constructor stub
		text = new ArrayList<Text>();
		setLayoutX(1108);setLayoutY(2);
		setPadding(new Insets(5));
		for(int i=0;i<4;i++) {
			VBox box = new VBox();
			ImageView image = new ImageView(Powerup.getSprites(i).getImage());
			image.setFitHeight(40);image.setFitWidth(40);
			box.getChildren().add(image);
			Text textt = new Text("0");
			textt.setFont(FontHolder.getInstance().getFont().get(30));
			textt.setFill(Color.YELLOW);
			box.setAlignment(Pos.CENTER);
			text.add(textt);
			box.getChildren().add(text.get(i) );
			getChildren().add(box);
		}
	}
	
	public static PowerupPane getInstance() {
		if(instance == null)instance = new PowerupPane();
		return instance;
	}

	public ArrayList<Text> getText() {
		return text;
	}
	
	public void clear() {
		instance = null;
	}
}
