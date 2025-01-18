package com.comcast.crm.baseutility;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
 
	@BeforeClass
	public void configBC() {
		System.out.println("===Launch the Browser===");
	}
	
	@AfterClass
	public void configAC() {
		System.out.println("=====close the Browser===");
	}
	
	@BeforeMethod
	public void configBM() {
		System.out.println("====Login===");
	}
	
	@AfterMethod
	public void configAM() {
		System.out.println("====LogOut====");
	}
	@BeforeSuite
	public void configBS() {
		System.out.println("=== Connect to DB , Report Confi=====");
	}
	
	@AfterSuite
	public void configAS() {
		System.out.println("====Close DB , report Backup====");
	}
	
}
