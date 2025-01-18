package com.comcast.crm.contactTest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.comcast.crm.baseutility.BaseClass;


public class CreateContactTest extends BaseClass   {
	
	@Test
	public void createContact() {
		System.out.println("create contact and verify");
	}
	@Test
	public void createContactWithDate() {
		System.out.println("Create contactwithDate and verify");
	}
	
	
}
