package com.comcast.crm.createorgTest;

import org.junit.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.comcast.crm.baseutility.BaseClass;

public class CreateOrgTest extends BaseClass  {
	
	@Test
	public void createOrgTest() {
		System.out.println("create OrgTest and verify");
	}
	
	@Test
	public void createOrgWithIndustries() {
		System.out.println("createOrgWithIndustries and verify");
	}
	
	
}
