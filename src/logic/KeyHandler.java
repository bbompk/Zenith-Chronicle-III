package logic;

import java.util.LinkedList;
import java.util.Queue;

import component.KeyStatus;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class KeyHandler extends Canvas{
	
	private static KeyHandler instance = null;

	private boolean LeftClick = false;
	private boolean LeftClickedLastFrame = false;
	private Queue<KeyStatus> action = new LinkedList<>();
	private Queue<Integer> keycode = new LinkedList<>();
	private KeyStatus[] keyCollection = new KeyStatus[112];
	

	private KeyHandler() {
		// TODO Auto-generated constructor stub
		super();
		addListener();
		for(int i=0;i<112;i++) {
			keyCollection[i] = KeyStatus.FREE;
		}
	}
	
	public static KeyHandler getInstance() {
		if(instance == null)instance = new KeyHandler();
		return instance;
	}

	public void addListener() {
		this.setOnKeyPressed((KeyEvent e) ->{
			keyPressed(e);
		});
		this.setOnKeyReleased((KeyEvent e) ->{
			keyReleased(e);
		});
		this.setOnMousePressed((MouseEvent e) -> {
			if (e.getButton() == MouseButton.PRIMARY)
				mouseLeftDown();
		});
		this.setOnMouseReleased((MouseEvent e) -> {
			if (e.getButton() == MouseButton.PRIMARY)
				mouseLeftRelease();
		});
	
		
	}
	
	public void update() {
		LeftClickedLastFrame = LeftClick;
		for(int k=0;k<112;k++) {
			if(keyCollection[k].equals(KeyStatus.PRESS)) {
				keyCollection[k] = KeyStatus.DOWN;
			}else if(keyCollection[k].equals(KeyStatus.RELEASE)) {
				keyCollection[k] = KeyStatus.FREE;
			}
		}
		KeyStatus i = action.poll();
		if(i != null) {
			int keycode = this.keycode.poll();
			if(i.equals(KeyStatus.PRESS) && keyCollection[keycode] != KeyStatus.DOWN) {
				keyCollection[keycode] = KeyStatus.PRESS;
			}
			if(i.equals(KeyStatus.RELEASE) && keyCollection[keycode] != KeyStatus.FREE) {
				keyCollection[keycode] = KeyStatus.RELEASE;
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getCode().getCode() < 112) {
			keycode.add(e.getCode().getCode());
			action.add(KeyStatus.PRESS);
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getCode().getCode() < 112) {
			keycode.add(e.getCode().getCode());
			action.add(KeyStatus.RELEASE);
		}
	}

	public void mouseLeftDown(){
		LeftClick = true;
	}
	
	public void mouseLeftRelease(){
		LeftClick = false;
	}
	
	public boolean isleftClick(){
		return LeftClickedLastFrame;
	}
	
	public void updateInputState(){
		LeftClickedLastFrame = false;
	}
	
	public KeyStatus getKeyStatus(int keycode) {
		return keyCollection[keycode];
	}
	
	public void restart() {
		for(int i=0;i<112;i++) {
			keyCollection[i] = KeyStatus.FREE;
		}
	}
}
