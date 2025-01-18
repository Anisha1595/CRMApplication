package practice.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrganizatioTest {
	public static void main(String[] args) throws IOException  {
		//read common data from properties file
		FileInputStream fis = new FileInputStream("./Testdata/demo.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		
				String BROWSER = pObj.getProperty("browser");
				String URL = pObj.getProperty("url");
				String USERNAME = pObj.getProperty("username");
				String PASSWORD = pObj.getProperty("password");
				
				//generate random number 
				Random random = new Random();
				int randomInt = random.nextInt(1000);
				
				//read the data from excel file
				
				FileInputStream fis1 = new FileInputStream(".\\Testdata\\testdataorg.xlsx");
				 Workbook wb	=WorkbookFactory.create(fis1);
				 Sheet sh =wb.getSheet("org");
				 Row row = sh.getRow(1);
				 String orgName = row.getCell(2).toString() +randomInt;
				 wb.close();
				
				WebDriver driver = null;
				
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
				
				driver.get(URL);
				
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				
				//step 2 navigate the organization module
				driver.findElement(By.partialLinkText("Organizations")).click();
				
				//step 3 click on organization button
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				//enter all the details and create new organization
				driver.findElement(By.name("accountname")).sendKeys(orgName);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//verify Header msg expected Result
				String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(headerInfo.contains(orgName)) {
					System.out.println(orgName + "is created==PASS");
				}
				else {
					System.out.println(orgName + "is not created==FAIL");
				}
				
				//Verify Header orgName info Expected Result
				String actOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
				if(actOrgName.equals(orgName)) {
					System.out.println(orgName + "Information is created==PASS");
				}
				else {
					System.out.println(orgName + "Information is not created==FAIL");
				}
				//logout
				Actions act = new Actions(driver);
				act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
				
				driver.findElement(By.linkText("Sign Out")).click();
				driver.quit();
			}

}
