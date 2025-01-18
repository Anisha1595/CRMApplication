package practicedatadriventesting;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;
import org.testng.xml.XmlTest;

public class ReadTheDataFromXmlCreatOrg {

	@Test
	public void creatOrgTest(XmlTest test) throws EncryptedDocumentException, IOException, Throwable {
		//read the data from xml file
		
		WebDriver driver=null;
		String BROWSER = test.getParameter("browser");
		String URL = test.getParameter("url");
		String USERNAME = test.getParameter("username");
		String PASSWORD = test.getParameter("password");
		//String BROWSER= test.getParameter("password");
		
		//generate random number 
		Random random = new Random();
		int randomInt = random.nextInt(1000);
		
		//read the data from excel file
		
		FileInputStream fis1 = new FileInputStream(".\\Testdata\\testScriptdata.xlsx");
		 Workbook wb	=WorkbookFactory.create(fis1);
		 Sheet sh =wb.getSheet("org");
		 Row row = sh.getRow(1);
		 String orgName = row.getCell(2).toString() +randomInt;
		 wb.close();
				
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if(BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		else if(BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		
		}
		else {
			driver = new ChromeDriver();
		}
		
		//step1 Login to app
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				driver.get(URL);
				
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				
				driver.findElement(By.partialLinkText("Organizations")).click();
				

				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

				driver.findElement(By.name("accountname")).sendKeys(orgName);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//logout
				Actions act = new Actions(driver);
				act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
				
				driver.findElement(By.linkText("Sign Out")).click();
	}
}
