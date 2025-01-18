package practice.homeTestAssert;
import java.lang.reflect.Method;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class homePageReporterlogTesst {

	@Test
	public void homePageTest(Method mtd) {
		
		Reporter.log(mtd.getName()+"Test Start",true);
		Reporter.log("step-1",true);
		Reporter.log("step-2",true);
		//Assert.assertEquals("Home", "Home-Page");
		Reporter.log("step-3",true);
		//Assert.assertEquals("Home-CRM", "Home-CRM");
		Reporter.log("step-4",true);
		
		Reporter.log(mtd.getName()+"Test End",true);
		
	}
	
	@Test
	public void verifyLogoHomePageTest(Method mtd) {
		
		Reporter.log(mtd.getName()+"Test Start",true);
		Reporter.log("step-1",true);
		Reporter.log("step-2",true);
	   // Assert.assertTrue(true);
		Reporter.log("step-3",true);
		Reporter.log("step-4",true);
		
		Reporter.log(mtd.getName()+"Test End",true);
		
		
		
	}
}
