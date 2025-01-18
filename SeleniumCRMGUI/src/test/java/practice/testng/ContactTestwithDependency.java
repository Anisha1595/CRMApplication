package practice.testng;

import org.testng.annotations.Test;

public class ContactTestwithDependency {
  @Test(priority=1)
  public void createContactTest() {
	  System.out.println("execute createcontact with ---->HDFC ");  
  }
  @Test(dependsOnMethods = "createContactTest")
  public void modifyContactTest() {
	  System.out.println("execute modifyContactTest --->HDFC===>ICICI ");
  }
  @Test(dependsOnMethods = "modifyContactText")
  public void deleteContactTest() {
	  System.out.println("execute deleteContactTest--> UPI");
  }
}
