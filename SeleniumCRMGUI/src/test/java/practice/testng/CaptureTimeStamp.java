package practice.testng;

import java.time.LocalDateTime;
import java.util.Date;

public class CaptureTimeStamp {
	public static void main(String[] args) {
		LocalDateTime ldt = LocalDateTime.now();
		String timeStamp = ldt.toString().replaceAll(":", "-");
		System.out.println(timeStamp);

	}
}
