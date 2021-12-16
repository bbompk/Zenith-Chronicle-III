package util;
import java.time.LocalTime;


public class Logger {

	public Logger() {
		// TODO Auto-generated constructor stub
		
	}
	
	public static void log(String msg) {
		LocalTime myObj = LocalTime.now();
	    System.out.println("[" + myObj.toString() + "] " + msg);
	}
	
	

}
