package component;

import java.io.Serializable;
import javafx.scene.image.Image;

public class Sprite implements Serializable {
	
	private Image image;
	String filepath;
	
	public Sprite() {
		// TODO Auto-generated constructor stub
	}

	public Sprite(String filepath) {
		// TODO Auto-generated constructor stub
		this.filepath = filepath;
		this.image = new Image(ClassLoader.getSystemResource(this.filepath).toString());
	}
	
	public void loadImage(String filepath) {
		this.filepath = filepath;
		this.image = new Image(ClassLoader.getSystemResource(this.filepath).toString());
	}
	
	public Image getImage() {
		return this.image;
	}

	public String getFilepath() {
		return this.filepath;
	}
	
}
