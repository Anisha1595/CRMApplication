package practice.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

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

public class CreateContactWithOrganizationTest {
public static void main(String[] args) throws IOException, InterruptedException {
	//read common data from properties file
			FileInputStream fis = new FileInputStream("./Testdata/demo.properties");
			Properties pObj = new Properties();
			pObj.load(fis);
			
			String BROWSER = pObj.getProperty("browser");
			String URL = pObj.getProperty("url");
			String USERNAME = pObj.getProperty("username");
			String PASSWORD = pObj.getProperty("password");
			
			// generate random number
			Random random = new Random();
			int randomInt = random.nextInt(1000);

			// read the data from excel file

			FileInputStream fis1 = new FileInputStream(".//Testdata//testdataorg.xlsx");
			Workbook wb = WorkbookFactory.create(fis1);
			Sheet sh = wb.getSheet("contact");
			Row row = sh.getRow(7);
			String  orgName= row.getCell(2).toString() + randomInt;
			String contactLastName = row.getCell(3).getStringCellValue();
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
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
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
			
			Thread.sleep(5000);
			//navigate to contact module
			driver.findElement(By.linkText("Contacts")).click();
			
	       //click on create contact button
			driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
			
			//enter all the details and create a contact
			driver.findElement(By.name("lastname")).sendKeys(contactLastName);
			driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
			
			//switch to child window
		      Set<String> set = driver.getWindowHandles();
		      Iterator<String> it = set.iterator();
		      while(it.hasNext()) {
		    	String windowID = it.next();  
		    	driver.switchTo().window(windowID);
		    	
		     String actUrl = driver.getCurrentUrl();
		     if(actUrl.contains("module=Accounts")) {
		    	 break;
		        }
		      }
			
			driver.findElement(By.name("search_text")).sendKeys(orgName);
			driver.findElement(By.name("search")).click();
			driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click(); //dynamic xpath
			
			//switch to parent window
			Set<String> set1 = driver.getWindowHandles();
		      Iterator<String> it1 = set.iterator();
		      while(it1.hasNext()) {
		    	String windowID = it.next();  
		    	driver.switchTo().window(windowID);
		    	
		     String actUrl = driver.getCurrentUrl();
		     if(actUrl.contains("Contacts&action")) {
		    	 break;
		        }
		      }
			
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			
			// Verify the header information

			headerInfo = driver.findElement(By.xpath("//span[@class='tvHeaderText']")).getText();
			if (headerInfo.equals(orgName)) {
				System.out.println(orgName + "headerInfo is created==PASS");
			} else {
				System.out.println(orgName+ "headerInfo is not created==FAIL");
			}
			
			//Verify Header orgName info Expected Result
			String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
			if(actOrgName.equals(orgName)) {
				System.out.println(orgName + "Information is created==PASS");
			}
			else {
				System.out.println(orgName + "Information is not created==FAIL");
			}
			
			//logout
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).click();
			
			driver.findElement(By.linkText("Sign Out")).click();
}
}
