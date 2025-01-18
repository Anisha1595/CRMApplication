package practice.testng;

import org.testng.annotations.Test;

public class ContactTest {
  @Test(priority=1)
  public void createContactTest() {
	  System.out.println("execute createcontact with ---->HDFC ");  
  }
  @Test(priority=2)
  public void modifyContactTest() {
	  System.out.println("execute query insert contact in DB==>ICICI");
	  System.out.println("execute modifyContactTest --->HDFC===>ICICI ");
  }
  @Test(priority=3)
  public void deleteContactTest() {
	  System.out.println("execute query insert contact in DB==>UPI");
	  System.out.println("execute deleteContactTest--> UPI");
  }
}
