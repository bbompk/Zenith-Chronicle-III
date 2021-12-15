package gui;

import java.util.ArrayList;

import javafx.scene.text.Font;

public class FontHolder {
	
	private static FontHolder instance = null;
	private ArrayList<Font> font;
	private ArrayList<Font> fontItalic;

	public FontHolder() {
		// TODO Auto-generated constructor stub
		font = new ArrayList<Font>();
		fontItalic = new ArrayList<Font>();
		for(int i = 1;i<201;i++) {
			font.add(Font.loadFont(ClassLoader.getSystemResourceAsStream("ui/font/Alien Eclipse.ttf"), i));
//			fontItalic.add(Font.loadFont(ClassLoader.getSystemResourceAsStream("ui/font/Alien Eclipse Italic.ttf"), i));
		}
	}

	public static FontHolder getInstance() {
		if(instance == null)instance = new FontHolder();
		return instance;
	}

	public ArrayList<Font> getFont() {
		return font;
	}

	public ArrayList<Font> getFontItalic() {
		return fontItalic;
	}
	
}

//public class FontHolder {
//	
//	private static FontHolder instance = null;
//	private InputStream font;
//	private InputStream fontItalic;
//
//	public FontHolder() {
//		// TODO Auto-generated constructor stub
//		font = ClassLoader.getSystemResourceAsStream("ui/font/Alien Eclipse.ttf");
//		fontItalic = ClassLoader.getSystemResourceAsStream("ui/font/Alien Eclipse Italic.ttf");
//	}
//
//	public static FontHolder getInstance() {
//		if(instance == null)instance = new FontHolder();
//		return instance;
//	}
//
//	public Font getFont(int x) {
//		return Font.loadFont(font, x);
//	}
//
//	public Font getFontItalic(int x) {
//		return Font.loadFont(fontItalic, x);
//	}
//	
//	
//
//}
