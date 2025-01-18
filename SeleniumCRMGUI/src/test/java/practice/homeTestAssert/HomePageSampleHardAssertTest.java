package practice.homeTestAssert;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageSampleHardAssertTest {

	@Test
	public void homePageTest(Method mtd) {
		
		System.out.println(mtd.getName()+"Test Start");
		System.out.println("step-1");
		System.out.println("step-2");
		Assert.assertEquals("Home", "Home-Page");
		System.out.println("step-3");
		Assert.assertEquals("Home-CRM", "Home-CRM");
		System.out.println("step-4");
		
		System.out.println(mtd.getName()+"Test End");
		
	}
	
	@Test
	public void verifyLogoHomePageTest(Method mtd) {
		
		System.out.println(mtd.getName()+"Test Start");
		System.out.println("step-1");
		System.out.println("step-2");
	    Assert.assertTrue(true);
		System.out.println("step-3");
		System.out.println("step-4");
		
		System.out.println(mtd.getName()+"Test End");
		
		
		
	}
}
