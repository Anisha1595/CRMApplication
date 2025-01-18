package practice.orgtest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JavaBasics {
public static void main(String[] args) {
	Date dateObj= new Date();
	//System.out.println(dateObj.toString());
	
	//capture system current date
	SimpleDateFormat sim= new SimpleDateFormat("yyyy-MM-dd");
	String actDate =sim.format(dateObj);
	System.out.println(actDate);
	
	//capture date of next 30 days
	
	 Calendar cal = sim.getCalendar();
	 cal.add(Calendar.DAY_OF_MONTH, 30);
	 String dateRequires = sim.format(cal.getTime());
	 System.out.println(dateRequires);
	 

      }
}
