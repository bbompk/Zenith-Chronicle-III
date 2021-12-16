package util;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Logger {

	public Logger() {
		// TODO Auto-generated constructor stub
		
	}
	
	public static void log(String msg) {
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
		String formattedDate = myDateObj.format(myFormatObj);
	    System.out.println("[" + formattedDate + "][System] " + msg);
	}
	
	

}
